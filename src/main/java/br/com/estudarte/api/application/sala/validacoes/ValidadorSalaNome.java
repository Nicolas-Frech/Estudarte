package br.com.estudarte.api.application.sala.validacoes;

import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorSalaNome {

    private final SalaRepository salaRepository;

    public ValidadorSalaNome(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public void validar(String nome) {
        if(salaRepository.existePorNome(nome)) {
            throw new ValidacaoException("Já existe uma sala com esse nome!");
        }
    }
}
