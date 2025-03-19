package br.com.estudarte.api.infra.usuario.repository;

import br.com.estudarte.api.infra.usuario.UsuarioEntity;

public interface UsuarioRepository {

    boolean existePorLogin(String login);

    UsuarioEntity salvar(UsuarioEntity usuario);

    UsuarioEntity buscarPorLogin(String login);
}
