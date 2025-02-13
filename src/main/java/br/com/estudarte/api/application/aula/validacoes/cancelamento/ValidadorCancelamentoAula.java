package br.com.estudarte.api.application.aula.validacoes.cancelamento;

import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;

public interface ValidadorCancelamentoAula {
    void validar(AulaCancelamentoDTO dto);
}
