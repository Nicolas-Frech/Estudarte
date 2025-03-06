package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDetalhadamentoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            if(professor.getModalidade() != aluno.getModalidade()) {
                throw new ValidacaoException("Professor não ministra aula desse tipo de modalidade!");
            } else {
                aluno.adicionarProfessor(professor);
                professor.setSalario(alunoRepository.findAllByProfessor(professor));
            }
        }


        aluno.atualizarDados(dto);
        return aluno;
    }

    public Page<AlunoDetalhadamentoDTO> listarAlunos(Pageable paginacao) {
        var page = alunoRepository.findAllByAtivoTrue(paginacao).map(AlunoDetalhadamentoDTO::new);
        return page;
    }

    public AlunoEntity buscarAlunoPorId(Long id) {
        AlunoEntity aluno =  alunoRepository.findByIdAndAtivoTrue(id);

        if(aluno == null) {
            throw new ValidacaoException("Não existe aluno com esse ID!");
        }

        return aluno;
    }
}
