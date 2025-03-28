package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aula.AulaEntity;
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
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AulaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AulaDTO> aulaDTOJson;

    @Autowired
    private JacksonTester<AulaDetalhadamentoDTO> aulaDetalhadamentoDTOJson;

    @MockitoBean
    private AulaService aulaService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/aula"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var modalidade = Modalidade.SAXOFONE;
        var saladto = new SalaDTO("Sala", Modalidade.SAXOFONE);
        var sala = new SalaEntity(saladto);

        var aulaDTO = new AulaDTO("", "", modalidade, data, "Sala");
        var aula = new AulaEntity(aulaDTO);
        aula.adicionarSala(sala);

        when(aulaService.agendarAula(any())).thenReturn(aula);

        var response = mvc
                .perform(
                        post("/aula")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(aulaDTOJson.write(
                                        new AulaDTO("", "", modalidade, data, "Sala")).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var aulaDetalhadamentoDTO = new AulaDetalhadamentoDTO(null, "", "", modalidade, data, "Sala");

        var jsonEsperado = aulaDetalhadamentoDTOJson.write(
                aulaDetalhadamentoDTO
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Não deve agendar aula quando a sala estiver reservada no mesmo horário")
    @WithMockUser
    void agendar_cenario3() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var modalidade = Modalidade.SAXOFONE;
        var sala = new SalaEntity(1l, "sala", true, new ArrayList<>(), modalidade, new ArrayList<>(), true);
        sala.reservarSala(new SalaReservaDTO(1l, data));

        var aulaDTO = new AulaDTO("", "", modalidade, data, "sala");

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST))
                .when(aulaService).agendarAula(aulaDTO);

        var response = mvc
                .perform(
                        post("/aula")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(aulaDTOJson.write(
                                        new AulaDTO("", "", modalidade, data, "sala")).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}