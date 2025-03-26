package br.com.estudarte.api.application.sala.validacoes.reserva;

import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;

public interface ValidadorReservaSala {
    void validar(SalaReservaDTO dto);
}
