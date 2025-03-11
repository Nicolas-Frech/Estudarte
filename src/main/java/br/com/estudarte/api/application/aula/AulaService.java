package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.application.aula.validacoes.agendamento.ValidadorAgendamentoAula;
import br.com.estudarte.api.application.aula.validacoes.cancelamento.ValidadorCancelamentoAula;
import br.com.estudarte.api.application.aula.validacoes.reagendamento.ValidadorReagendarAula;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepository;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.repository.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import br.com.estudarte.api.infra.sala.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {


    private final AulaRepository aulaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;
    private final SalaRepository salaRepository;

    @Autowired
    List<ValidadorAgendamentoAula> validadores;

    @Autowired
    List<ValidadorCancelamentoAula> validadoresCancelamento;

    @Autowired
    List<ValidadorReagendarAula> validadoresReagendamento;

    public AulaService(AulaRepository aulaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository, SalaRepository salaRepository) {
        this.aulaRepository = aulaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.salaRepository = salaRepository;
    }

    public AulaEntity agendarAula(AulaDTO dto) {
        if(!alunoRepository.existePorNome(dto.alunoNome())) {
            throw new ValidacaoException("Não existe aluno com esse nome!");
        }

        if(!professorRepository.existePorNome(dto.professorNome())) {
            throw new ValidacaoException("Não existe professor com esse nome!");
        }

        if(!salaRepository.existePorNome(dto.salaNome())) {
            throw new ValidacaoException("Não existe sala com esse nome!");
        }

        validadores.forEach(v -> v.validar(dto));

        AulaEntity aula = new AulaEntity(dto);
        aula.adicionarSala(salaRepository.buscarPorNome(dto.salaNome()));

        aulaRepository.salvar(aula);
        return aula;
    }

    public void cancelarAula(AulaCancelamentoDTO dto) {
        if(!aulaRepository.existePorId(dto.id())) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dto));
        
        AulaEntity aulaCancelada = aulaRepository.buscarPorId(dto.id());
        aulaCancelada.cancelarAula(dto.motivoCancelamento());
    }

    public AulaEntity reagendarAula(AulaAtualizacaoDTO dto) {
        if(!aulaRepository.existePorId(dto.aulaId())) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        validadoresReagendamento.forEach(v -> v.validar(dto));

        AulaEntity aulaReagendada = aulaRepository.buscarPorId(dto.aulaId());
        aulaReagendada.remarcarAula(dto.data());

        return aulaReagendada;
    }

    public Page<AulaDetalhadamentoDTO> listarAulas(Pageable paginacao) {
        var page = aulaRepository.buscarTodosPorMotivoCancelamentoNull(paginacao).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public Page<AulaDetalhadamentoDTO> listarAulasPorAluno(String alunoNome, Pageable paginacao) {
        var page = aulaRepository.buscarTodosPorAlunoNomeEMotivoCancelamentoNull(paginacao, alunoNome).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public Page<AulaDetalhadamentoDTO> listarAulasPorProfessor(String professorNome, Pageable paginacao) {
        var page = aulaRepository.buscarTodosPorProfessorNomeEMotivoCancelamentoNull(paginacao, professorNome).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public AulaEntity buscarAulaPorId(Long id) {
        AulaEntity aula = aulaRepository.buscarPorIdEMotivoCancelamentoNull(id);

        if(aula == null) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        return aula;
    }
}
