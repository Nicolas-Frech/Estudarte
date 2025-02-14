package br.com.estudarte.api.infra.aula;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AulaRepository extends JpaRepository<AulaEntity, Long> {
    Boolean existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(String professorNome, LocalDateTime data);

    Boolean existsByAlunoNomeAndDataAndMotivoCancelamentoIsNull(String alunoNome, LocalDateTime data);

    Page<AulaEntity> findAllByMotivoCancelamentoIsNull(Pageable paginacao);

    Page<AulaEntity> findAllByAlunoNomeAndMotivoCancelamentoIsNull(Pageable paginacao, String alunoNome);

    Page<AulaEntity> findAllByProfessorNomeAndMotivoCancelamentoIsNull(Pageable paginacao, String professorNome);
}
