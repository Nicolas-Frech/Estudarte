package br.com.estudarte.api.application.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaDetalhadamentoDTO;
import br.com.estudarte.api.infra.sala.SalaEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
