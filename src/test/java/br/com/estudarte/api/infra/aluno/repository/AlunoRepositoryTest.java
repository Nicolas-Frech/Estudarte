package br.com.estudarte.api.infra.aluno.repository;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AlunoRepositoryJpa alunoRepository;

    @Test
    @DisplayName("Deveria retornar aluno do banco pelo nome")
    void buscarAluno_cenario1() {
        AlunoDTO alunoTeste = new AlunoDTO("Aluno", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        AlunoEntity aluno = matricularAluno(alunoTeste);

        var alunoDoBanco = alunoRepository.findByNome(alunoTeste.nome());

        assertThat(alunoDoBanco).isEqualTo(aluno);
    }

    @Test
    @DisplayName("Não deveria retornar aluno do banco pelo nome")
    void buscarAluno_cenario2() {
        AlunoDTO alunoTeste = new AlunoDTO("Aluno", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        var alunoDoBanco = alunoRepository.findByNome(alunoTeste.nome());

        assertThat(alunoDoBanco).isNull();
    }

    @Test
    @DisplayName("Deveria retornar aluno do banco pelo professor")
    void buscarAluno_cenario3() {
        AlunoDTO alunoTeste = new AlunoDTO("Aluno", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        AlunoEntity aluno = matricularAluno(alunoTeste);

        ProfessorDTO professorTeste = new ProfessorDTO("Professor", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);
        ProfessorEntity professor = cadastrarProfessor(professorTeste);

        aluno.adicionarProfessor(professor);

        var alunoDoBanco = alunoRepository.findAllByProfessor(professor);

        assertThat(alunoDoBanco).contains(aluno.getNome());
    }

    @Test
    @DisplayName("Não deveria retornar aluno do banco pelo professor")
    void buscarAluno_cenario4() {
        AlunoDTO alunoTeste = new AlunoDTO("Aluno", "123.456.789-00", "123456789", "email@email.com",
                Modalidade.SAXOFONE);

        AlunoEntity aluno = matricularAluno(alunoTeste);

        ProfessorDTO professorTeste = new ProfessorDTO("Professor", "12.343.123/0001-39", "12345678", "email@email.com",
                Modalidade.SAXOFONE);
        ProfessorEntity professor = cadastrarProfessor(professorTeste);

        var alunoDoBanco = alunoRepository.findAllByProfessor(professor);

        assertThat(alunoDoBanco).doesNotContain(aluno.getNome());
    }




    private AlunoEntity matricularAluno(AlunoDTO dto) {
        AlunoEntity aluno = new AlunoEntity(dto);
        this.em.persist(aluno);

        return aluno;
    }

    private ProfessorEntity cadastrarProfessor(ProfessorDTO dto) {
        ProfessorEntity professor = new ProfessorEntity(dto);
        this.em.persist(professor);

        return professor;
    }
}