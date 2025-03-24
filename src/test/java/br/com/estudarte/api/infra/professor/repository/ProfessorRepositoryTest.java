package br.com.estudarte.api.infra.professor.repository;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.TestConfig;
import br.com.estudarte.api.infra.TestPersistenceHelper;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProfessorRepositoryTest {

    @Autowired
    private TestPersistenceHelper helper;

    @Autowired
    private ProfessorRepositoryJpa repository;

    @Test
    @DisplayName("Deveria retornar professor do banco a partir do nome")
    void buscarProfessor_cenario1() {
        ProfessorDTO professorTeste = new ProfessorDTO("Prof", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);

        ProfessorEntity professor = helper.cadastrarProfessor(professorTeste);

        var professorDoBanco = repository.findByNome(professorTeste.nome());

        assertThat(professorDoBanco).isEqualTo(professor);
    }

    @Test
    @DisplayName("Não deveria retornar professor do banco a partir do nome")
    void buscarProfessor_cenario2() {
        ProfessorDTO professorTeste = new ProfessorDTO("Prof", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);

        var professorDoBanco = repository.findByNome(professorTeste.nome());

        assertThat(professorDoBanco).isNull();
    }

    @Test
    @DisplayName("Deveria retornar ativo do banco a partir do nome")
    void buscarProfessor_cenario3() {
        ProfessorDTO professorTeste = new ProfessorDTO("Prof", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);

        ProfessorEntity professor = helper.cadastrarProfessor(professorTeste);

        var ativo = repository.findAtivoByNome(professorTeste.nome());

        assertThat(ativo).isTrue();
    }

    @Test
    @DisplayName("Não deveria retornar ativo do banco a partir do nome")
    void buscarProfessor_cenario4() {
        ProfessorDTO professorTeste = new ProfessorDTO("Prof", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);

        var ativo = repository.findAtivoByNome(professorTeste.nome());

        assertThat(ativo).isNull();
    }
}