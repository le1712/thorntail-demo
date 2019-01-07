package br.com.le1712.thorntail.thorntaildemo.domain.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class RepositoryImpl<E extends PrimaryKeyEntity> extends RepositoryBaseImpl<E> {

	private static final long serialVersionUID = -9184156900369332897L;

	public RepositoryImpl(Class<E> entityClass) {
		super(entityClass);
	}

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}