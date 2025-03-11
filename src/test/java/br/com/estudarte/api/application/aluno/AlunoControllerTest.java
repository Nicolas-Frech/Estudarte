package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDetalhadamentoDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AlunoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AlunoDTO> alunoDTOJson;

    @Autowired
    private JacksonTester<AlunoDetalhadamentoDTO> alunoDetalhadamentoDTOJson;

    @MockitoBean
    private AlunoRepository repository;
    @Test
    @DisplayName("Deveria devolver código 400 quando informacões estão inválidas")
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/aluno")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informacões estão válidas")
    void cadastrar_cenario2() throws Exception {
        var alunoDTO = new AlunoDTO( "Aluno", "063.888.324-09", "47996403810","aluno@email.com", Modalidade.SAXOFONE);

        when(repository.save(any())).thenReturn(new AlunoEntity(alunoDTO));

        var response = mvc.perform(post("/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoDTOJson.write(alunoDTO).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var alunoDTODetalhadamento = new AlunoDetalhadamentoDTO(
                null,
                alunoDTO.nome(),
                alunoDTO.cpf(),
                alunoDTO.telefone(),
                alunoDTO.email(),
                alunoDTO.modalidade()
        );

        var jsonEsperado = alunoDetalhadamentoDTOJson.write(alunoDTODetalhadamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}