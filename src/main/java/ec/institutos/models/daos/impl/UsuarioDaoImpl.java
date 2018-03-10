package ec.institutos.models.daos.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.institutos.models.daos.UsuarioDao;
import ec.institutos.models.entities.Usuario;

@Stateless
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	@PersistenceContext
	EntityManager em;

	public UsuarioDaoImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario findUsuario(String cedula) {
		Usuario result = new Usuario();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u FROM Usuario u ");
		sql.append("WHERE u.cedula = :cedula");

		Query query = em.createQuery(sql.toString());
		query.setParameter("cedula", cedula);

		if (!query.getResultList().isEmpty()) {
			result = (Usuario) query.getSingleResult();
		}
		return result;
	}

}
