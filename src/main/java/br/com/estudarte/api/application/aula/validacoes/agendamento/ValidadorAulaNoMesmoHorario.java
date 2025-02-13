package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAulaNoMesmoHorario implements ValidadorAgendamentoAula {

    @Autowired
    AulaRepository aulaRepository;

    @Override
    public void validar(AulaDTO dto) {
        var professorComAulaNoMesmoHorario = aulaRepository.existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(dto.professorNome(), dto.data());

        var alunoComAulaNoMesmoHorario = aulaRepository.existsByAlunoNomeAndDataAndMotivoCancelamentoIsNull(dto.alunoNome(), dto.data());

        if(professorComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse professor já tem uma aula agendada neste horário");
        }

        if(alunoComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse aluno já tem uma aula agendada neste horário");
        }

    }
}
