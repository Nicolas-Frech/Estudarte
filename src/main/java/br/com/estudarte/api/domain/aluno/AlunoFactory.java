package br.com.estudarte.api.domain.aluno;

import br.com.estudarte.api.domain.Endereco;
import br.com.estudarte.api.domain.Modalidade;

public class AlunoFactory {

    private Aluno aluno;

    public Aluno comNomeCpfEModalidade(String nome, String cpf, Modalidade modalidade){
        this.aluno = new Aluno(nome, cpf, "", "", modalidade);
        return this.aluno;
    }

    public Aluno adicionarEndereco(String cep, Integer numero, String complemeto) {
        this.aluno.setEndereco(new Endereco(cep, numero, complemeto));
        return this.aluno;
    }

}
