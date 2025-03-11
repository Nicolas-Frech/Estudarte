package br.com.estudarte.api.application.sala.validacoes;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.sala.repository.SalaRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorReservaNoMesmoHorario implements ValidadorReservaSala {

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    SalaRepositoryJpa salaRepositoryJpa;
    @Override
    public void validar(SalaReservaDTO dto) {

        if(aulaRepository.existsByDataAndIdAndMotivoCancelamentoIsNull(dto.horarioReserva(), dto.idSala())) {
            throw new ValidacaoException("Já existe uma aula marcada para esse horário nesta sala!");
        }

        if(salaRepositoryJpa.existsByHorarioReservaAndId(dto.horarioReserva(), dto.idSala())) {
            throw new ValidacaoException("Já existe uma reserva para esse horário nesta sala!");
        }
    }
}
