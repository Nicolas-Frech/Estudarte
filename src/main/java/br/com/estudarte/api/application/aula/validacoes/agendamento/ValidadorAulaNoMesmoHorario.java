package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aula.repository.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.sala.repository.SalaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAulaNoMesmoHorario implements ValidadorAgendamentoAula {

    private final AulaRepository aulaRepository;

    private final SalaRepository salaRepository;

    public ValidadorAulaNoMesmoHorario(AulaRepository aulaRepository, SalaRepository salaRepository) {
        this.aulaRepository = aulaRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public void validar(AulaDTO dto) {
        var professorComAulaNoMesmoHorario = aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(dto.professorNome(), dto.data());

        var alunoComAulaNoMesmoHorario = aulaRepository.existePorAlunoNomeEDataEMotivoCancelamentoNull(dto.alunoNome(), dto.data());

        var aulaNaMesmaSala = salaRepository.existePorHorarioReservaENome(dto.data(), dto.salaNome());

        if(professorComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse professor já tem uma aula agendada neste horário!");
        }

        if(alunoComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse aluno já tem uma aula agendada neste horário!");
        }

        if(aulaNaMesmaSala) {
            throw new ValidacaoException("Essa sala está reservada para esse horário!");
        }
    }
}
