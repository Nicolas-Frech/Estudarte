package br.com.estudarte.api.application.aluno.validacoes.atualizacao;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProfessorId implements ValidadorAtualizacao {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public ValidadorProfessorId(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void validar(AlunoAtualizacaoDTO dto) {

        AlunoEntity aluno = alunoRepository.buscarPorId(dto.alunoId());
        ProfessorEntity professor = professorRepository.buscarPorIdEAtivoTrue(dto.professorId());

        if(professor == null) {
            throw new ValidacaoException("Não existe professor com esse ID!");
        } else if(professor.getModalidade() != aluno.getModalidade()) {
            throw new ValidacaoException("Professor não ministra aula dessa modalidade!");
        }
    }
}
