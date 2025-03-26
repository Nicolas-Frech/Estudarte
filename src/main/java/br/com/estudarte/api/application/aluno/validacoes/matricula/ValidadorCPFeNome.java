package br.com.estudarte.api.application.aluno.validacoes.matricula;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCPFeNome implements ValidadorMatricula {

    private final AlunoRepository alunoRepository;

    public ValidadorCPFeNome(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void validar(AlunoDTO dto) {
        if (alunoRepository.existePorCpf(dto.cpf())) {
            throw new ValidacaoException("CPF já registrado!");
        }
        if (alunoRepository.existePorNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");
        }
    }
}

