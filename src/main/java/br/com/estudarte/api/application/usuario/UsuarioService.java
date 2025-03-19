package br.com.estudarte.api.application.usuario;

import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.security.token.TokenService;
import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import br.com.estudarte.api.infra.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity cadastrarUsuario(UsuarioDTO dto) {
        if(usuarioRepository.existePorLogin(dto.login())) {
            throw new ValidacaoException("Já existe um usuário com esse login!");
        } else {
            var encodedPwd = passwordEncoder.encode(dto.senha());

            UsuarioEntity usuario = new UsuarioEntity(dto.login(), encodedPwd);
            usuarioRepository.salvar(usuario);
            return usuario;
        }
    }

    public String loginUsuario(UsuarioDTO dto) {
        var authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(dto.login(), dto.senha()));
        var tokenJWT = tokenService.createToken((UsuarioEntity) authentication.getPrincipal());
        return tokenJWT;
    }
}
