package br.com.estudarte.api.application.aluno.validacoes.matricula;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;

public interface ValidadorMatricula {
    void validar(AlunoDTO dto);
}
