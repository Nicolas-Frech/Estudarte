package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;

public interface ValidadorReagendarAula {
    void validar(AulaAtualizacaoDTO dto);
}
