package br.com.estudarte.api.application.aula.validacoes.reagendamento;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.sala.SalaEntity;
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
class ValidadorAulaMesmoHorarioTest {

    @Mock
    private AulaRepository aulaRepository;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private AulaAtualizacaoDTO dto;

    @InjectMocks
    private ValidadorAulaMesmoHorario validador;

    @Mock
    private AulaEntity aula;

    @Mock
    SalaEntity sala;

    @Test
    @DisplayName("Deveria reagendar aula")
    void reagendar_cenario1() {
        given(aula.getSala()).willReturn(sala);
        given(sala.getNome()).willReturn("Sala");

        given(aulaRepository.buscarPorId(dto.aulaId())).willReturn(aula);
        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(aula.getProfessorNome(), dto.data()))
                .willReturn(false);
        given(salaRepository.existePorHorarioReservaENome(dto.data(), aula.getSala().getNome()))
                .willReturn(false);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria reagendar aula se professor já tem aula no mesmo horário")
    void reagendar_cenario2() {
        given(aula.getSala()).willReturn(sala);
        given(sala.getNome()).willReturn("Sala");

        given(aulaRepository.buscarPorId(dto.aulaId())).willReturn(aula);
        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(aula.getProfessorNome(), dto.data()))
                .willReturn(true);
        given(salaRepository.existePorHorarioReservaENome(dto.data(), aula.getSala().getNome()))
                .willReturn(false);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria reagendar aula se sala está reservada")
    void reagendar_cenario3() {
        given(aula.getSala()).willReturn(sala);
        given(sala.getNome()).willReturn("Sala");

        given(aulaRepository.buscarPorId(dto.aulaId())).willReturn(aula);
        given(aulaRepository.existePorProfessorNomeEDataEMotivoCancelamentoNull(aula.getProfessorNome(), dto.data()))
                .willReturn(false);
        given(salaRepository.existePorHorarioReservaENome(dto.data(), aula.getSala().getNome()))
                .willReturn(true);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
}