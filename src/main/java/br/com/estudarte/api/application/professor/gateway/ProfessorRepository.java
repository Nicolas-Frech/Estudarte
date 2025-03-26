package br.com.estudarte.api.application.professor.gateway;

import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessorRepository {

    ProfessorEntity salvar(ProfessorEntity professor);

    boolean existePorCnpj(String cnpj);

    boolean existePorNome(String nome);

    ProfessorEntity buscarPorId(Long id);

    Page<ProfessorEntity> buscarTodosPorAtivoTrue(Pageable paginacao);

    ProfessorEntity buscarPorIdEAtivoTrue(Long id);

    boolean existePorId(Long id);

    boolean buscarAtivoByNome(String nome);

    ProfessorEntity buscarPorNome(String nome);
}
