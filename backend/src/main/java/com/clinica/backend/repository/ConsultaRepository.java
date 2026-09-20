package com.clinica.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.backend.domain.Consulta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinica.backend.domain.enums.StatusConsulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

    boolean existsByPacienteIdAndDataHora(Long pacienteId, LocalDateTime dataHora);

    List<Consulta> findByMedicoId(Long medicoId);

    List<Consulta> findByPacienteId(Long pacienteId);

    boolean existsByMedicoIdAndDataHoraAndIdNot(Long medicoId, LocalDateTime dataHora, Long id);

    boolean existsByPacienteIdAndDataHoraAndIdNot(Long pacienteId, LocalDateTime dataHora, Long id);

    @Query("""
                SELECT c FROM Consulta c
                WHERE (:medicoId IS NULL OR c.medico.id = :medicoId)
                  AND (:pacienteId IS NULL OR c.paciente.id = :pacienteId)
                  AND (:status IS NULL OR c.status = :status)
            """)
    List<Consulta> buscarComFiltros(
            @Param("medicoId") Long medicoId,
            @Param("pacienteId") Long pacienteId,
            @Param("status") StatusConsulta status);
}
