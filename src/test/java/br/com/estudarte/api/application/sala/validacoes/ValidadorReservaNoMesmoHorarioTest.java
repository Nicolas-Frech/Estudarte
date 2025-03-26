package br.com.estudarte.api.application.sala.validacoes;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.application.sala.validacoes.reserva.ValidadorReservaNoMesmoHorario;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidadorReservaNoMesmoHorarioTest {

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private ValidadorReservaNoMesmoHorario validador;

    @Mock
    private SalaReservaDTO dto;

    @Test
    @DisplayName("Deveria reservar sala")
    void resevar_cenario1() {

        given(aulaRepository.existePorDataEIdEMotivoCancelamentoNull(dto.horarioReserva(), dto.idSala())).willReturn(false);
        given(salaRepository.existePorHorarioReservaEId(dto.horarioReserva(), dto.idSala())).willReturn(false);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria reservar sala com aula já marcada no mesmo horário")
    void resevar_cenario2() {

        given(aulaRepository.existePorDataEIdEMotivoCancelamentoNull(dto.horarioReserva(), dto.idSala())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria reservar sala com reserva no mesmo horario")
    void resevar_cenario3() {

        given(salaRepository.existePorHorarioReservaEId(dto.horarioReserva(), dto.idSala())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
}