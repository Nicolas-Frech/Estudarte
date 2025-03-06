package br.com.estudarte.api.application.professor.dto;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTOAtualizacao(
        @NotNull
        Long idProfessor,
        Modalidade modalidade,
        Double salario,
        String telefone,
        String email) {
}
