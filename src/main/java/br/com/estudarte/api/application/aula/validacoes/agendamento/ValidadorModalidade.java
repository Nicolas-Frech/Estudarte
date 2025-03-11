package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepositoryJpa;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorModalidade implements ValidadorAgendamentoAula {

    @Autowired
    ProfessorRepositoryJpa professorRepository;

    @Autowired
    AlunoRepositoryJpa alunoRepository;

    @Override
    public void validar(AulaDTO dto) {
        AlunoEntity aluno = alunoRepository.findByNome(dto.alunoNome());
        ProfessorEntity professor = professorRepository.findByNome(dto.professorNome());

        if(!aluno.getModalidade().equals(dto.modalidade())) {
            throw new ValidacaoException("Esse aluno não está matriculado para essa modalidade");
        }

        if(!professor.getModalidade().equals(dto.modalidade())) {
            throw new ValidacaoException("Esse professor não ministra aulas dessa modalidade");
        }
    }
}
