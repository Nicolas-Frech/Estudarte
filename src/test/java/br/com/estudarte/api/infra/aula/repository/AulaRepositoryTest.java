package br.com.estudarte.api.infra.aula.repository;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AulaRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AulaRepositoryJpa repository;

    @Test
    @DisplayName("Deveria retornar lista de aulas pelo nome do professor")
    void buscarAula_cenario1() {
        AulaDTO aulaTeste = new AulaDTO("Professor", "Aluno", Modalidade.SAXOFONE,
                LocalDateTime.of(2025, 3, 3, 9, 0, 0), "Sala");

        ProfessorDTO professorTeste = new ProfessorDTO("Professor", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);
        ProfessorEntity professor = cadastrarProfessor(professorTeste);

        AulaEntity aula = marcarAula(aulaTeste);

        var aulasProfessor = repository.findAllByProfessorNomeAndMotivoCancelamentoIsNull(null, professorTeste.nome());

        assertThat(aulasProfessor).contains(aula);
    }

    @Test
    @DisplayName("Não deveria retornar lista de aulas pelo nome do professor")
    void buscarAula_cenario2() {
        AulaDTO aulaTeste = new AulaDTO("Professor", "Aluno", Modalidade.SAXOFONE,
                LocalDateTime.of(2025, 3, 3, 9, 0, 0), "Sala");

        ProfessorDTO professorTeste = new ProfessorDTO("Professor 2", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);
        ProfessorEntity professor = cadastrarProfessor(professorTeste);

        AulaEntity aula = marcarAula(aulaTeste);

        var aulasProfessor = repository.findAllByProfessorNomeAndMotivoCancelamentoIsNull(null, professorTeste.nome());

        assertThat(aulasProfessor).doesNotContain(aula);
    }

    @Test
    @DisplayName("Deveria retornar lista de aulas pelo nome do aluno")
    void buscarAula_cenario3() {
        AulaDTO aulaTeste = new AulaDTO("Professor", "Aluno", Modalidade.SAXOFONE,
                LocalDateTime.of(2025, 3, 3, 9, 0, 0), "Sala");

        AlunoDTO alunoTeste = new AlunoDTO("Aluno", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        AlunoEntity aluno = matricularAluno(alunoTeste);

        AulaEntity aula = marcarAula(aulaTeste);

        var aulasProfessor = repository.findAllByAlunoNomeAndMotivoCancelamentoIsNull(null, alunoTeste.nome());

        assertThat(aulasProfessor).contains(aula);
    }

    @Test
    @DisplayName("Não deveria retornar lista de aulas pelo nome do aluno")
    void buscarAula_cenario4() {
        AulaDTO aulaTeste = new AulaDTO("Professor", "Aluno", Modalidade.SAXOFONE,
                LocalDateTime.of(2025, 3, 3, 9, 0, 0), "Sala");

        AlunoDTO alunoTeste = new AlunoDTO("Aluno 2", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        AlunoEntity aluno = matricularAluno(alunoTeste);

        AulaEntity aula = marcarAula(aulaTeste);

        var aulasProfessor = repository.findAllByAlunoNomeAndMotivoCancelamentoIsNull(null, alunoTeste.nome());

        assertThat(aulasProfessor).doesNotContain(aula);
    }


    private AulaEntity marcarAula(AulaDTO dto) {
        AulaEntity aula = new AulaEntity(dto);
        this.em.persist(aula);

        return aula;
    }

    private ProfessorEntity cadastrarProfessor(ProfessorDTO dto) {
        ProfessorEntity professor = new ProfessorEntity(dto);
        this.em.persist(professor);

        return professor;
    }

    private AlunoEntity matricularAluno(AlunoDTO dto) {
        AlunoEntity aluno = new AlunoEntity(dto);
        this.em.persist(aluno);

        return aluno;
    }

}