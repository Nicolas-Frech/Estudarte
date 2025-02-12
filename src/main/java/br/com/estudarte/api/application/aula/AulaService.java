package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    public void marcarAula(AulaDTO dto) {
        aulaRepository.save(new AulaEntity(dto));
    }
}
