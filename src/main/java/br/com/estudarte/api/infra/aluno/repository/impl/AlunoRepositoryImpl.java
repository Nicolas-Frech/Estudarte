package br.com.estudarte.api.infra.aluno.repository.impl;

import br.com.estudarte.api.infra.aluno.AlunoEntity;
import br.com.estudarte.api.application.aluno.gateway.AlunoRepository;
import br.com.estudarte.api.infra.aluno.repository.AlunoRepositoryJpa;
import br.com.estudarte.api.infra.professor.ProfessorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AlunoRepositoryJpa jpaRepository;

    public AlunoRepositoryImpl(AlunoRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public AlunoEntity salvar(AlunoEntity aluno) {
        return jpaRepository.save(aluno);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existePorNome(String nome) {
        return jpaRepository.existsByNome(nome);
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        return entityManager.getReference(AlunoEntity.class, id);
    }

    @Override
    public Page<AlunoEntity> buscarTodosPorAtivoTrue(Pageable paginacao) {
        return jpaRepository.findAllByAtivoTrue(paginacao);
    }

    @Override
    public AlunoEntity buscarPorIdEAtivoTrue(Long id) {
        return jpaRepository.findByIdAndAtivoTrue(id);
    }

    @Override
    public boolean buscarAtivoByNome(String nome) {
        return jpaRepository.findAtivoByNome(nome);
    }

    @Override
    public AlunoEntity buscarPorNome(String nome) {
        return jpaRepository.findByNome(nome);
    }
}
