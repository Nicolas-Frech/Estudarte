package br.com.estudarte.api.domain.professor;

import br.com.estudarte.api.domain.Endereco;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.domain.aluno.Aluno;

import java.util.List;

public class Professor {

    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private Modalidade modalidade;
    private Endereco endereco;
    private Double salario;
    private List<Aluno> alunos;
    private Boolean ativo;

    public Professor(String nome, String cnpj, String telefone, String email, Modalidade modalidade) {

        if(cnpj == null || !cnpj.matches("(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)")) {
            throw new IllegalArgumentException("CNPJ no formato inválido!");
        }

        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.modalidade = modalidade;
        this.ativo = true;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void adicionarSalario() {
        this.salario = alunos.size() * 40.0;
    }

    public void desligarProfessor() {
        this.ativo = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
