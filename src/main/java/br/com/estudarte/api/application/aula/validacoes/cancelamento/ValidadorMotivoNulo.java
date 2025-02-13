package br.com.estudarte.api.application.aula.validacoes.cancelamento;

import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoNulo implements ValidadorCancelamentoAula {
    @Override
    public void validar(AulaCancelamentoDTO dto) {
        if(dto.motivoCancelamento() == null) {
            throw new ValidacaoException("É obrigatório informar o motivo do cancelamento!");
        }
    }
}
