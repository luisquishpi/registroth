package ec.institutos.models.daos.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.institutos.models.daos.InstitutoDao;
import ec.institutos.models.entities.Instituto;


@Stateless
public class InstitutoDaoImpl extends GenericDaoImpl<Instituto, Integer> implements InstitutoDao {
	@PersistenceContext
	EntityManager em;

	public InstitutoDaoImpl() {

		super(Instituto.class);
	}

}
