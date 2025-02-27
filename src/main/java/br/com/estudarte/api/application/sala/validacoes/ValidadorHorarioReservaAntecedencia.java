package br.com.estudarte.api.application.sala.validacoes;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioReservaAntecedencia implements ValidadorReservaSala {
    @Override
    public void validar(SalaReservaDTO dto) {
        var dataReserva = dto.horarioReserva();

        var agora = LocalDateTime.now();

        var diferencaEmMin = Duration.between(agora, dataReserva).toMinutes();

        if(diferencaEmMin < 60) {
            throw new ValidacaoException("Sala deve ser reservada com no mínimo 1 hora de antecedência");
        }
    }
}
