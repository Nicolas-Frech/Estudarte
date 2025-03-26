package br.com.estudarte.api.application.sala.gateway;

import br.com.estudarte.api.infra.sala.SalaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface SalaRepository {

    SalaEntity salvar(SalaEntity sala);

    Page<SalaEntity> buscarTodosPorAtivoTrue(Pageable paginacao);

    SalaEntity buscarPorIdEAtivoTrue(Long id);

    boolean existePorIdEAtivoTrue(Long id);

    boolean existePorNome(String nome);

    SalaEntity buscarPorId(Long id);

    SalaEntity buscarPorNome(String nome);

    boolean existePorHorarioReservaENome(LocalDateTime data, String nome);

    boolean existePorHorarioReservaEId(LocalDateTime data, Long id);
}
