package br.com.estudarte.api.infra.sala;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    boolean existsByNome(String nome);

    boolean existsByHorarioReserva(LocalDateTime horarioReserva);
}
