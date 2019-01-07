package br.com.le1712.thorntail.thorntaildemo.domain.repository;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;

public abstract class RepositoryBaseImpl<E extends PrimaryKeyEntity> implements Repository<E> {

	private static final long serialVersionUID = 1468430959175375725L;

	private Class<E> entityClass;

	protected abstract EntityManager getEntityManager();

	//@Inject
	//private Logger logger;

	public RepositoryBaseImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void add(E entity) {
		try {
			saveOrUpdate(entity);
			this.getEntityManager().flush();
		} catch (Exception e) {
			//logger.error(e.getMessage());
		} finally {
			this.getEntityManager().clear();
		}
	}

	@Override
	public void remove(E entity) {
		try {
			this.getEntityManager().remove(this.getEntityManager().merge(entity));
			this.getEntityManager().flush();
		} catch (Exception e) {
			//logger.error(e.getMessage());
		} finally {
			this.getEntityManager().clear();
		}
	}

	@Override
	public E porId(final Object id) {
		try {
			return id == null ? null : this.getEntityManager().find(this.entityClass, id);
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E single(DetachedCriteria detachedCriteria) {
		try {
			return (E) criarCriteria(detachedCriteria).uniqueResult();
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> list(DetachedCriteria detachedCriteria) {
		try {
			return criarCriteria(detachedCriteria).list();
		} catch (NoResultException noResultException) {
			return Collections.emptyList();
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> listComNumeroMaximoRegistros(DetachedCriteria detachedCriteria, Integer numeroMaximoRegistros) {
		try {
			return criarCriteria(detachedCriteria).setMaxResults(numeroMaximoRegistros).list();
		} catch (NoResultException noResultException) {
			return Collections.emptyList();
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<?> listWithProjection(DetachedCriteria detachedCriteria) {
		return list(detachedCriteria);
	}

	protected void saveOrUpdate(E entity) {
		if (entity.getPrimaryKey() == null) {
			this.getEntityManager().persist(entity);
		} else {
			this.getEntityManager().merge(entity);
		}
	}

	protected void saveOnly(E entity) {
		this.getEntityManager().persist(entity);
	}

	private Criteria criarCriteria(DetachedCriteria detachedCriteria) {
		return detachedCriteria.getExecutableCriteria(getEntityManager().unwrap(Session.class));
	}
}
