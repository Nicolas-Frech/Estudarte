package br.com.estudarte.api.domain.sala;

import br.com.estudarte.api.domain.Modalidade;

import java.time.LocalDateTime;

public class Sala {

    private String nome;
    private Boolean reservada;
    private LocalDateTime horarioReserva;
    private Modalidade modalidade;

    public Sala(String nome, Modalidade modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
        this.reservada = false;
    }

    public void reservarSala(LocalDateTime horarioReserva) {
        this.reservada = true;
        this.horarioReserva = horarioReserva;
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
