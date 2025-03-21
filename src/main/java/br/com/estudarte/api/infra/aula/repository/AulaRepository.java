package br.com.estudarte.api.infra.aula.repository;

import br.com.estudarte.api.infra.aula.AulaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AulaRepository {
    AulaEntity salvar(AulaEntity aula);

    boolean existePorId(Long id);

    AulaEntity buscarPorId(Long id);

    Page<AulaEntity> buscarTodosPorMotivoCancelamentoNull(Pageable paginacao);

    Page<AulaEntity> buscarTodosPorAlunoNomeEMotivoCancelamentoNull(Pageable paginacao, String alunoNome);

    Page<AulaEntity> buscarTodosPorProfessorNomeEMotivoCancelamentoNull(Pageable paginacao, String professorNome);

    AulaEntity buscarPorIdEMotivoCancelamentoNull(Long id);

    boolean existePorProfessorNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data);

    boolean existePorAlunoNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data);

    boolean existePorDataEIdEMotivoCancelamentoNull(LocalDateTime data, Long id);
}
