package com.clinica.backend.exception;

import java.time.Instant;

public record ErroResposta(
    Instant timestamp,
    Integer status,
    String error,
    String message,
    String path
) {}
