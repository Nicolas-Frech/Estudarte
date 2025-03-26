package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidadorAulaNoMesmoHorarioTest {

    @Mock
    private AulaDTO dto;

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private ValidadorAulaNoMesmoHorario validador;

    @Test
    @DisplayName("Deveria agendar aula")
    void agendar_cenario1() {

        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(dto.professorNome(), dto.data()))
                .willReturn(false);

        given(aulaRepository.existePorAlunoNomeEDataEMotivoCancelamentoNull(dto.alunoNome(), dto.data()))
                .willReturn(false);

        given(salaRepository.existePorHorarioReservaENome(dto.data(), dto.salaNome())).willReturn(false);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se professor já tem uma aula no mesmo horario")
    void agendar_cenario2() {

        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(dto.professorNome(), dto.data()))
                .willReturn(true);

        given(aulaRepository.existePorAlunoNomeEDataEMotivoCancelamentoNull(dto.alunoNome(), dto.data()))
                .willReturn(false);

        given(salaRepository.existePorHorarioReservaENome(dto.data(), dto.salaNome())).willReturn(false);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se aluno já tem uma aula no mesmo horario")
    void agendar_cenario3() {

        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(dto.professorNome(), dto.data()))
                .willReturn(false);

        given(aulaRepository.existePorAlunoNomeEDataEMotivoCancelamentoNull(dto.alunoNome(), dto.data()))
                .willReturn(true);

        given(salaRepository.existePorHorarioReservaENome(dto.data(), dto.salaNome())).willReturn(false);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se sala está reservada")
    void agendar_cenario4() {

        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(dto.professorNome(), dto.data()))
                .willReturn(false);

        given(aulaRepository.existePorAlunoNomeEDataEMotivoCancelamentoNull(dto.alunoNome(), dto.data()))
                .willReturn(false);

        given(salaRepository.existePorHorarioReservaENome(dto.data(), dto.salaNome())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
}