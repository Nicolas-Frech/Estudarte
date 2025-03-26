package br.com.estudarte.api.application.aluno.gateway;

import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlunoRepository {

    AlunoEntity salvar(AlunoEntity aluno);

    boolean existePorCpf(String cpf);

    boolean existePorNome(String nome);

    AlunoEntity buscarPorId(Long id);

    Page<AlunoEntity> buscarTodosPorAtivoTrue(Pageable paginacao);

    AlunoEntity buscarPorIdEAtivoTrue(Long id);

    boolean buscarAtivoByNome(String nome);

    AlunoEntity buscarPorNome(String nome);
}
