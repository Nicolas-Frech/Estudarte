package br.com.estudarte.api.application.sala.dto;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.sala.SalaEntity;

import java.time.LocalDateTime;

public record SalaDetalhadamentoDTO(Long id, String nome, Modalidade modalidade, Boolean reservada, LocalDateTime horarioReserva) {
    public SalaDetalhadamentoDTO(SalaEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getModalidade(), entity.getReservada(), entity.getHorarioReserva());
    }
}
