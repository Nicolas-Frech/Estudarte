package br.com.estudarte.api.infra.aula;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
    public boolean existePorId(Long id) {
        return jpaRepository.existsById(id);
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
    public AulaEntity buscarPorIdEMotivoCancelamentoNull(Long id) {
        return jpaRepository.findByIdAndMotivoCancelamentoIsNull(id);
    }
}
