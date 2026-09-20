package com.clinica.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.clinica.backend.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Evita o erro clássico de NullPointerException. Se o CPF não existir no banco,
    // o Optional retornará vazio (Optional.empty()), permitindo tratar a busca de
    // forma segura.
    Optional<Paciente> findByCpf(String cpf);

    // Verifica se o CPF já existe no banco antes de cadastrar um novo paciente.
    boolean existsByCpf(String cpf);
}
