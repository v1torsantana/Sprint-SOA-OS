package br.com.fiap.betadvisor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApostaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleApostaNotFoundException(ApostaNotFoundException ex) {
        Map<String, Object> corpoErro = new HashMap<>();
        corpoErro.put("message", ex.getMessage());
        corpoErro.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(corpoErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> corpoErro = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        corpoErro.put("message", "Erro de validação nos campos.");
        corpoErro.put("status", HttpStatus.BAD_REQUEST.value());
        corpoErro.put("errors", errors);

        return new ResponseEntity<>(corpoErro, HttpStatus.BAD_REQUEST);
    }
}
