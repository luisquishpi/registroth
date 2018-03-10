package ec.institutos.models.daos.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.institutos.models.daos.TipoContratoDao;
import ec.institutos.models.entities.TipoContrato;

@Stateless
public class TipoContratoDaoImpl extends GenericDaoImpl<TipoContrato, Integer> implements TipoContratoDao {
	@PersistenceContext
	EntityManager em;

	public TipoContratoDaoImpl() {

		super(TipoContrato.class);
	}

}
