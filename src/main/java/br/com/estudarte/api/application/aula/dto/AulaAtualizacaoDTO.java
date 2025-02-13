package br.com.estudarte.api.application.aula.dto;

import java.time.LocalDateTime;

public record AulaAtualizacaoDTO(Long aulaId, LocalDateTime data) {
}
