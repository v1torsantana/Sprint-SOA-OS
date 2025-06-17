package br.com.fiap.betadvisor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApostaNotFoundException extends RuntimeException {

    public ApostaNotFoundException(String message) {
        super(message);
    }
}