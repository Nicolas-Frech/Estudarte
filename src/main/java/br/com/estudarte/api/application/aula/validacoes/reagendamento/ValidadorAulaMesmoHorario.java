package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAulaMesmoHorario implements ValidadorReagendarAula {

    private final AulaRepository aulaRepository;

    private final SalaRepository salaRepository;

    public ValidadorAulaMesmoHorario(AulaRepository aulaRepository, SalaRepository salaRepository) {
        this.aulaRepository = aulaRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public void validar(AulaAtualizacaoDTO dto) {
        AulaEntity aula = aulaRepository.buscarPorId(dto.aulaId());

        var professorComAulaNoMesmoHorario = aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(aula.getProfessorNome(), dto.data());

        var aulaNaMesmaSala = salaRepository.existePorHorarioReservaENome(dto.data(), aula.getSala().getNome());

        if(professorComAulaNoMesmoHorario) {
            throw new ValidacaoException("Esse professor já tem uma aula agendada neste horário!");
        }

        if(aulaNaMesmaSala) {
            throw new ValidacaoException("Essa sala está reservada para esse horário");
        }
    }
}
