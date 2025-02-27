package br.com.estudarte.api.domain.sala;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.aula.Aula;

import java.time.LocalDateTime;
import java.util.List;

public class Sala {

    private String nome;
    private Boolean reservada;
    private LocalDateTime horarioReserva;
    private Modalidade modalidade;
    private List<Aula> aulas;

    public Sala(String nome, Modalidade modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
        this.reservada = false;
    }

    public void reservarSala(LocalDateTime horarioReserva) {
        this.reservada = true;
        this.horarioReserva = horarioReserva;
    }

    public void adicionarAula(Aula aula) {
        this.aulas.add(aula);
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getReservada() {
        return reservada;
    }

    public LocalDateTime getHorarioReserva() {
        return horarioReserva;
    }
}
