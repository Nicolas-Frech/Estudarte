package br.com.estudarte.api.application.sala.validacoes.reserva;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ValidadorHorarioReservaAntecedencia implements ValidadorReservaSala {
    @Override
    public void validar(SalaReservaDTO dto) {
        var dataReserva = dto.horarioReserva();

        var agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        var diferencaEmMin = Duration.between(agora, dataReserva).toMinutes();

        if(diferencaEmMin < 60) {
            throw new ValidacaoException("Sala deve ser reservada com no mínimo 1 hora de antecedência!");
        }
    }
}
