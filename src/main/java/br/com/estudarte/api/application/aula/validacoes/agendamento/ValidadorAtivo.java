package br.com.estudarte.api.application.aula.validacoes.agendamento;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.application.sala.gateway.SalaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAtivo implements ValidadorAgendamentoAula {


    private final AlunoRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    private final SalaRepository salaRepository;

    public ValidadorAtivo(AlunoRepository alunoRepository, ProfessorRepository professorRepository, SalaRepository salaRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public void validar(AulaDTO dto) {
        if(!alunoRepository.existePorNome(dto.alunoNome())) {
            throw new ValidacaoException("Não existe aluno com esse nome!");
        }

        if(!professorRepository.existePorNome(dto.professorNome())) {
            throw new ValidacaoException("Não existe professor com esse nome!");
        }

        if(!salaRepository.existePorNome(dto.salaNome())) {
            throw new ValidacaoException("Não existe sala com esse nome!");
        }

        var professorAtivo = professorRepository.buscarAtivoByNome(dto.professorNome());
        var alunoAtivo = alunoRepository.buscarAtivoByNome(dto.alunoNome());

        if(!professorAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com professor inativo!");
        } else if(!alunoAtivo) {
            throw new ValidacaoException("Não é permitido agendar aulas com aluno inativo!");
        }
    }
}
