package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTOAtualizacao;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.infra.aluno.AlunoRepository;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public ProfessorEntity registrarProfessor(ProfessorDTO dto) {
        if(professorRepository.existsByCnpj(dto.cnpj())) {
            throw new ValidacaoException("CNPJ já registrado!");
        } else if(professorRepository.existsByNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");

        } else {
            ProfessorEntity professor = new ProfessorEntity(dto);
            professorRepository.save(professor);
            return professor;
        }
    }

    public void desligarProfessor(Long id) {
       ProfessorEntity professor = professorRepository.getReferenceById(id);
       professor.desligarProfessor();
    }

    public ProfessorEntity atualizarProfessor(ProfessorDTOAtualizacao dto) {
        ProfessorEntity professorAtualizado = professorRepository.getReferenceById(dto.idProfessor());
        professorAtualizado.atualizarDados(dto);

        return professorAtualizado;
    }

    public Page<ProfessorDetalhadamentoDTO> listarProfessores(Pageable paginacao) {
        var page = professorRepository.findAllByAtivoTrue(paginacao).map(ProfessorDetalhadamentoDTO::new);
        return page;
    }

    public ProfessorEntity buscarProfessorPorId(Long id) {
        ProfessorEntity professor = professorRepository.findByIdAndAtivoTrue(id);

        if(professor == null) {
            throw new ValidacaoException("Não existe professor com esse ID!");
        }

        return professor;
    }
}
