package com.clinica.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.backend.domain.Consulta;
import com.clinica.backend.domain.Medico;
import com.clinica.backend.domain.Paciente;
import com.clinica.backend.domain.enums.StatusConsulta;
import com.clinica.backend.dto.ConsultaDTO;
import com.clinica.backend.exception.RecursoNaoEncontradoException;
import com.clinica.backend.exception.RegraDeNegocioException;
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
            throw new RegraDeNegocioException("O médico já possui uma consulta agendada para este horário.");
        }
        if (consultaRepository.existsByPacienteIdAndDataHora(dto.pacienteId(), dto.dataHora())) {
            throw new RegraDeNegocioException("O paciente já possui uma consulta agendada para este horário.");
        }

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.dataHora());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setStatus(StatusConsulta.AGENDADA);
        consulta.setObservacoes(dto.observacoes());

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarPorMedicoOuPacienteOuStatus(Long medicoId, Long pacienteId, StatusConsulta status) {
        return consultaRepository.buscarComFiltros(medicoId, pacienteId, status);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta remarcar(Long id, ConsultaDTO dto) {
        Consulta consulta = buscarPorId(id);

        Medico medico = medicoService.buscarPorId(dto.medicoId());
        Paciente paciente = pacienteService.buscarPorId(dto.pacienteId());

        if (!consulta.getPaciente().getId().equals(dto.pacienteId())) {
            throw new RegraDeNegocioException("Não é permitido alterar o paciente de uma consulta já agendada.");
        }

        if (consultaRepository.existsByMedicoIdAndDataHoraAndIdNot(dto.medicoId(), dto.dataHora(), id)) {
            throw new RegraDeNegocioException("O médico já possui uma consulta agendada para este horário.");
        }
        if (consultaRepository.existsByPacienteIdAndDataHoraAndIdNot(dto.pacienteId(), dto.dataHora(), id)) {
            throw new RegraDeNegocioException("O paciente já possui uma consulta agendada para este horário.");
        }

        consulta.setDataHora(dto.dataHora());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setObservacoes(dto.observacoes());

        return consultaRepository.save(consulta);

    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consulta não encontrada com ID: " + id));
    }

    public void cancelar(Long id) {
        Consulta consulta = buscarPorId(id);
        if (consulta.getStatus() == StatusConsulta.REALIZADA || consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new RegraDeNegocioException(
                    "Não é possível cancelar uma consulta que já foi realizada ou cancelada.");
        }
        consulta.setStatus(StatusConsulta.CANCELADA);
        consultaRepository.save(consulta);
    }
}
