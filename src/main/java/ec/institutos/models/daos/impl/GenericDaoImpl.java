package ec.institutos.models.daos.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import ec.institutos.models.daos.GenericDao;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	@PersistenceContext(unitName = "registrothpu")
	protected EntityManager em;

	private final Class<T> entityClass;

	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void setEm(final EntityManager em) {
		this.em = em;
	}

	@Override
	public String create(T entity) {
		String error = "Error!";
		try {
			em.persist(entity);
			error = null;
		} catch (javax.persistence.PersistenceException err) {
			final Throwable cause = err.getCause();
			System.out.println("--> " + cause);
			// err.printStackTrace();
			if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
				error = "Datos duplicados, ya existen en la base de datos";
			} else if (cause instanceof org.hibernate.exception.DataException) {
				error = "Campos incorrectos, posible causa: sobrepasa número de carateres permitidos";
			} else {
				error = "No se pudo guardar. Clave:" + err.getCause();
			}
		}
		return error;
	}

	@Override
	public String update(T entity) {
		String error = "Error!";
		try {
			em.merge(entity);
			error = null;
		} catch (javax.persistence.PersistenceException err) {
			final Throwable cause = err.getCause();
			System.out.println("--> " + cause);
			// err.printStackTrace();
			if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
				error = "Datos duplicados, ya existen en la base de datos";
			} else if (cause instanceof org.hibernate.exception.DataException) {
				error = "Campos incorrectos, posible causa: sobrepasa número de carateres permitidos";
			} else {
				error = "No se pudo actualizar. Clave:" + err.getCause();
			}
		}

		return error;
	}

	@Override
	public String delete(T entity) {
		String error = "Error!";
		try {
			em.remove(entity);
			error = null;
		} catch (javax.persistence.PersistenceException err) {
			final Throwable cause = err.getCause();
			System.out.println("--> " + cause);
			// err.printStackTrace();
			if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
				error = "No se pudo eliminar, Datos estan siendo usados";
			} else {
				error = "No se pudo guardar. Clave:" + err.getCause();
			}
		}

		return error;
	}

	@Override
	public T find(ID id) {
		return em.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		em.flush();
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
		CriteriaQuery<T> all = cq.select(cq.from(entityClass));
		return em.createQuery(all).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findRange(int[] range) {
		em.flush();
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		Query q = em.createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer count() {
		em.flush();
		CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
		Root<T> rt = cq.from(entityClass);
		cq.select((Selection<? extends T>) em.getCriteriaBuilder().count(rt));
		Query q = em.createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
