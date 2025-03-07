package br.com.estudarte.api.infra.sala;

import br.com.estudarte.api.application.sala.dto.SalaDTO;
import br.com.estudarte.api.application.sala.dto.SalaReservaDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aula.AulaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean reservada;

    @ElementCollection
    @CollectionTable(name = "sala_horarios_reserva", joinColumns = @JoinColumn(name = "sala_id"))
    @Column(name = "horario_reserva")
    private List<LocalDateTime> horariosReserva = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;

    @OneToMany(mappedBy = "sala")
    private List<AulaEntity> aulas;

    public SalaEntity(SalaDTO dto) {
        this.nome = dto.nome();
        this.modalidade = dto.modalidade();
        this.reservada = false;
    }

    public void reservarSala(SalaReservaDTO dto) {
        this.reservada = true;
        if (this.horariosReserva == null) {
            this.horariosReserva = new ArrayList<>();
        }
        this.horariosReserva.add(dto.horarioReserva());
    }
}
