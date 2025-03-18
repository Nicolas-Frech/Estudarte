package br.com.estudarte.api.infra;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.sala.SalaEntity;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class TestPersistenceHelper {

    private final TestEntityManager em;

    public TestPersistenceHelper(TestEntityManager em) {
        this.em = em;
    }

    public SalaEntity cadastrarSala(SalaDTO dto) {
        SalaEntity sala = new SalaEntity(dto);
        this.em.persist(sala);

        return sala;
    }

    public AulaEntity marcarAula(AulaDTO dto) {
        AulaEntity aula = new AulaEntity(dto);
        this.em.persist(aula);

        return aula;
    }

    public ProfessorEntity cadastrarProfessor(ProfessorDTO dto) {
        ProfessorEntity professor = new ProfessorEntity(dto);
        this.em.persist(professor);

        return professor;
    }

    public AlunoEntity matricularAluno(AlunoDTO dto) {
        AlunoEntity aluno = new AlunoEntity(dto);
        this.em.persist(aluno);

        return aluno;
    }


}
