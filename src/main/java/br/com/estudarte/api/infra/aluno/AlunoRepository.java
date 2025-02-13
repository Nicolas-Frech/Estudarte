package br.com.estudarte.api.infra.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    boolean existsByCpf(String cpf);
}
