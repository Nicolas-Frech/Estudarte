package br.com.estudarte.api.application.sala.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SalaReservaDTO(
        @NotNull
        Long idSala,
        @NotNull
        LocalDateTime horarioReserva) {
}
