package com.clinica.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clinica.backend.domain.Paciente;
import com.clinica.backend.dto.PacienteDTO;
import com.clinica.backend.service.PacienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // 1. Cadastrar Paciente (POST /api/pacientes)
    @PostMapping
    public ResponseEntity<PacienteDTO> criar(@Valid @RequestBody PacienteDTO dto) {
        Paciente pacienteSalvo = pacienteService.salvar(dto.toEntity());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pacienteSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(PacienteDTO.toDto(pacienteSalvo));
    }

    // 2. Listar Todos (GET /api/pacientes)
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listarTodos() {
        List<PacienteDTO> lista = pacienteService.listarTodos().stream()
                .map(PacienteDTO::toDto)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // 3. Buscar por ID (GET /api/pacientes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(PacienteDTO.toDto(paciente));
    }

    // 4. Atualizar Paciente (PUT /api/pacientes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        Paciente pacienteAtualizado = pacienteService.atualizar(id, dto.toEntity());
        return ResponseEntity.ok(PacienteDTO.toDto(pacienteAtualizado));
    }

}
