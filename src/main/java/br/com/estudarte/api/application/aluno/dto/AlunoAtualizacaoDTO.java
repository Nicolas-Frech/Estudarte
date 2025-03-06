package br.com.estudarte.api.application.aluno.dto;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;

public record AlunoAtualizacaoDTO(
        @NotNull
        Long alunoId,
        Long professorId,
        Modalidade modalidade,
        String telefone,
        String email) {
}
