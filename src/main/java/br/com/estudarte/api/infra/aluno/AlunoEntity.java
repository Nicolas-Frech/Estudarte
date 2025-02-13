package br.com.estudarte.api.infra.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "alunos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    @ManyToMany
    @JoinTable(name = "aluno_professor",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<ProfessorEntity> professores;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private Boolean ativo;

    public void cancelarMatricula() {
        this.ativo = false;
    }

    public void adicionarProfessor(ProfessorEntity professor) {
        this.professores.add(professor);
    }

    public void atualizarModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public AlunoEntity(AlunoDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.modalidade = dto.modalidade();
        this.ativo = true;
    }
}
