package br.com.estudarte.api.application.professor.dto;

import br.com.estudarte.api.domain.Modalidade;

public record ProfessorDTOAtualizacao(Long idProfessor, Modalidade modalidade, Double salario) {
}
