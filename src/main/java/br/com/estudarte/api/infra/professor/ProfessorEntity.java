package br.com.estudarte.api.infra.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
import br.com.estudarte.api.application.professor.dto.ProfessorDTOAtualizacao;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.aluno.AlunoEntity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;

    @OneToMany(mappedBy = "professor")
    private List<AlunoEntity> alunos = new ArrayList<>();

    private Double salario;
    private Boolean ativo;

    public void desligarProfessor() {
        this.ativo = false;
    }

    public void atualizarDados(ProfessorDTOAtualizacao dto) {
        if(dto.modalidade() != null) {
            this.modalidade = dto.modalidade();
        }

        if(dto.salario() != null) {
            this.salario = alunos.size() * dto.salario();
        }

        if(dto.telefone() == null || !dto.telefone().equals("")) {
            this.telefone = dto.telefone();
        }

        if(dto.email() == null || !dto.email().equals("")) {
            this.email = dto.email();
        }
    }

    public void setSalario(List<String> alunos) {
        this.salario = alunos.size() * 200.0;
    }

    public ProfessorEntity(ProfessorDTO dto) {
        this.nome = dto.nome();
        this.cnpj = dto.cnpj();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.modalidade = dto.modalidade();
        this.ativo = true;
    }
}
