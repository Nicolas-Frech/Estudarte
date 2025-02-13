package br.com.estudarte.api.infra.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByNome(String alunoNome);

    AlunoEntity findByNome(String alunoNome);

    @Query("SELECT a.ativo FROM AlunoEntity a WHERE a.nome = :alunoNome")
    Boolean findAtivoByNome(String alunoNome);
}
