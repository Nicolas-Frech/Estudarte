package br.com.estudarte.api.application.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDetalhadamentoDTO;
import br.com.estudarte.api.infra.aluno.AlunoEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarAluno(@RequestBody @Valid AlunoDTO dto, UriComponentsBuilder uriBuilder) {
        AlunoEntity alunoRegistrado = alunoService.registrarAluno(dto);

        var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(alunoRegistrado.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDetalhadamentoDTO(alunoRegistrado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelarMatricula(@PathVariable Long id) {
        alunoService.cancelarMatricula(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarInformacoes(@RequestBody AlunoAtualizacaoDTO dto) {
        AlunoEntity alunoAtualizado = alunoService.atualizarInformacoes(dto);

        return ResponseEntity.ok(new AlunoDetalhadamentoDTO(alunoAtualizado));
    }

    @GetMapping
    public ResponseEntity listarAlunos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = alunoService.listarAlunos(paginacao);

        return ResponseEntity.ok(page);
    }
}
