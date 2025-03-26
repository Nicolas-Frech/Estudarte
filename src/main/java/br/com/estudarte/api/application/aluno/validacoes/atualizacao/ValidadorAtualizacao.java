package br.com.estudarte.api.application.aluno.validacoes.atualizacao;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;

public interface ValidadorAtualizacao {

    void validar(AlunoAtualizacaoDTO dto);
}
