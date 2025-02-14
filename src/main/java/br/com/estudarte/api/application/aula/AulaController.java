package br.com.estudarte.api.application.aula;

import br.com.estudarte.api.application.aula.dto.AulaAtualizacaoDTO;
import br.com.estudarte.api.application.aula.dto.AulaCancelamentoDTO;
import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.application.aula.dto.AulaDetalhadamentoDTO;
import br.com.estudarte.api.infra.aula.AulaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    AulaService aulaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarAula(@RequestBody @Valid AulaDTO dto, UriComponentsBuilder uriBuilder) {
        AulaEntity aulaAgendada = aulaService.agendarAula(dto);

        var uri = uriBuilder.path("/aula/{id}").buildAndExpand(aulaAgendada.getId()).toUri();
        return ResponseEntity.created(uri).body(new AulaDetalhadamentoDTO(aulaAgendada));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarAula(@RequestBody AulaCancelamentoDTO dto) {
        aulaService.cancelarAula(dto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional ResponseEntity reagendarAula(@RequestBody AulaAtualizacaoDTO dto) {
        AulaEntity aulaReagendada = aulaService.reagendarAula(dto);

        return ResponseEntity.ok(new AulaDetalhadamentoDTO(aulaReagendada));
    }
}
