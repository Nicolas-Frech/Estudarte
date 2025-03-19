package br.com.estudarte.api.infra.usuario.repository;

import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByLogin(String login);
}
