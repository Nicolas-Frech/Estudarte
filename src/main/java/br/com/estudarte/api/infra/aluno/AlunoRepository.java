package br.com.estudarte.api.infra.aluno;

import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByNome(String alunoNome);

    AlunoEntity findByNome(String alunoNome);

    @Query("SELECT a.ativo FROM AlunoEntity a WHERE a.nome = :alunoNome")
    Boolean findAtivoByNome(String alunoNome);

    @Query("SELECT a.nome FROM AlunoEntity a WHERE a.professor = :professor")
    List<String> findAllByProfessor(ProfessorEntity professor);

    Page<AlunoEntity> findAllByAtivoTrue(Pageable paginacao);

    AlunoEntity findByIdAndAtivoTrue(Long id);
}
