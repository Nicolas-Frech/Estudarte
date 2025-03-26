package br.com.estudarte.api.application.sala.validacoes;

import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorSalaId {

    private final SalaRepository salaRepository;

    public ValidadorSalaId(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public void validar(Long id) {
        if (!salaRepository.existePorIdEAtivoTrue(id)) {
            throw new ValidacaoException("Não existe sala com esse ID!");
        }
    }
}
