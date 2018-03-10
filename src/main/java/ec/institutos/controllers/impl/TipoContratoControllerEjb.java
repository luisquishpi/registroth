package ec.institutos.controllers.impl;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.institutos.controllers.TipoContratoController;
import ec.institutos.models.daos.TipoContratoDao;
import ec.institutos.models.entities.TipoContrato;

@Stateless
public class TipoContratoControllerEjb implements TipoContratoController {

	@Inject
	private TipoContratoDao tipoContratoDao;

	public TipoContratoControllerEjb() {

	}

	@Override
	public String guardar(TipoContrato entity, Boolean esNuevo) {
		String msg = null;
		if (esNuevo) {
			msg = tipoContratoDao.create(entity);
		} else {
			msg = tipoContratoDao.update(entity);
		}
		return msg;
	}

	@Override
	public String eliminar(TipoContrato entity) {
		return tipoContratoDao.delete(entity);
	}

	@Override
	public TipoContrato buscar(Integer id) {
		return tipoContratoDao.find(id);
	}

	@Override
	public List<TipoContrato> listarTodo() {
		return tipoContratoDao.findAll();
	}

	@Override
	public Integer contar() {
		return tipoContratoDao.count();
	}

}
