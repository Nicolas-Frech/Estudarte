package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.validacoes.agendamento.ValidadorAgendamentoAula;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.infra.aula.AulaRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    List<ValidadorAgendamentoAula> validadores;

    public AulaEntity agendarAula(AulaDTO dto) {
        if(!alunoRepository.existsByNome(dto.alunoNome())) {
            throw new ValidacaoException("Não existe aluno com esse nome!");
        }

        if(!professorRepository.existsByNome(dto.professorNome())) {
            throw new ValidacaoException("Não existe professor com esse nome!");
        }

        validadores.forEach(v -> v.validar(dto));

        AulaEntity aula = new AulaEntity(dto);
        aulaRepository.save(aula);
        return aula;
    }
}
