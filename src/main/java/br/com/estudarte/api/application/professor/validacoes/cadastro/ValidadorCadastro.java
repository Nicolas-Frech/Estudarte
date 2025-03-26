package br.com.estudarte.api.application.professor.validacoes.cadastro;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;

public interface ValidadorCadastro {
    void validar(ProfessorDTO dto);
}
