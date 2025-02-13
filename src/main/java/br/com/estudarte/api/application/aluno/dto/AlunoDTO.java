package br.com.estudarte.api.application.aluno.dto;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AlunoDTO(
        @NotBlank
        String nome,

        @NotNull
        @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @NotNull
        String telefone,
        String email,
        @NotNull
        Modalidade modalidade) {
}
