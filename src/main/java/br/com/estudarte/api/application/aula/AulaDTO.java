package br.com.estudarte.api.application.aula;


import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AulaDTO(
        @NotNull
        String professorNome,
        @NotNull
        String alunoNome,
        @NotNull
        Modalidade modalidade,
        @NotNull
        LocalDateTime data) {
}
