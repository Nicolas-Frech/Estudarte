package br.com.estudarte.api.application.sala.dto;

import br.com.estudarte.api.domain.Modalidade;
import jakarta.validation.constraints.NotNull;

public record SalaDTO(
        @NotNull
        String nome,

        @NotNull
        Modalidade modalidade) {
}
