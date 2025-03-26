package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidadorAtivoTest {

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private ValidadorAtivo validador;

    @Mock
    private AulaDTO dto;

    @Mock
    private ProfessorEntity professor;

    @Mock
    private AlunoEntity aluno;

    @Test
    @DisplayName("Deveria agendar aula")
    void agendar_cenario1() {
        given(professorRepository.buscarAtivoByNome(dto.professorNome())).willReturn(true);
        given(alunoRepository.buscarAtivoByNome(dto.alunoNome())).willReturn(true);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se professor não estiver ativo")
    void agendar_cenario2() {
        given(professorRepository.buscarAtivoByNome(dto.professorNome())).willReturn(true);
        given(alunoRepository.buscarAtivoByNome(dto.alunoNome())).willReturn(false);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se aluno não estiver ativo")
    void agendar_cenario3() {
        given(professorRepository.buscarAtivoByNome(dto.professorNome())).willReturn(false);
        given(alunoRepository.buscarAtivoByNome(dto.alunoNome())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

}