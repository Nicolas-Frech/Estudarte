package br.com.estudarte.api.infra.aula.repository.impl;

import br.com.estudarte.api.infra.aula.AulaEntity;
import br.com.estudarte.api.application.aula.gateway.AulaRepository;
import br.com.estudarte.api.infra.aula.repository.AulaRepositoryJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class AulaRepositoryImpl implements AulaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AulaRepositoryJpa jpaRepository;

    public AulaRepositoryImpl(AulaRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AulaEntity salvar(AulaEntity aula) {
        return jpaRepository.save(aula);
    }

    @Override
    public AulaEntity buscarPorId(Long id) {
        return entityManager.getReference(AulaEntity.class, id);
    }

    @Override
    public Page<AulaEntity> buscarTodosPorMotivoCancelamentoNull(Pageable paginacao) {
        return jpaRepository.findAllByMotivoCancelamentoIsNull(paginacao);
    }

    @Override
    public Page<AulaEntity> buscarTodosPorAlunoNomeEMotivoCancelamentoNull(Pageable paginacao, String alunoNome) {
        return jpaRepository.findAllByAlunoNomeAndMotivoCancelamentoIsNull(paginacao, alunoNome);
    }

    @Override
    public Page<AulaEntity> buscarTodosPorProfessorNomeEMotivoCancelamentoNull(Pageable paginacao, String professorNome) {
        return jpaRepository.findAllByProfessorNomeAndMotivoCancelamentoIsNull(paginacao, professorNome);
    }

    @Override
    public boolean existePorProfessorNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data) {
        return jpaRepository.existsByProfessorNomeAndDataAndMotivoCancelamentoIsNull(nome, data);
    }

    @Override
    public boolean existePorAlunoNomeEDataEMotivoCancelamentoNull(String nome, LocalDateTime data) {
        return jpaRepository.existsByAlunoNomeAndDataAndMotivoCancelamentoIsNull(nome, data);
    }

    @Override
    public boolean existePorDataEIdEMotivoCancelamentoNull(LocalDateTime data, Long id) {
        return jpaRepository.existsByDataAndIdAndMotivoCancelamentoIsNull(data, id);
    }

    @Override
    public boolean existePorIdEMotivoCancelamentoNull(Long id) {
        return jpaRepository.existsByIdAndMotivoCancelamentoIsNull(id);
    }
}
