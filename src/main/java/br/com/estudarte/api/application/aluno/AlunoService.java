package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoEntity registrarAluno(AlunoDTO dto) {
        if(alunoRepository.existsByCpf(dto.cpf())) {
            throw new ValidacaoException("CPF já registrado!");
        } else if(alunoRepository.existsByNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");
        } else {
            AlunoEntity aluno = new AlunoEntity(dto);
            alunoRepository.save(aluno);
            return aluno;
        }
    }

    public void cancelarMatricula(Long id) {
        AlunoEntity aluno = alunoRepository.getReferenceById(id);
        aluno.cancelarMatricula();
    }
}
