package br.com.fiap.betadvisor.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRegistroDTO(
        @NotBlank String login,
        @NotBlank String senha) {
}