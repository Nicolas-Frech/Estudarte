package br.com.estudarte.api.application.professor.validacoes;

import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProfessorAtivo {

    private final ProfessorRepository professorRepository;

    public ValidadorProfessorAtivo(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void validar(Long id) {
        ProfessorEntity professor = professorRepository.buscarPorIdEAtivoTrue(id);
        if(professor == null) {
            throw new ValidacaoException("Não existe professor com esse ID!");
        }
    }
}
