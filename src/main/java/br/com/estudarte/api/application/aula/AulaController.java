package br.com.estudarte.api.application.aula;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    AulaService aulaService;

    @PostMapping
    @Transactional
    public ResponseEntity marcarAula(@RequestBody @Valid AulaDTO dto) {
        aulaService.marcarAula(dto);
        return ResponseEntity.ok().build();
    }
}
