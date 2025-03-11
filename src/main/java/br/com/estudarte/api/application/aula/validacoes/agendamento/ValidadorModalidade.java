package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorModalidade implements ValidadorAgendamentoAula {


    private final ProfessorRepository professorRepository;

    private final AlunoRepository alunoRepository;

    public ValidadorModalidade(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void validar(AulaDTO dto) {
        AlunoEntity aluno = alunoRepository.buscarPorNome(dto.alunoNome());
        ProfessorEntity professor = professorRepository.buscarPorNome(dto.professorNome());

        if(!aluno.getModalidade().equals(dto.modalidade())) {
            throw new ValidacaoException("Esse aluno não está matriculado para essa modalidade");
        }

        if(!professor.getModalidade().equals(dto.modalidade())) {
            throw new ValidacaoException("Esse professor não ministra aulas dessa modalidade");
        }
    }
}
