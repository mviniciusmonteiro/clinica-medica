package com.clinica.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.backend.domain.Paciente;
import com.clinica.backend.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new RuntimeException(("Já existe um paciente cadastrado com esse CPF."));
        }
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
    }

    public Paciente atualizar(Long id, Paciente paciente) {
        Paciente pacienteExistente = buscarPorId(id);

        if (!pacienteExistente.getCpf().equals(paciente.getCpf()) &&
                pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new RuntimeException("Já existe um paciente cadastrado com esse CPF.");
        }

        pacienteExistente.setNome(paciente.getNome());
        pacienteExistente.setCpf(paciente.getCpf());
        pacienteExistente.setDataNascimento(paciente.getDataNascimento());
        pacienteExistente.setSexo(paciente.getSexo());
        pacienteExistente.setEmail(paciente.getEmail());

        return pacienteRepository.save(pacienteExistente);
    }
}
