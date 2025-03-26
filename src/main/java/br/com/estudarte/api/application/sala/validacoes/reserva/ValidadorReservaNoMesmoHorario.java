package br.com.estudarte.api.application.sala.validacoes.reserva;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorReservaNoMesmoHorario implements ValidadorReservaSala {

    private final AulaRepository aulaRepository;
    private final SalaRepository salaRepository;

    public ValidadorReservaNoMesmoHorario(AulaRepository aulaRepository, SalaRepository salaRepository) {
        this.aulaRepository = aulaRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public void validar(SalaReservaDTO dto) {

        if(aulaRepository.existePorDataEIdEMotivoCancelamentoNull(dto.horarioReserva(), dto.idSala())) {
            throw new ValidacaoException("Já existe uma aula marcada para esse horário nesta sala!");
        }

        if(salaRepository.existePorHorarioReservaEId(dto.horarioReserva(), dto.idSala())) {
            throw new ValidacaoException("Já existe uma reserva para esse horário nesta sala!");
        }
    }
}
