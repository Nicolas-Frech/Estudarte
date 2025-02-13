package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

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

    public AlunoEntity atualizarInformacoes(AlunoAtualizacaoDTO dto) {
        AlunoEntity aluno = alunoRepository.getReferenceById(dto.alunoId());

        if(professorRepository.existsById(dto.professorId())) {
            ProfessorEntity professor = professorRepository.getReferenceById(dto.professorId());
            aluno.adicionarProfessor(professor);
        }

        if(dto.modalidade() != null) {
            aluno.atualizarModalidade(dto.modalidade());
        }

        return aluno;
    }
}
