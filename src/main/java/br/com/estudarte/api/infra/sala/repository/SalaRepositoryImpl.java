package br.com.estudarte.api.infra.sala.repository;


import br.com.estudarte.api.infra.sala.SalaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class SalaRepositoryImpl implements SalaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final SalaRepositoryJpa jpaRepository;

    public SalaRepositoryImpl(SalaRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public SalaEntity salvar(SalaEntity sala) {
        return jpaRepository.save(sala);
    }

    @Override
    public Page<SalaEntity> buscarTodosPorAtivoTrue(Pageable paginacao) {
        return jpaRepository.findAllByAtivoTrue(paginacao);
    }

    @Override
    public SalaEntity buscarPorIdEAtivoTrue(Long id) {
        return jpaRepository.findByIdAndAtivoTrue(id);
    }

    @Override
    public boolean existePorIdEAtivoTrue(Long id) {
        return jpaRepository.existsByIdAndAtivoTrue(id);
    }

    @Override
    public boolean existePorNome(String nome) {
        return jpaRepository.existsByNome(nome);
    }

    @Override
    public SalaEntity buscarPorId(Long id) {
        return entityManager.getReference(SalaEntity.class, id);
    }
}
