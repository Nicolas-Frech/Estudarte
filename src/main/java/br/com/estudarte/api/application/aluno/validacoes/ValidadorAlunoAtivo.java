package br.com.estudarte.api.application.aluno.validacoes;

import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlunoAtivo {

    private final AlunoRepository alunoRepository;

    public ValidadorAlunoAtivo(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void validar(Long id) {
        AlunoEntity aluno = alunoRepository.buscarPorIdEAtivoTrue(id);
        if(aluno == null) {
            throw new ValidacaoException("Não existe aluno com esse ID!");
        }
    }
}
