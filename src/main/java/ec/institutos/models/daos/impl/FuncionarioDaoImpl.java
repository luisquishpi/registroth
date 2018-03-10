package ec.institutos.models.daos.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.institutos.models.daos.FuncionarioDao;
import ec.institutos.models.entities.Funcionario;

@Stateless
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario, Integer> implements FuncionarioDao {
	@PersistenceContext
	EntityManager em;

	public FuncionarioDaoImpl() {

		super(Funcionario.class);
	}

	@Override
	public Funcionario findByCedula(String cedula) {
		Funcionario result = new Funcionario();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u FROM Funcionario u ");
		sql.append("WHERE u.cedula = :cedula");

		Query query = em.createQuery(sql.toString());
		query.setParameter("cedula", cedula);

		if (!query.getResultList().isEmpty()) {
			result = (Funcionario) query.getSingleResult();
		}
		return result;
	}

}
