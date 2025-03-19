package br.com.estudarte.api.application.usuario;

import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.application.usuario.dto.UsuarioDetalhadamentoDTO;
import br.com.estudarte.api.infra.security.token.TokenDTO;
import br.com.estudarte.api.infra.security.token.TokenService;
import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        var usuario = authService.cadastrarUsuario(dto);

        var uri = uriBuilder.path("/login/cadastro/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDetalhadamentoDTO(usuario));
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UsuarioDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.createToken((UsuarioEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
