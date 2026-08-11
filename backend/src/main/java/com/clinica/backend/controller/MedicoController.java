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

import com.clinica.backend.domain.Medico;
import com.clinica.backend.dto.MedicoDTO;
import com.clinica.backend.service.MedicoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    // 1. Cadastrar Médico (POST /api/medicos)
    @PostMapping
    public ResponseEntity<MedicoDTO> criar(@Valid @RequestBody MedicoDTO dto) {
        Medico medicoSalvo = medicoService.salvar(dto.toEntity());
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(medicoSalvo.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(MedicoDTO.toDTO(medicoSalvo));
    }

    // 2. Listar Todos (GET /api/medicos)
    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarTodos() {
        List<MedicoDTO> lista = medicoService.listarTodos().stream()
                .map(MedicoDTO::toDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // 3. Buscar por ID (GET /api/medicos/1)
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(MedicoDTO.toDTO(medico));
    }

    // 4. Deletar por ID (DELETE /api/medicos/1)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
