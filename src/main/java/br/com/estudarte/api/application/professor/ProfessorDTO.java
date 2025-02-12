package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

public record ProfessorDTO(
        @NotNull
        String nome,
        @NotNull
        @CNPJ
        String cnpj,
        @NotNull
        String telefone,
        String email,
        @NotNull
        Modalidade modalidade) {
}
