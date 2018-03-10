package ec.institutos.models.daos;

import javax.ejb.Local;

import ec.institutos.models.entities.Funcionario;

@Local
public interface FuncionarioDao extends GenericDao<Funcionario, Integer> {
	Funcionario findByCedula(String cedula);
}
