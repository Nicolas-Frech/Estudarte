package br.com.estudarte.api.domain.aula;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.sala.Sala;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AulaTest {

    @Test
    public void naoDeveMarcarAulaSemAntecedenciaDeNoMin1Hora() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aula("momo", "momomo", Modalidade.BAIXO, LocalDateTime.now(), new Sala("Sala 01", Modalidade.BAIXO)));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aula("momo", "momomo", Modalidade.BAIXO, LocalDateTime.now().minusHours(1), new Sala("Sala 01", Modalidade.BAIXO)));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Aula("momo", "momomo", Modalidade.BAIXO, LocalDateTime.now().plusMinutes(59), new Sala("Sala 01", Modalidade.BAIXO)));
    }

}