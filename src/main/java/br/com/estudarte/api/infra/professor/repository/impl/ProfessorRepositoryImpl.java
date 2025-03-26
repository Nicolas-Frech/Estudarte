package br.com.estudarte.api.infra.professor.repository.impl;

import br.com.estudarte.api.infra.professor.ProfessorEntity;
import br.com.estudarte.api.application.professor.gateway.ProfessorRepository;
import br.com.estudarte.api.infra.professor.repository.ProfessorRepositoryJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProfessorRepositoryJpa jpaRepository;

    public ProfessorRepositoryImpl(ProfessorRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ProfessorEntity salvar(ProfessorEntity professor) {
        return jpaRepository.save(professor);
    }

    @Override
    public boolean existePorCnpj(String cnpj) {
        return jpaRepository.existsByCnpj(cnpj);
    }

    @Override
    public boolean existePorNome(String nome) {
        return jpaRepository.existsByNome(nome);
    }

    @Override
    public ProfessorEntity buscarPorId(Long id) {
        return entityManager.getReference(ProfessorEntity.class, id);
    }

    @Override
    public Page<ProfessorEntity> buscarTodosPorAtivoTrue(Pageable paginacao) {
        return jpaRepository.findAllByAtivoTrue(paginacao);
    }

    @Override
    public ProfessorEntity buscarPorIdEAtivoTrue(Long id) {
        return jpaRepository.findByIdAndAtivoTrue(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean buscarAtivoByNome(String nome) {
        return jpaRepository.findAtivoByNome(nome);
    }

    @Override
    public ProfessorEntity buscarPorNome(String nome) {
        return jpaRepository.findByNome(nome);
    }
}
