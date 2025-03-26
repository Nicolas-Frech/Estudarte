package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDetalhadamentoDTO;
import br.com.estudarte.api.application.aluno.validacoes.ValidadorAlunoAtivo;
import br.com.estudarte.api.application.aluno.validacoes.atualizacao.ValidadorAtualizacao;
import br.com.estudarte.api.application.aluno.validacoes.matricula.ValidadorMatricula;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    @Autowired
    private List<ValidadorMatricula> validadorMatricula;

    @Autowired
    private List<ValidadorAtualizacao> validadorAtualizacao;

    @Autowired
    private ValidadorAlunoAtivo validadorAlunoAtivo;

    public AlunoService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public AlunoEntity registrarAluno(AlunoDTO dto) {
        validadorMatricula.forEach(v -> v.validar(dto));

        AlunoEntity aluno = new AlunoEntity(dto);
        alunoRepository.salvar(aluno);
        return aluno;

    }

    public void cancelarMatricula(Long id) {
        validadorAlunoAtivo.validar(id);

        AlunoEntity aluno = alunoRepository.buscarPorId(id);
        aluno.cancelarMatricula();
        alunoRepository.salvar(aluno);
    }

    public AlunoEntity atualizarInformacoes(AlunoAtualizacaoDTO dto) {
        validadorAlunoAtivo.validar(dto.alunoId());

        AlunoEntity aluno = alunoRepository.buscarPorId(dto.alunoId());

        aluno.atualizarDados(dto);

        if(dto.professorId() != null) {
            validadorAtualizacao.forEach(v -> v.validar(dto));
            ProfessorEntity professor = professorRepository.buscarPorId(dto.professorId());
            aluno.adicionarProfessor(professor);
        }

        alunoRepository.salvar(aluno);
        return aluno;
    }

    public Page<AlunoDetalhadamentoDTO> listarAlunos(Pageable paginacao) {
        var page = alunoRepository.buscarTodosPorAtivoTrue(paginacao).map(AlunoDetalhadamentoDTO::new);
        return page;
    }

    public AlunoEntity buscarAlunoPorId(Long id) {
        validadorAlunoAtivo.validar(id);
        AlunoEntity aluno =  alunoRepository.buscarPorIdEAtivoTrue(id);
        return aluno;
    }
}
