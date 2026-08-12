package com.clinica.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.backend.domain.Medico;
import com.clinica.backend.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    // Regra 1: Cadastrar Médico (Verifica se CRM já existe)
    public Medico salvar(Medico medico) {
        if (medicoRepository.existsByCrm(medico.getCrm())) {
            throw new RuntimeException("Já existe um médico cadastrado com o CRM: " + medico.getCrm());
        }
        return medicoRepository.save(medico);
    }

    // Regra 2: Listar todos os médicos
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    // Regra 3: Buscar médico por ID (Lança exceção se não encontrar)
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + id));
    }

    public Medico atualizar(Long id, Medico medico) {

        Medico medicoExistente = buscarPorId(id);

        if (!medicoExistente.getCrm().equals(medico.getCrm())) {
            if (medicoRepository.existsByCrm(medico.getCrm())) {
                throw new RuntimeException("Já existe um médico cadastrado com o CRM: " + medico.getCrm());
            }
        }

        medicoExistente.setNome(medico.getNome());
        medicoExistente.setCrm(medico.getCrm());
        medicoExistente.setEspecialidade(medico.getEspecialidade());

        return medicoRepository.save(medicoExistente);

    }

    // Regra 4: Buscar médico por CRM
    public Medico buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com CRM: " + crm));
    }

    // Regra 5: Deletar médico por ID
    public void deletar(Long id) {
        Medico medico = buscarPorId(id); // Garante que existe antes de deletar
        medicoRepository.delete(medico);
    }
}
