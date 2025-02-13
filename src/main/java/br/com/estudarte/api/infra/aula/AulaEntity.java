package br.com.estudarte.api.infra.aula;

import br.com.estudarte.api.application.aula.dto.AulaDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.aula.MotivoCancelamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "aulas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AulaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String professorNome;
    private String alunoNome;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public AulaEntity(AulaDTO dto) {
        this.professorNome = dto.professorNome();
        this.alunoNome = dto.alunoNome();
        this.modalidade = dto.modalidade();
        this.data = dto.data();
    }
}
