package br.com.estudarte.api.application.professor.validacoes.cadastro;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCNPJeNome implements ValidadorCadastro {

    private final ProfessorRepository professorRepository;

    public ValidadorCNPJeNome(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public void validar(ProfessorDTO dto) {
        if (professorRepository.existePorCnpj(dto.cnpj())) {
            throw new ValidacaoException("CNPJ já registrado!");
        }
        if (professorRepository.existePorNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");
        }
    }
}
