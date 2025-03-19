package br.com.estudarte.api.application.usuario;

import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.application.usuario.dto.UsuarioDetalhadamentoDTO;
import br.com.estudarte.api.infra.security.token.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioDTO dto) {
        var usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok(new UsuarioDetalhadamentoDTO(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity loginUsuario(@RequestBody @Valid UsuarioDTO dto) {
        var tokenJWT = usuarioService.loginUsuario(dto);
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
