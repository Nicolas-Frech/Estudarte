package br.com.estudarte.api.domain.professor;

import br.com.estudarte.api.domain.Endereco;
import br.com.estudarte.api.domain.Modalidade;

public class ProfessorFactory {

    private Professor professor;

    public Professor comNomeCnpjEModalidade(String nome, String cnpj, Modalidade modalidade) {
        this.professor = new Professor(nome, cnpj, "", "", modalidade);
        return this.professor;
    }

    public Professor adicionarEndereco(String cep, Integer numero, String complemento) {
        this.professor.setEndereco(new Endereco(cep, numero, complemento));
        return this.professor;
    }
}
