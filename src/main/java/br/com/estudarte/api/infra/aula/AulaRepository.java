package br.com.estudarte.api.infra.aula;

import br.com.estudarte.api.domain.aula.MotivoCancelamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AulaRepository extends JpaRepository<AulaEntity, Long> {
    Boolean existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(String professorNome, LocalDateTime data);

    Boolean existsByAlunoNomeAndDataAndMotivoCancelamentoIsNull(String alunoNome, LocalDateTime data);
}
