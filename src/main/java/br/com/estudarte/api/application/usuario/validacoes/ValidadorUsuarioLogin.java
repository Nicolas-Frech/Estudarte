package br.com.estudarte.api.application.usuario.validacoes;


import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.application.usuario.gateway.UsuarioRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioLogin {


    private final UsuarioRepository usuarioRepository;

    public ValidadorUsuarioLogin(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validar(String login) {
        if(usuarioRepository.existePorLogin(login)) {
            throw new ValidacaoException("Já existe um usuário com esse login!");
        }
    }
}