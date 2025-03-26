package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTOAtualizacao;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.application.professor.validacoes.cadastro.ValidadorCadastro;
import br.com.estudarte.api.application.professor.validacoes.ValidadorProfessorAtivo;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfessorService {


    private final ProfessorRepository professorRepository;

    @Autowired
    private List<ValidadorCadastro> validadoresCadastro;

    @Autowired
    private ValidadorProfessorAtivo validadorProfessorAtivo;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorEntity registrarProfessor(ProfessorDTO dto) {
        validadoresCadastro.forEach(v -> v.validar(dto));
        ProfessorEntity professor = new ProfessorEntity(dto);
        professorRepository.salvar(professor);
        return professor;
    }

    public void desligarProfessor(Long id) {
        validadorProfessorAtivo.validar(id);
        ProfessorEntity professor = professorRepository.buscarPorId(id);
        professor.desligarProfessor();
        professorRepository.salvar(professor);
    }

    public ProfessorEntity atualizarProfessor(ProfessorDTOAtualizacao dto) {
        validadorProfessorAtivo.validar(dto.idProfessor());

        ProfessorEntity professorAtualizado = professorRepository.buscarPorIdEAtivoTrue(dto.idProfessor());

        professorAtualizado.atualizarDados(dto);
        professorRepository.salvar(professorAtualizado);
        return professorAtualizado;
    }

    public Page<ProfessorDetalhadamentoDTO> listarProfessores(Pageable paginacao) {
        var page = professorRepository.buscarTodosPorAtivoTrue(paginacao).map(ProfessorDetalhadamentoDTO::new);
        return page;
    }

    public ProfessorEntity buscarProfessorPorId(Long id) {
        validadorProfessorAtivo.validar(id);

        ProfessorEntity professor = professorRepository.buscarPorIdEAtivoTrue(id);
        return professor;
    }
}
