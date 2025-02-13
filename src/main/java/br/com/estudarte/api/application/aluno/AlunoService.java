package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoEntity registrarAluno(AlunoDTO dto) {
        if(alunoRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já registrado!");
        } else {
            AlunoEntity aluno = new AlunoEntity(dto);
            alunoRepository.save(aluno);
            return aluno;
        }
    }

}
