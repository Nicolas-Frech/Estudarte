package br.com.estudarte.api.application.professor.dto;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfessorDTO(
        @NotNull
        String nome,
        @NotNull
        @Pattern(regexp = "^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$")
        String cnpj,
        @NotNull
        String telefone,
        String email,
        @NotNull
        Modalidade modalidade) {
}
