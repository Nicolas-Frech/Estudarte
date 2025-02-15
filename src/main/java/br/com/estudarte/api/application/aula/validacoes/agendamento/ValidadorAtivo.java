package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtivo implements ValidadorAgendamentoAula {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Override
    public void validar(AulaDTO dto) {
        var professorAtivo = professorRepository.findAtivoByNome(dto.professorNome());
        var alunoAtivo = alunoRepository.findAtivoByNome(dto.alunoNome());

        if(!professorAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com professor inativo!");
        } else if(!alunoAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com aluno inativo!");
        }
    }
}
