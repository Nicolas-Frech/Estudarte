package br.com.estudarte.api.infra.usuario.repository.impl;

import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import br.com.estudarte.api.application.usuario.gateway.UsuarioRepository;
import br.com.estudarte.api.infra.usuario.repository.UsuarioRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioRepositoryJpa jpaRepository;


    public UsuarioRepositoryImpl(UsuarioRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existePorLogin(String login) {
        return jpaRepository.existsByLogin(login);
    }

    @Override
    public UsuarioEntity salvar(UsuarioEntity usuario) {
        return jpaRepository.save(usuario);
    }

    @Override
    public UsuarioEntity buscarPorLogin(String login) {
        return jpaRepository.findByLogin(login);
    }
}
