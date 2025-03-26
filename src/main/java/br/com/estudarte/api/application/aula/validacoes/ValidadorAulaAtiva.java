package br.com.estudarte.api.application.aula.validacoes;

import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAulaAtiva {

    private final AulaRepository aulaRepository;

    public ValidadorAulaAtiva(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public void validar(Long id) {
        if(!aulaRepository.existePorIdEMotivoCancelamentoNull(id)) {
            throw new ValidacaoException("Não existe aula marcada com esse ID!");
        }
    }
}
