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

        if(dto.modalidade() != null) {
            professorAtualizado.atualizarModalidade(dto.modalidade());
        }

        if(dto.salario() != null) {
            professorAtualizado.atualizarSalario(dto.salario());
        }

        return professorAtualizado;
    }

    public Page<ProfessorDetalhadamentoDTO> listarProfessores(Pageable paginacao) {
        var page = professorRepository.findAllByAtivoTrue(paginacao).map(ProfessorDetalhadamentoDTO::new);
        return page;
    }
}
