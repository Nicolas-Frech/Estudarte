package br.com.estudarte.api.application.aluno.dto;

import br.com.estudarte.api.domain.Modalidade;

public record AlunoAtualizacaoDTO(Long alunoId, Long professorId, Modalidade modalidade) {
}
