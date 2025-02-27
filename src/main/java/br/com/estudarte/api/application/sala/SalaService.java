package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.infra.sala.SalaEntity;
import br.com.estudarte.api.infra.sala.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;


    public SalaEntity registrarSala(SalaDTO dto) {
        SalaEntity sala = new SalaEntity(dto);
        salaRepository.save(sala);
        return sala;
    }
}
