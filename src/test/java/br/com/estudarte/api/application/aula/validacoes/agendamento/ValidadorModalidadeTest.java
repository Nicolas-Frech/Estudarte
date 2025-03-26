package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.domain.Modalidade;
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
class ValidadorModalidadeTest {

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private ProfessorEntity professor;

    @Mock
    private AlunoEntity aluno;

    @Mock
    private AulaDTO dto;

    @InjectMocks
    private ValidadorModalidade validador;

    @Test
    @DisplayName("Deveria agendar aula")
    void agendar_cenario1() {
        given(alunoRepository.buscarPorNome(dto.alunoNome())).willReturn(aluno);
        given(professorRepository.buscarPorNome(dto.professorNome())).willReturn(professor);

        given(dto.modalidade()).willReturn(Modalidade.SAXOFONE);
        given(aluno.getModalidade()).willReturn(Modalidade.SAXOFONE);
        given(professor.getModalidade()).willReturn(Modalidade.SAXOFONE);

        assertDoesNotThrow(() -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se aluno tem modalidade diferente")
    void agendar_cenario2() {
        given(alunoRepository.buscarPorNome(dto.alunoNome())).willReturn(aluno);
        given(professorRepository.buscarPorNome(dto.professorNome())).willReturn(professor);

        given(dto.modalidade()).willReturn(Modalidade.SAXOFONE);
        given(aluno.getModalidade()).willReturn(Modalidade.BAIXO);

        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }

    @Test
    @DisplayName("Não deveria agendar aula se professor tem modalidade diferente")
    void agendar_cenario3() {
        given(alunoRepository.buscarPorNome(dto.alunoNome())).willReturn(aluno);
        given(professorRepository.buscarPorNome(dto.professorNome())).willReturn(professor);

        given(dto.modalidade()).willReturn(Modalidade.SAXOFONE);
        given(aluno.getModalidade()).willReturn(Modalidade.SAXOFONE);
        given(professor.getModalidade()).willReturn(Modalidade.BAIXO);


        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
}