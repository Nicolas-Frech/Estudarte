package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepository;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepositoryJpa;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtivo implements ValidadorAgendamentoAula {


    private final AlunoRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    public ValidadorAtivo(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void validar(AulaDTO dto) {
        var professorAtivo = professorRepository.buscarAtivoByNome(dto.professorNome());
        var alunoAtivo = alunoRepository.buscarAtivoByNome(dto.alunoNome());

        if(!professorAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com professor inativo!");
        } else if(!alunoAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com aluno inativo!");
        }
    }
}
