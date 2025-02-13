package br.com.estudarte.api.infra.professor;

import br.com.estudarte.api.application.professor.dto.ProfessorDTO;
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

    @ManyToMany(mappedBy = "professores")
    private List<AlunoEntity> alunos = new ArrayList<>();
    private Double salario = alunos.size() * 200.0;
    private Boolean ativo;

    public void desligarProfessor() {
        this.ativo = false;
    }

    public void atualizarSalario(Double novoSalario) {
        this.salario = alunos.size() * novoSalario;
    }

    public void adicionarAluno(AlunoEntity aluno) {
        this.alunos.add(aluno);
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
