package br.com.estudarte.api.domain.aula;

import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.sala.Sala;

import java.time.LocalDateTime;

public class Aula {

    private String professorNome;
    private String alunoNome;
    private Modalidade modalidade;
    private LocalDateTime data;
    private MotivoCancelamento motivoCancelamento;
    private Sala sala;

    public Aula(String professorNome, String alunoNome, Modalidade modalidade, LocalDateTime data, Sala sala) {
        validarData(data);

        this.professorNome = professorNome;
        this.alunoNome = alunoNome;
        this.modalidade = modalidade;
        this.data = data;
        this.sala = sala;
    }

    public void alterarData(LocalDateTime data) {
        validarData(data);
        this.data = data;
    }

    public void cancelarAula(MotivoCancelamento motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getProfessor() {
        return professorNome;
    }

    public String getAluno() {
        return alunoNome;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    private void validarData(LocalDateTime data) {
        if(data.isBefore(LocalDateTime.now().plusMinutes(60))) {
            throw new IllegalArgumentException("Horário da aula inválido!");
        }
    }
}
