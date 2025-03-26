package br.com.estudarte.api.application.aula.validacoes.cancelamento;

import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validador24HorasDeAntecedencia implements ValidadorCancelamentoAula {

    private final AulaRepository aulaRepository;

    public Validador24HorasDeAntecedencia(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    @Override
    public void validar(AulaCancelamentoDTO dto) {
        AulaEntity aula = aulaRepository.buscarPorId(dto.id());
        var agora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(agora, aula.getData()).toHours();

        if(diferencaEmHoras < 24) {
            throw new ValidacaoException("Somente cancelar aula com no mínimo 24 horas de antecedência!");
        }
    }
}
