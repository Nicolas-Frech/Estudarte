package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAulaMesmoHorario implements ValidadorReagendarAula {

    @Autowired
    AulaRepository aulaRepository;

    @Override
    public void validar(AulaAtualizacaoDTO dto) {
        AulaEntity aula = aulaRepository.getReferenceById(dto.aulaId());

        var professorComAulaNoMesmoHorario = aulaRepository.existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(aula.getProfessorNome(), dto.data());

        if(professorComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse professor já tem uma aula agendada neste horário!");
        }
    }
}
