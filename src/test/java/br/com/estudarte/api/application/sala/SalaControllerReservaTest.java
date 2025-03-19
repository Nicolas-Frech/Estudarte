package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.sala.SalaEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class SalaControllerReservaTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<SalaDetalhadamentoDTO> salaDetalhadamentoDTOJson;

    @MockitoBean
    private SalaService salaService;

    @Autowired
    JacksonTester<SalaReservaDTO> salaReservaDTOJson;

    @Test
    @DisplayName("Não deve reservar sala fora do horário de funcionamento")
    @WithMockUser
    void reservar_cenario1() throws Exception {
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 03, 25, 6, 0, 0);
        SalaReservaDTO reserva = new SalaReservaDTO(1l, horarioReserva);
        SalaEntity sala = new SalaEntity(new SalaDTO("Sala", Modalidade.SAXOFONE));

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reserva fora do horário de funcionamento da escola!"))
                .when(salaService).reservarSala(reserva);

        var response = mvc.perform(put("/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salaReservaDTOJson.write(reserva).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Não deve reservar sala quando o horário já estiver reservado")
    @WithMockUser
    void reservar_cenario2() throws Exception {
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 03, 25, 14, 0, 0);
        SalaReservaDTO reserva = new SalaReservaDTO(2l, horarioReserva);
        SalaEntity sala = new SalaEntity(2l, "Sala", true, new ArrayList<>(), Modalidade.SAXOFONE, new ArrayList<>(), true);
        sala.reservarSala(reserva);

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma reserva para esse horário nesta sala!"))
                .when(salaService).reservarSala(reserva);

        var response = mvc.perform(put("/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salaReservaDTOJson.write(reserva).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
    @Test
    @DisplayName("Não deve reservar sala quando tem aula no mesmo horário")
    void reservar_cenario3() throws Exception {
        LocalDateTime horario = LocalDateTime.of(2025, 03, 25, 14, 0, 0);
        SalaEntity sala = new SalaEntity(2l, "Sala", true, new ArrayList<>(), Modalidade.SAXOFONE, new ArrayList<>(), true);
        AulaEntity aula = new AulaEntity(new AulaDTO("Professor", "Aluno", Modalidade.SAXOFONE, horario, "Sala"));
        aula.adicionarSala(sala);

        SalaReservaDTO reserva = new SalaReservaDTO(2l, horario);

        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma aula marcada para esse horário nesta sala!"))
                .when(salaService).reservarSala(reserva);

        var response = mvc.perform(put("/sala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salaReservaDTOJson.write(reserva).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
