package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTOAtualizacao;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.infra.exception.ValidacaoException;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ProfessorService {


    private final ProfessorRepository professorRepository;


    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorEntity registrarProfessor(ProfessorDTO dto) {
        if(professorRepository.existePorCnpj(dto.cnpj())) {
            throw new ValidacaoException("CNPJ já registrado!");
        } else if(professorRepository.existePorNome(dto.nome())) {
            throw new ValidacaoException("Nome já registrado!");

        } else {
            ProfessorEntity professor = new ProfessorEntity(dto);
            professorRepository.salvar(professor);
            return professor;
        }
    }

    public void desligarProfessor(Long id) {
       ProfessorEntity professor = professorRepository.buscarPorIdEAtivoTrue(id);
       if(professor == null) {
           throw new ValidacaoException("Não existe professor com esse ID!");
       }
       professor.desligarProfessor();
       professorRepository.salvar(professor);
    }

    public ProfessorEntity atualizarProfessor(ProfessorDTOAtualizacao dto) {
        ProfessorEntity professorAtualizado = professorRepository.buscarPorIdEAtivoTrue(dto.idProfessor());

        if(professorAtualizado == null) {
            throw new ValidacaoException("Não existe professor com esse ID!");
        }
        professorAtualizado.atualizarDados(dto);
        professorRepository.salvar(professorAtualizado);

        return professorAtualizado;
    }

    public Page<ProfessorDetalhadamentoDTO> listarProfessores(Pageable paginacao) {
        var page = professorRepository.buscarTodosPorAtivoTrue(paginacao).map(ProfessorDetalhadamentoDTO::new);
        return page;
    }

    public ProfessorEntity buscarProfessorPorId(Long id) {
        ProfessorEntity professor = professorRepository.buscarPorIdEAtivoTrue(id);

        if(professor == null) {
            throw new ValidacaoException("Não existe professor com esse ID!");
        }

        return professor;
    }
}
