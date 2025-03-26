package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.application.sala.validacoes.ValidadorSalaId;
import br.com.estudarte.api.application.sala.validacoes.ValidadorSalaNome;
import br.com.estudarte.api.application.sala.validacoes.reserva.ValidadorReservaSala;
import br.com.estudarte.api.infra.sala.SalaEntity;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final List<ValidadorReservaSala> validadores;

    @Autowired
    private ValidadorSalaNome validadorSalaNome;

    @Autowired
    private ValidadorSalaId validadorSalaId;

    @Autowired
    public SalaService(SalaRepository salaRepository, List<ValidadorReservaSala> validadores) {
        this.salaRepository = salaRepository;
        this.validadores = validadores;
    }


    public SalaEntity registrarSala(SalaDTO dto) {
        validadorSalaNome.validar(dto.nome());
        SalaEntity sala = new SalaEntity(dto);
        salaRepository.salvar(sala);
        return sala;
    }

    public SalaEntity reservarSala(SalaReservaDTO dto) {
        validadorSalaId.validar(dto.idSala());
        validadores.forEach(v -> v.validar(dto));

        SalaEntity sala = salaRepository.buscarPorId(dto.idSala());
        sala.reservarSala(dto);
        return salaRepository.salvar(sala);
    }

    public void deletarSala(Long id) {
        validadorSalaId.validar(id);
        SalaEntity sala = salaRepository.buscarPorId(id);
        sala.deletar();
        salaRepository.salvar(sala);
    }

    public Page<SalaDetalhadamentoDTO> listarSalas(Pageable paginacao) {
        var page = salaRepository.buscarTodosPorAtivoTrue(paginacao).map(SalaDetalhadamentoDTO::new);
        return page;
    }

    public SalaEntity buscarSalaPorId(Long id) {
        return salaRepository.buscarPorIdEAtivoTrue(id);
    }
}
