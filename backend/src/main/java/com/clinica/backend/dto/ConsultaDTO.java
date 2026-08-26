package com.clinica.backend.dto;

import java.time.LocalDateTime;
import com.clinica.backend.domain.Consulta;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(
        Long id,

        @NotNull(message = "A data e hora da consulta são obrigatórias.") LocalDateTime dataHora,

        @NotNull(message = "O ID do paciente é obrigatório.") Long pacienteId,

        String pacienteNome,

        @NotNull(message = "O ID do médico é obrigatório.") Long medicoId,

        String medicoNome,

        String observacoes) {

    public static ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getId(),
                consulta.getDataHora(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getMedico().getId(),
                consulta.getMedico().getNome(),
                consulta.getObservacoes());
    }
}
