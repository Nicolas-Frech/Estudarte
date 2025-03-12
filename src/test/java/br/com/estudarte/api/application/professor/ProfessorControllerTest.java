package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ProfessorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ProfessorDTO> professorDTOJson;

    @Autowired
    private JacksonTester<ProfessorDetalhadamentoDTO> professorDetalhadamentoDTOJson;

    @MockitoBean
    private ProfessorRepository repository;

    @Test
    @DisplayName("Deveria devolver código 400 quando informacões estão inválidas")
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/professor")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informacões estão válidas")
    void cadastrar_cenario2() throws Exception {
        List<String> alunos = new ArrayList<String>();
        var professorDTO = new ProfessorDTO( "Professor", "43.743.281/0001-68", "47996403810","professor@email.com", Modalidade.SAXOFONE);

        when(repository.salvar(any())).thenReturn(new ProfessorEntity(professorDTO));

        var response = mvc.perform(post("/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(professorDTOJson.write(professorDTO).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var professorDTODetalhadamento = new ProfessorDetalhadamentoDTO(
                null,
                professorDTO.nome(),
                professorDTO.cnpj(),
                professorDTO.telefone(),
                professorDTO.email(),
                professorDTO.modalidade(),
                alunos,
                 null
        );

        var jsonEsperado = professorDetalhadamentoDTOJson.write(professorDTODetalhadamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}