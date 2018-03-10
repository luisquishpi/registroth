package ec.institutos.controllers.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.institutos.controllers.InstitutoController;
import ec.institutos.models.daos.InstitutoDao;
import ec.institutos.models.entities.Instituto;

@Stateless
public class InstitutoControllerEjb implements InstitutoController {

	@Inject
	private InstitutoDao institutoDao;

	public InstitutoControllerEjb() {

	}

	@Override
	public String guardar(Instituto entity, Boolean esNuevo) {
		String msg = null;
		if (esNuevo) {
			msg = institutoDao.create(entity);
		} else {
			msg = institutoDao.update(entity);
		}
		return msg;
	}

	@Override
	public String eliminar(Instituto entity) {
		return institutoDao.delete(entity);
	}

	@Override
	public Instituto buscar(Integer id) {
		return institutoDao.find(id);
	}

	@Override
	public List<Instituto> listarTodo() {
		return institutoDao.findAll();
	}

	@Override
	public Integer contar() {
		return institutoDao.count();
	}

}
