package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.application.aula.validacoes.agendamento.ValidadorAgendamentoAula;
import br.com.estudarte.api.application.aula.validacoes.cancelamento.ValidadorCancelamentoAula;
import br.com.estudarte.api.application.aula.validacoes.reagendamento.ValidadorReagendarAula;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepositoryJpa;
import br.com.estudarte.api.infra.sala.repository.SalaRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    ProfessorRepositoryJpa professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    SalaRepositoryJpa salaRepositoryJpa;

    @Autowired
    List<ValidadorAgendamentoAula> validadores;

    @Autowired
    List<ValidadorCancelamentoAula> validadoresCancelamento;

    @Autowired
    List<ValidadorReagendarAula> validadoresReagendamento;

    public AulaEntity agendarAula(AulaDTO dto) {
        if(!alunoRepository.existsByNome(dto.alunoNome())) {
            throw new ValidacaoException("Não existe aluno com esse nome!");
        }

        if(!professorRepository.existsByNome(dto.professorNome())) {
            throw new ValidacaoException("Não existe professor com esse nome!");
        }

        if(!salaRepositoryJpa.existsByNome(dto.salaNome())) {
            throw new ValidacaoException("Não existe sala com esse nome!");
        }

        validadores.forEach(v -> v.validar(dto));

        AulaEntity aula = new AulaEntity(dto);
        aula.adicionarSala(salaRepositoryJpa.findByNome(dto.salaNome()));

        aulaRepository.save(aula);
        return aula;
    }

    public void cancelarAula(AulaCancelamentoDTO dto) {
        if(!aulaRepository.existsById(dto.id())) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dto));
        
        AulaEntity aulaCancelada = aulaRepository.getReferenceById(dto.id());
        aulaCancelada.cancelarAula(dto.motivoCancelamento());
    }

    public AulaEntity reagendarAula(AulaAtualizacaoDTO dto) {
        if(!aulaRepository.existsById(dto.aulaId())) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        validadoresReagendamento.forEach(v -> v.validar(dto));

        AulaEntity aulaReagendada = aulaRepository.getReferenceById(dto.aulaId());
        aulaReagendada.remarcarAula(dto.data());

        return aulaReagendada;
    }

    public Page<AulaDetalhadamentoDTO> listarAulas(Pageable paginacao) {
        var page = aulaRepository.findAllByMotivoCancelamentoIsNull(paginacao).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public Page<AulaDetalhadamentoDTO> listarAulasPorAluno(String alunoNome, Pageable paginacao) {
        var page = aulaRepository.findAllByAlunoNomeAndMotivoCancelamentoIsNull(paginacao, alunoNome).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public Page<AulaDetalhadamentoDTO> listarAulasPorProfessor(String professorNome, Pageable paginacao) {
        var page = aulaRepository.findAllByProfessorNomeAndMotivoCancelamentoIsNull(paginacao, professorNome).map(AulaDetalhadamentoDTO::new);
        return page;
    }

    public AulaEntity buscarAulaPorId(Long id) {
        AulaEntity aula = aulaRepository.findByIdAndMotivoCancelamentoIsNull(id);

        if(aula == null) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }

        return aula;
    }
}
