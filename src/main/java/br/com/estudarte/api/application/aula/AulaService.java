package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    public AulaEntity marcarAula(AulaDTO dto) {
        AulaEntity aula = new AulaEntity(dto);
        aulaRepository.save(aula);
        return aula;
    }
}
