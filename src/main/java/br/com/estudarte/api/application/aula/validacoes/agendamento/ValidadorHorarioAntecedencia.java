package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoAula {
    @Override
    public void validar(AulaDTO dto) {
        var dataAula = dto.data();

        var agora = LocalDateTime.now();

        var diferencaEmMin = Duration.between(agora, dataAula).toMinutes();

        if(diferencaEmMin < 60) {
            throw new ValidacaoException("Aula deve ser agendada com no mínimo 1 hora de antecedência");
        }
    }
}
