package com.clinica.backend.dto;

import com.clinica.backend.domain.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PacienteDTO(
        Long id,

        @NotBlank(message = "O nome do paciente é obrigatório.") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.") String nome,

        @NotBlank(message = "O CPF do paciente é obrigatório.") @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres.") String cpf,

        @NotBlank(message = "A data de nascimento do paciente é obrigatória.") @Size(min = 10, max = 10, message = "A data de nascimento deve ter 10 caracteres.") String dataNascimento,

        @Size(min = 1, max = 1, message = "O sexo deve ter 1 caractere.") String sexo,

        @Size(min = 3, max = 100, message = "O email deve ter entre 3 e 100 caracteres.") String email) {

    public static PacienteDTO toDto(Paciente paciente) {
        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getDataNascimento(),
                paciente.getSexo(), paciente.getEmail());
    }

    public Paciente toEntity() {
        return new Paciente(id, nome, cpf, dataNascimento, sexo, email);
    }

}
