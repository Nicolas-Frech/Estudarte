package br.com.estudarte.api.application.aula.dto;


import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.sala.SalaEntity;
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
        LocalDateTime data,

        @NotNull
        String salaNome) {
}
