package com.clinica.backend.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Quando uma entidade não for encontrada pelo ID ou identificador único (HTTP 404)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> recursoNaoEncontrado(
            RecursoNaoEncontradoException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResposta erro = new ErroResposta(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(erro);
    }

    // 2. Quando houver violação de regra de negócio (ex: choque de horários, duplicidade de CRM/CPF) (HTTP 400)
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroResposta> regraDeNegocio(
            RegraDeNegocioException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResposta erro = new ErroResposta(
                Instant.now(),
                status.value(),
                "Violação de regra de negócio",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(erro);
    }

    // 3. Quando dados enviados no corpo da requisição falharem nas anotações do @Valid (HTTP 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> validacaoCampos(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErroResposta erro = new ErroResposta(
                Instant.now(),
                status.value(),
                "Erro de validação nos campos",
                mensagens,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(erro);
    }

    // 4. Tratamento genérico para erros inesperados do sistema (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> erroInesperado(
            Exception ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroResposta erro = new ErroResposta(
                Instant.now(),
                status.value(),
                "Erro interno do servidor",
                "Ocorreu um erro interno inesperado no servidor.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(erro);
    }
}
