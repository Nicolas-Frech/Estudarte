package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validador24HorasRemarcar implements ValidadorReagendarAula {


    @Autowired
    AulaRepository aulaRepository;

    @Override
    public void validar(AulaAtualizacaoDTO dto) {
        AulaEntity aula = aulaRepository.getReferenceById(dto.aulaId());
        var agora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(agora, aula.getData()).toHours();

        if(diferencaEmHoras < 24) {
            throw new ValidacaoException("Somente reagendar aula com no mínimo 24 horas de antecedência!");
        }
    }
}
