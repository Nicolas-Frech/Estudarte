package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;

public interface ValidadorAgendamentoAula {
    void validar(AulaDTO dto);
}
