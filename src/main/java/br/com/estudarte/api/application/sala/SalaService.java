package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.application.sala.validacoes.ValidadorReservaSala;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.sala.SalaEntity;
import br.com.estudarte.api.infra.sala.repository.SalaRepository;
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
    public SalaService(SalaRepository salaRepository, List<ValidadorReservaSala> validadores) {
        this.salaRepository = salaRepository;
        this.validadores = validadores;
    }


    public SalaEntity registrarSala(SalaDTO dto) {
        SalaEntity sala = new SalaEntity(dto);

        if(salaRepository.existePorNome(dto.nome())) {
            throw new ValidacaoException("Já existe uma sala com esse nome!");
        } else {
            salaRepository.salvar(sala);
            return sala;
        }
    }

    public SalaEntity reservarSala(SalaReservaDTO dto) {
        if (!salaRepository.existePorIdEAtivoTrue(dto.idSala())) {
            throw new ValidacaoException("Não existe sala com esse ID!");
        }

        validadores.forEach(v -> v.validar(dto));

        SalaEntity sala = salaRepository.buscarPorId(dto.idSala());
        sala.reservarSala(dto);
        return salaRepository.salvar(sala);
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existePorIdEAtivoTrue(id)) {
            throw new ValidacaoException("Não existe sala com esse ID!");
        }

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
