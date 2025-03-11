package br.com.estudarte.api.infra.sala.repository;

import br.com.estudarte.api.infra.sala.SalaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalaRepository {

    SalaEntity salvar(SalaEntity sala);

    Page<SalaEntity> buscarTodosPorAtivoTrue(Pageable paginacao);

    SalaEntity buscarPorIdEAtivoTrue(Long id);

    boolean existePorIdEAtivoTrue(Long id);

    boolean existePorNome(String nome);

    SalaEntity buscarPorId(Long id);

    SalaEntity buscarPorNome(String nome);
}
