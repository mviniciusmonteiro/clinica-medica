package com.clinica.backend.dto;

import com.clinica.backend.domain.Medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MedicoDTO(
    Long id,

    @NotBlank(message = "O nome do médico é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O CRM é obrigatório.")
    @Size(min = 4, max = 20, message = "O CRM deve ter entre 4 e 20 caracteres.")
    String crm,

    @NotBlank(message = "A especialidade é obrigatória.")
    String especialidade
) {

    // Método utilitário: Converte Entidade JPA em DTO (para enviar a resposta HTTP)
    public static MedicoDTO toDTO(Medico medico) {
        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }

    // Método utilitário: Converte DTO em Entidade JPA (para salvar no Banco)
    public Medico toEntity() {
        return new Medico(id, nome, crm, especialidade);
    }
}
