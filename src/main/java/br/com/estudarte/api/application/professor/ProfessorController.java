package br.com.estudarte.api.application.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
