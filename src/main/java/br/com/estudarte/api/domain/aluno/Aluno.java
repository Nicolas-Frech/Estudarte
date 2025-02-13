package br.com.estudarte.api.domain.aluno;

import br.com.estudarte.api.domain.Endereco;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.professor.Professor;

import java.util.List;

public class Aluno {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private List<Professor> professor;
    private Endereco endereco;
    private Modalidade modalidade;
    private Boolean ativo;

    public Aluno(String nome, String cpf, String telefone, String email, Modalidade modalidade) {
        if(cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("CPF no formato errado!");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.modalidade = modalidade;
        this.ativo = true;
    }

    public void adicionarProfessor(Professor professor) {
        this.professor.add(professor);
    }

    public void cancelarMatricula() {
        this.ativo = false;
    }


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }
}
