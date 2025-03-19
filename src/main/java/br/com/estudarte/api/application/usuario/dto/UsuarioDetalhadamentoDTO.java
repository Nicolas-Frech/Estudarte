package br.com.estudarte.api.application.usuario.dto;

import br.com.estudarte.api.infra.usuario.UsuarioEntity;

public record UsuarioDetalhadamentoDTO(Long id, String login, String senha) {
    public UsuarioDetalhadamentoDTO(UsuarioEntity usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
