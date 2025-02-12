package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record AlunoDTO(
        @NotBlank
        String nome,
        @NotNull
        @CPF
        String cpf,
        @NotNull
        String telefone,
        String email,
        @NotNull
        Modalidade modalidade) {
}
