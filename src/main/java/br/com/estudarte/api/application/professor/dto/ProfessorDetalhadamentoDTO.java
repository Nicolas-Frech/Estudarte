package br.com.estudarte.api.application.professor.dto;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.professor.ProfessorEntity;

public record ProfessorDetalhadamentoDTO(Long id, String nome, String cnpj, String telefone, String email, Modalidade modalidade) {
    public ProfessorDetalhadamentoDTO(ProfessorEntity professorRegistrado) {
        this(professorRegistrado.getId(), professorRegistrado.getNome(), professorRegistrado.getCnpj(),
                professorRegistrado.getTelefone(), professorRegistrado.getEmail(), professorRegistrado.getModalidade());
    }
}
