package br.com.estudarte.api.application.aluno.dto;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aluno.AlunoEntity;


public record AlunoDetalhadamentoDTO(Long id, String nome, String cpf, String telefone, String email, Modalidade modalidade) {
    public AlunoDetalhadamentoDTO(AlunoEntity aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getTelefone(), aluno.getEmail(), aluno.getModalidade());
    }
}
