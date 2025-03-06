package br.com.estudarte.api.infra.aluno;

import br.com.estudarte.api.application.aluno.dto.AlunoAtualizacaoDTO;
import br.com.estudarte.api.application.aluno.dto.AlunoDTO;
import br.com.estudarte.api.domain.Modalidade;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = ("professor_id"))
    private ProfessorEntity professor;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private Boolean ativo;

    public void cancelarMatricula() {
        this.ativo = false;
    }

    public void adicionarProfessor(ProfessorEntity professor) {
        if(this.professor == null) {
            this.professor = professor;
        }
    }

    public void atualizarDados(AlunoAtualizacaoDTO dto) {
        if(dto.modalidade() != null) {
            this.modalidade = dto.modalidade();
        }

        if(dto.telefone() != null) {
            this.telefone = dto.telefone();
        }

        if(dto.email() != null) {
            this.email = dto.email();
        }
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
