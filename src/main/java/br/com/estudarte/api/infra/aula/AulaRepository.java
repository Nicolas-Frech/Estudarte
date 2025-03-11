package br.com.estudarte.api.infra.aula;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AulaRepository {
    AulaEntity salvar(AulaEntity aula);

    boolean existePorId(Long id);

    AulaEntity buscarPorId(Long id);

    Page<AulaEntity> buscarTodosPorMotivoCancelamentoNull(Pageable paginacao);

    Page<AulaEntity> buscarTodosPorAlunoNomeEMotivoCancelamentoNull(Pageable paginacao, String alunoNome);

    Page<AulaEntity> buscarTodosPorProfessorNomeEMotivoCancelamentoNull(Pageable paginacao, String professorNome);

    AulaEntity buscarPorIdEMotivoCancelamentoNull(Long id);
}
