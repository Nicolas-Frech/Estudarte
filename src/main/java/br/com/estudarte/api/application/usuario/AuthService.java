package br.com.estudarte.api.application.usuario;

import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.application.usuario.validacoes.ValidadorUsuarioLogin;
import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import br.com.estudarte.api.application.usuario.gateway.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorUsuarioLogin validadorUsuarioLogin;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity cadastrarUsuario(UsuarioDTO dto) {
        validadorUsuarioLogin.validar(dto.login());

        var encodedPwd = passwordEncoder.encode(dto.senha());
        UsuarioEntity usuario = new UsuarioEntity(dto.login(), encodedPwd);

        usuarioRepository.salvar(usuario);
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.buscarPorLogin(username);
    }
}
