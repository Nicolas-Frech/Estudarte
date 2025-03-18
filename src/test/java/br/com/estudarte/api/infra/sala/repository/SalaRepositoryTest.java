package br.com.estudarte.api.infra.sala.repository;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.sala.SalaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class SalaRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private SalaRepositoryJpa repository;

    @Test
    @DisplayName("Deveria retornar true se existe sala no banco por reserva e id")
    void buscarSala_cenario1() {
        SalaDTO salaTeste = new SalaDTO("Sala", Modalidade.SAXOFONE);
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 3, 3, 9, 0, 0);


        SalaEntity sala = cadastrarSala(salaTeste);
        SalaReservaDTO reserva = new SalaReservaDTO((Long) em.getId(sala), horarioReserva);

        sala.reservarSala(reserva);

        var existeSalaDoBanco = repository.existsByHorarioReservaAndId(horarioReserva, (Long) em.getId(sala));

        assertThat(existeSalaDoBanco).isTrue();
    }

    @Test
    @DisplayName("Deveria retornar false se não existe sala no banco por reserva e id")
    void buscarSala_cenario2() {
        SalaDTO salaTeste = new SalaDTO("Sala", Modalidade.SAXOFONE);
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 3, 3, 9, 0, 0);


        SalaEntity sala = cadastrarSala(salaTeste);
        SalaReservaDTO reserva = new SalaReservaDTO((Long) em.getId(sala), horarioReserva);

        var existeSalaDoBanco = repository.existsByHorarioReservaAndId(horarioReserva, (Long) em.getId(sala));

        assertThat(existeSalaDoBanco).isFalse();
    }

    @Test
    @DisplayName("Deveria retornar true se existe sala no banco por reserva e nome")
    void buscarSala_cenario3() {
        SalaDTO salaTeste = new SalaDTO("Sala", Modalidade.SAXOFONE);
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 3, 3, 9, 0, 0);


        SalaEntity sala = cadastrarSala(salaTeste);
        SalaReservaDTO reserva = new SalaReservaDTO((Long) em.getId(sala), horarioReserva);

        sala.reservarSala(reserva);

        var existeSalaDoBanco = repository.existsByHorarioReservaAndNome(horarioReserva, salaTeste.nome());

        assertThat(existeSalaDoBanco).isTrue();
    }

    @Test
    @DisplayName("Deveria retornar false se não existe sala no banco por reserva e nome")
    void buscarSala_cenario4() {
        SalaDTO salaTeste = new SalaDTO("Sala", Modalidade.SAXOFONE);
        LocalDateTime horarioReserva = LocalDateTime.of(2025, 3, 3, 9, 0, 0);


        SalaEntity sala = cadastrarSala(salaTeste);
        SalaReservaDTO reserva = new SalaReservaDTO((Long) em.getId(sala), horarioReserva);

        var existeSalaDoBanco = repository.existsByHorarioReservaAndNome(horarioReserva, salaTeste.nome());

        assertThat(existeSalaDoBanco).isFalse();
    }

    private SalaEntity cadastrarSala(SalaDTO dto) {
        SalaEntity sala = new SalaEntity(dto);
        this.em.persist(sala);

        return sala;
    }
}