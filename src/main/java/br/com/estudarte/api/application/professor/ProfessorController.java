package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTOAtualizacao;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarProfessor(@RequestBody @Valid ProfessorDTO dto, UriComponentsBuilder uriBuilder) {
        ProfessorEntity professorRegistrado = professorService.registrarProfessor(dto);

        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professorRegistrado.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfessorDetalhadamentoDTO(professorRegistrado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desligarProfessor(@PathVariable Long id) {
        professorService.desligarProfessor(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarProfessor(@RequestBody ProfessorDTOAtualizacao dto) {
        ProfessorEntity professor = professorService.atualizarProfessor(dto);

        return ResponseEntity.ok(new ProfessorDetalhadamentoDTO(professor));
    }
}
