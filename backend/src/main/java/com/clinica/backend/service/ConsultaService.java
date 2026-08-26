package com.clinica.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.backend.domain.Consulta;
import com.clinica.backend.domain.Medico;
import com.clinica.backend.domain.Paciente;
import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public Consulta agendar(ConsultaDTO dto) {
        Paciente paciente = pacienteService.buscarPorId(dto.pacienteId());
        Medico medico = medicoService.buscarPorId(dto.medicoId());

        if (consultaRepository.existsByMedicoIdAndDataHora(dto.medicoId(), dto.dataHora())) {
            throw new RuntimeException("O médico já possui uma consulta agendada para este horário.");
        }
        if (consultaRepository.existsByPacienteIdAndDataHora(dto.pacienteId(), dto.dataHora())) {
            throw new RuntimeException("O paciente já possui uma consulta agendada para este horário.");
        }

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.dataHora());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setObservacoes(dto.observacoes());

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
    }

    public void cancelar(Long id) {
        Consulta consulta = buscarPorId(id);
        consultaRepository.delete(consulta);
    }
}
