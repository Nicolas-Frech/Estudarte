package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.application.sala.validacoes.ValidadorReservaSala;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.sala.SalaEntity;
import br.com.estudarte.api.infra.sala.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    List<ValidadorReservaSala> validadores;


    public SalaEntity registrarSala(SalaDTO dto) {
        SalaEntity sala = new SalaEntity(dto);

        if(salaRepository.existsByNome(dto.nome())) {
            throw new ValidacaoException("Já existe uma sala com esse nome!");
        } else {
            salaRepository.save(sala);
            return sala;
        }
    }

    public SalaEntity reservarSala(SalaReservaDTO dto) {
        if (!salaRepository.existsById(dto.idSala())) {
            throw new ValidacaoException("Não existe sala com esse ID!");
        }

        validadores.forEach(v -> v.validar(dto));

        SalaEntity sala = salaRepository.getReferenceById(dto.idSala());
        sala.reservarSala(dto);
        return sala;
    }

    public void deletarSala(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ValidacaoException("Não existe sala com esse ID!");
        }

        SalaEntity sala = salaRepository.getReferenceById(id);
        salaRepository.delete(sala);
    }

    public Page<SalaDetalhadamentoDTO> listarSalas(Pageable paginacao) {
        var page = salaRepository.findAll(paginacao).map(SalaDetalhadamentoDTO::new);
        return page;
    }

    public SalaEntity buscarSalaPorId(Long id) {
        return salaRepository.getReferenceById(id);
    }
}
