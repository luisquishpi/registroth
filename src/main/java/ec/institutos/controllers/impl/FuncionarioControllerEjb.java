package ec.institutos.controllers.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.institutos.controllers.FuncionarioController;
import ec.institutos.models.daos.FuncionarioDao;
import ec.institutos.models.entities.Funcionario;

@Stateless
public class FuncionarioControllerEjb implements FuncionarioController {

	@Inject
	private FuncionarioDao funcionarioDao;

	public FuncionarioControllerEjb() {

	}

	@Override
	public String guardar(Funcionario entity, Boolean esNuevo) {
		String msg = null;
		if (esNuevo) {
			msg = funcionarioDao.create(entity);
		} else {
			msg = funcionarioDao.update(entity);
		}
		return msg;
	}

	@Override
	public String eliminar(Funcionario entity) {
		return funcionarioDao.delete(entity);
	}

	@Override
	public Funcionario buscar(Integer id) {
		return funcionarioDao.find(id);
	}

	@Override
	public List<Funcionario> listarTodo() {
		return funcionarioDao.findAll();
	}

	@Override
	public Integer contar() {
		return funcionarioDao.count();
	}

	@Override
	public Funcionario buscar(String cedula) {
		return funcionarioDao.findByCedula(cedula);
	}

}
