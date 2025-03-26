package br.com.estudarte.api.application.aula.gateway;

import br.com.estudarte.api.infra.aula.AulaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AulaRepository {
    AulaEntity salvar(AulaEntity aula);

    AulaEntity buscarPorId(Long id);

    Page<AulaEntity> buscarTodosPorMotivoCancelamentoNull(Pageable paginacao);

    Page<AulaEntity> buscarTodosPorAlunoNomeEMotivoCancelamentoNull(Pageable paginacao, String alunoNome);

    Page<AulaEntity> buscarTodosPorProfessorNomeEMotivoCancelamentoNull(Pageable paginacao, String professorNome);

    boolean existePorProfessorNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data);

    boolean existePorAlunoNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data);

    boolean existePorDataEIdEMotivoCancelamentoNull(LocalDateTime data, Long id);

    boolean existePorIdEMotivoCancelamentoNull(Long id);
}
