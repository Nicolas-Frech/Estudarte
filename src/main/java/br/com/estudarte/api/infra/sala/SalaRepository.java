package br.com.estudarte.api.infra.sala;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    boolean existsByNome(String nome);

    @Query("SELECT COUNT(s) > 0 FROM SalaEntity s WHERE :horarioReserva MEMBER OF s.horariosReserva AND s.id = :id")
    boolean existsByHorarioReservaAndId(@Param("horarioReserva") LocalDateTime horarioReserva, Long id);

    @Query("SELECT COUNT(s) > 0 FROM SalaEntity s WHERE :horarioReserva MEMBER OF s.horariosReserva AND s.nome = :nomeSala")
    boolean existsByHorarioReservaAndNome(@Param("horarioReserva") LocalDateTime horarioReserva, String nomeSala);

    Page<SalaEntity> findAll(Pageable paginacao);

    SalaEntity findByNome(String nome);

}
