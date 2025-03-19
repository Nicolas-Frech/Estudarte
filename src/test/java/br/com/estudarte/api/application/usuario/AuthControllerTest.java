package br.com.estudarte.api.application.usuario;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.application.usuario.dto.UsuarioDTO;
import br.com.estudarte.api.application.usuario.dto.UsuarioDetalhadamentoDTO;
import br.com.estudarte.api.infra.usuario.UsuarioEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private JacksonTester<UsuarioDTO> usuarioDTOJson;

    @Autowired
    private JacksonTester<UsuarioDetalhadamentoDTO> usuarioDetalhadamentoDTOJson;


    @Test
    @DisplayName("Deveria devolver código 400 quando informacões estão inválidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/login/cadastro")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informacões estão válidas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO("loginUsuario", "senha");

        when(authService.cadastrarUsuario(any())).thenReturn(new UsuarioEntity(usuarioDTO));

        var response =  mvc.perform(post("/login/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioDTOJson.write(usuarioDTO).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var usuarioDetalhadamentoDTO = new UsuarioDetalhadamentoDTO(null, "loginUsuario", "senha");

        var jsonEsperado = usuarioDetalhadamentoDTOJson.write(usuarioDetalhadamentoDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}