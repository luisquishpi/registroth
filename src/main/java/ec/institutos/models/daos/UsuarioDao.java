package ec.institutos.models.daos;

import javax.ejb.Local;

import ec.institutos.models.entities.Usuario;

@Local
public interface UsuarioDao extends GenericDao<Usuario, Integer> {

	Usuario findUsuario(String cedula);
}
