package com.elmensajero.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Envío no encontrado → 404
    @ExceptionHandler(EnvioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEnvioNotFound(EnvioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "No encontrado",
                        ex.getMessage()
                ));
    }

    // Validaciones (@NotNull, @NotBlank, etc.) → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Error de validación",
                        errores.toString()
                ));
    }

    // Tarifa no encontrada u otros errores de negocio → 400
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Error de negocio",
                        ex.getMessage()
                ));
    }

    // Cualquier otro error inesperado → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Error interno del servidor",
                        ex.getMessage()
                ));
    }

    // Record interno para la respuesta de error
    public record ErrorResponse(
            int status,
            String error,
            String mensaje,
            LocalDateTime timestamp
    ) {
        public ErrorResponse(int status, String error, String mensaje) {
            this(status, error, mensaje, LocalDateTime.now());
        }
    }
}