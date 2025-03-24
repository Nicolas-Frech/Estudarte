package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDetalhadamentoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    public AlunoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public AlunoEntity registrarAluno(AlunoDTO dto) {
        if(alunoRepository.existePorCpf(dto.cpf())) {
            throw new ValidacaoException("CPF já registrado!");
        } else if(alunoRepository.existePorNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");
        } else {
            AlunoEntity aluno = new AlunoEntity(dto);
            alunoRepository.salvar(aluno);
            return aluno;
        }
    }

    public void cancelarMatricula(Long id) {
        AlunoEntity aluno = alunoRepository.buscarPorId(id);
        aluno.cancelarMatricula();
        alunoRepository.salvar(aluno);
    }

    public AlunoEntity atualizarInformacoes(AlunoAtualizacaoDTO dto) {
        AlunoEntity aluno = alunoRepository.buscarPorId(dto.alunoId());

        if(dto.modalidade() != null) {
            aluno.atualizarDados(dto);
        }

        if(dto.professorId() != null) {
            if(!professorRepository.existePorId(dto.professorId())) {
                throw new ValidacaoException("Não existe professor com esse ID!");
            }

            ProfessorEntity professor = professorRepository.buscarPorId(dto.professorId());

            if(professor.getModalidade() != aluno.getModalidade()) {
                throw new ValidacaoException("Professor não ministra aula desse tipo de modalidade!");
            } else {
                aluno.adicionarProfessor(professor);
                professor.setSalario(alunoRepository.buscarTodosPorProfessor(professor));
            }
        }
        alunoRepository.salvar(aluno);
        return aluno;
    }

    public Page<AlunoDetalhadamentoDTO> listarAlunos(Pageable paginacao) {
        var page = alunoRepository.buscarTodosPorAtivoTrue(paginacao).map(AlunoDetalhadamentoDTO::new);
        return page;
    }

    public AlunoEntity buscarAlunoPorId(Long id) {
        AlunoEntity aluno =  alunoRepository.buscarPorIdEAtivoTrue(id);

        if(aluno == null) {
            throw new ValidacaoException("Não existe aluno com esse ID!");
        }

        return aluno;
    }
}
