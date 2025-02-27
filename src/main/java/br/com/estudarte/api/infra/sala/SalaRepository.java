package br.com.estudarte.api.infra.sala;

import br.com.estudarte.api.infra.aula.AulaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    boolean existsByNome(String nome);

    boolean existsByHorarioReserva(LocalDateTime horarioReserva);

    Page<SalaEntity> findAll(Pageable paginacao);
}
