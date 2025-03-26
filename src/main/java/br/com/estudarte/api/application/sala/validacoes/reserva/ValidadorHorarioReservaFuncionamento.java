package br.com.estudarte.api.application.sala.validacoes.reserva;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioReservaFuncionamento implements ValidadorReservaSala {

    @Override
    public void validar(SalaReservaDTO dto) {
        var dataReserva = dto.horarioReserva();

        var domingo = dataReserva.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataReserva.getHour() < 9;
        var depoisDoFechamento = dataReserva.getHour() > 21;

        if(domingo || antesDaAbertura || depoisDoFechamento) {
            throw new ValidacaoException("Reserva fora do horário de funcionamento da escola!");
        }
    }
}
