package br.com.estudarte.api.infra.aula;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<AulaEntity, Long> {
}
