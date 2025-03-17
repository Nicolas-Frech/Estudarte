package br.com.estudarte.api.application.aula.validacoes.cancelamento;

import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.domain.aula.MotivoCancelamento;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidadorMotivoNuloTest {

    @InjectMocks
    ValidadorMotivoNulo validador;

    @Mock
    AulaCancelamentoDTO dto;

    @Test
    @DisplayName("Deveria cancelar aula")
    void cancelar_cenario1() {
        given(dto.motivoCancelamento()).willReturn(MotivoCancelamento.ALUNO_CANCELOU);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria cancelar aula com motivo nulo")
    void cancelar_cenario2() {
        given(dto.motivoCancelamento()).willReturn(null);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
}