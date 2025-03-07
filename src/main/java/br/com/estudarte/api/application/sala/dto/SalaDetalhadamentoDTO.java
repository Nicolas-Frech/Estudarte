package br.com.estudarte.api.application.sala.dto;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.sala.SalaEntity;

import java.time.LocalDateTime;
import java.util.List;

public record SalaDetalhadamentoDTO(Long id, String nome, Modalidade modalidade, Boolean reservada, List<LocalDateTime> horariosReserva) {
    public SalaDetalhadamentoDTO(SalaEntity entity) {
        this(entity.getId(), entity.getNome(), entity.getModalidade(), entity.getReservada(), entity.getHorariosReserva());
    }
}
