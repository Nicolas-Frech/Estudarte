package br.com.estudarte.api.infra.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    boolean existsByCnpj(String cnpj);

    boolean existsByNome(String professorNome);

    ProfessorEntity findByNome(String professorNome);

    @Query("SELECT p.ativo FROM ProfessorEntity p WHERE p.nome = :professorNome")
    Boolean findAtivoByNome(String professorNome);
}
