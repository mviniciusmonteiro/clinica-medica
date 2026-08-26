
package com.clinica.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clinica.backend.domain.Consulta;
import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.service.ConsultaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    // 1. Agendar Consulta (POST /api/consultas)
    @PostMapping
    public ResponseEntity<ConsultaDTO> agendar(@Valid @RequestBody ConsultaDTO dto) {
        Consulta consultaSalva = consultaService.agendar(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultaSalva.getId())
                .toUri();

        return ResponseEntity.created(location).body(ConsultaDTO.toDTO(consultaSalva));
    }

    // 2. Listar Todas as Consultas (GET /api/consultas)
    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarTodas() {
        List<ConsultaDTO> lista = consultaService.listarTodas().stream()
                .map(ConsultaDTO::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // 3. Buscar Consulta por ID (GET /api/consultas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(ConsultaDTO.toDTO(consulta));
    }

    // 4. Cancelar Consulta (DELETE /api/consultas/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
