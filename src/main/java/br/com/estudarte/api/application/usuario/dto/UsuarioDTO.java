package br.com.estudarte.api.application.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotNull
        String login,
        @NotNull
        String senha) {
}
