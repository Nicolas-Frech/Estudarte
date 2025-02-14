package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorReagendarAula {
    @Override
    public void validar(AulaAtualizacaoDTO dto) {
        var dataAula = dto.data();

        var domingo = dataAula.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataAula.getHour() < 9;
        var depoisDoFechamento = dataAula.getHour() > 21;

        if(domingo || antesDaAbertura || depoisDoFechamento) {
            throw new ValidacaoException("Aula fora do horário de funcionamento da escola!");
        }
    }
}
