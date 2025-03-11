package br.com.estudarte.api.infra.professor.repository;

import br.com.estudarte.api.infra.professor.ProfessorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessorRepositoryJpa extends JpaRepository<ProfessorEntity, Long> {
    boolean existsByCnpj(String cnpj);

    boolean existsByNome(String professorNome);

    ProfessorEntity findByNome(String professorNome);

    @Query("SELECT p.ativo FROM ProfessorEntity p WHERE p.nome = :professorNome")
    Boolean findAtivoByNome(String professorNome);

    Page<ProfessorEntity> findAllByAtivoTrue(Pageable paginacao);

    ProfessorEntity findByIdAndAtivoTrue(Long id);
}
