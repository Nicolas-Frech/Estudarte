package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.validacoes.ValidadorAulaAtiva;
import br.com.estudarte.api.application.email.EmailSender;
import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.application.aula.validacoes.agendamento.ValidadorAgendamentoAula;
import br.com.estudarte.api.application.aula.validacoes.cancelamento.ValidadorCancelamentoAula;
import br.com.estudarte.api.application.aula.validacoes.reagendamento.ValidadorReagendarAula;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
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
    private final EmailSender emailService;

    @Autowired
    private ValidadorAulaAtiva validadorAulaAtiva;

    @Autowired
    List<ValidadorAgendamentoAula> validadores;

    @Autowired
    List<ValidadorCancelamentoAula> validadoresCancelamento;

    @Autowired
    List<ValidadorReagendarAula> validadoresReagendamento;

    public AulaService(AulaRepository aulaRepository, ProfessorRepository professorRepository, AlunoRepository alunoRepository, SalaRepository salaRepository, EmailSender emailService) {
        this.aulaRepository = aulaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.salaRepository = salaRepository;
        this.emailService = emailService;
    }

    public AulaEntity agendarAula(AulaDTO dto) {
        validadores.forEach(v -> v.validar(dto));

        AulaEntity aula = new AulaEntity(dto);
        aula.adicionarSala(salaRepository.buscarPorNome(dto.salaNome()));

        aulaRepository.salvar(aula);

        AlunoEntity aluno = alunoRepository.buscarPorNome(dto.alunoNome());
        ProfessorEntity professor = professorRepository.buscarPorNome(dto.professorNome());

        var dataFormatada = emailService.formatarData(aula.getData());

        emailService.enviarEmail(aluno.getEmail(), "Agendamento de Aula", "Você tem uma aula marcada na data " + dataFormatada);
        emailService.enviarEmail(professor.getEmail(), "Agendamento de Aula", "Você tem uma aula marcada na data " + dataFormatada);
        return aula;
    }

    public void cancelarAula(AulaCancelamentoDTO dto) {
        validadorAulaAtiva.validar(dto.id());
        validadoresCancelamento.forEach(v -> v.validar(dto));
        
        AulaEntity aulaCancelada = aulaRepository.buscarPorId(dto.id());
        aulaCancelada.cancelarAula(dto.motivoCancelamento());

        AlunoEntity aluno = alunoRepository.buscarPorNome(aulaCancelada.getAlunoNome());
        ProfessorEntity professor = professorRepository.buscarPorNome(aulaCancelada.getProfessorNome());

        var dataFormatada = emailService.formatarData(aulaCancelada.getData());

        emailService.enviarEmail(aluno.getEmail(), "Cancelamento de Aula", "A aula da data " + dataFormatada + " foi cancelada pelo motivo: " + aulaCancelada.getMotivoCancelamento());
        emailService.enviarEmail(professor.getEmail(), "Cancelamento de Aula", "A aula da data " + dataFormatada + " foi cancelada pelo motivo: " + aulaCancelada.getMotivoCancelamento());
    }

    public AulaEntity reagendarAula(AulaAtualizacaoDTO dto) {
        validadorAulaAtiva.validar(dto.aulaId());
        validadoresReagendamento.forEach(v -> v.validar(dto));

        AulaEntity aulaReagendada = aulaRepository.buscarPorId(dto.aulaId());

        var dataOriginal = aulaReagendada.getData();
        var dataOriginalFormatada = emailService.formatarData(dataOriginal);

        aulaReagendada.remarcarAula(dto.data());

        var novaDataFormatada = emailService.formatarData(aulaReagendada.getData());

        AlunoEntity aluno = alunoRepository.buscarPorNome(aulaReagendada.getAlunoNome());
        ProfessorEntity professor = professorRepository.buscarPorNome(aulaReagendada.getProfessorNome());

        emailService.enviarEmail(aluno.getEmail(), "Reagendamento de Aula", "A aula da data " + dataOriginalFormatada + " foi reagendada para a data " + novaDataFormatada);
        emailService.enviarEmail(professor.getEmail(), "Reagendamento de Aula", "A aula da data " + dataOriginalFormatada + " foi reagendada para a data " + novaDataFormatada);

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
        validadorAulaAtiva.validar(id);
        AulaEntity aula = aulaRepository.buscarPorId(id);
        return aula;
    }
}
