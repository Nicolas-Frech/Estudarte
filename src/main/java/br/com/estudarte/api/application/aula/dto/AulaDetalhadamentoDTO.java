package br.com.estudarte.api.application.aula.dto;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aula.AulaEntity;

import java.time.LocalDateTime;

public record AulaDetalhadamentoDTO(Long id, String professorNome, String alunoNome, Modalidade modalidade, LocalDateTime data, String salaNome) {
    public AulaDetalhadamentoDTO(AulaEntity aulaMarcada) {
        this(aulaMarcada.getId(), aulaMarcada.getProfessorNome(), aulaMarcada.getAlunoNome(), aulaMarcada.getModalidade(), aulaMarcada.getData(), aulaMarcada.getSala().getNome());
    }
}
