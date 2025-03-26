package br.com.estudarte.api.infra.aula.repository;

import br.com.estudarte.api.infra.aula.AulaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AulaRepositoryJpa extends JpaRepository<AulaEntity, Long> {

    boolean existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(String professorNome, LocalDateTime data);

    boolean existsByAlunoNomeAndDataAndMotivoCancelamentoIsNull(String alunoNome, LocalDateTime data);

    Page<AulaEntity> findAllByMotivoCancelamentoIsNull(Pageable paginacao);

    Page<AulaEntity> findAllByAlunoNomeAndMotivoCancelamentoIsNull(Pageable paginacao, String alunoNome);

    Page<AulaEntity> findAllByProfessorNomeAndMotivoCancelamentoIsNull(Pageable paginacao, String professorNome);

    boolean existsByDataAndIdAndMotivoCancelamentoIsNull(LocalDateTime localDateTime, Long id);

    boolean existsByIdAndMotivoCancelamentoIsNull(Long id);

}
