package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.professor.dto.ProfessorDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.infra.sala.SalaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    SalaService salaService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarSala(@RequestBody @Valid SalaDTO dto) {
        SalaEntity sala = salaService.registrarSala(dto);

        return ResponseEntity.ok(new SalaDetalhadamentoDTO(sala));
    }

    @PutMapping
    @Transactional
    public ResponseEntity reservarSala(@RequestBody @Valid SalaReservaDTO dto) {
        SalaEntity sala = salaService.reservarSala(dto);

        return ResponseEntity.ok(new SalaDetalhadamentoDTO(sala));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarSala(@PathVariable Long id) {
        salaService.deletarSala(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity listarSalas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = salaService.listarSalas(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarSalaPorId(@PathVariable Long id) {
        SalaEntity sala = salaService.buscarSalaPorId(id);
        return ResponseEntity.ok(new SalaDetalhadamentoDTO(sala));
    }
}
