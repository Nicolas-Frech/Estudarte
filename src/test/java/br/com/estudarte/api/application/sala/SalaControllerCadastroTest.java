package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.sala.SalaEntity;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class SalaControllerCadastroTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<SalaDTO> salaDTOJson;

    @Autowired
    private JacksonTester<SalaDetalhadamentoDTO> salaDetalhadamentoDTOJson;

    @MockitoBean
    private SalaService salaService;


    @Test
    @DisplayName("Deveria devolver código 400 quando informacões estão inválidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/sala")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informacões estão válidas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var salaDTO = new SalaDTO( "Sala", Modalidade.SAXOFONE);

        when(salaService.registrarSala(any())).thenReturn(new SalaEntity(salaDTO));

        var response = mvc.perform(post("/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salaDTOJson.write(salaDTO).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var salaDTODetalhadamento = new SalaDetalhadamentoDTO(
                null,
                salaDTO.nome(),
                salaDTO.modalidade(),
                false,
                new ArrayList<>(),
                true
        );

        var jsonEsperado = salaDetalhadamentoDTOJson.write(salaDTODetalhadamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}