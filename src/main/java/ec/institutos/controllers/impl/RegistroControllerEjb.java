package ec.institutos.controllers.impl;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.institutos.controllers.RegistroController;
import ec.institutos.models.daos.RegistroDao;
import ec.institutos.models.entities.Registro;

@Stateless
public class RegistroControllerEjb implements RegistroController {

	@Inject
	private RegistroDao registroDao;

	public RegistroControllerEjb() {

	}

	@Override
	public String guardar(Registro entity, Boolean esNuevo) {
		String msg = null;
		if (esNuevo) {
			msg = registroDao.create(entity);
		} else {
			msg = registroDao.update(entity);
		}
		return msg;
	}

	@Override
	public String eliminar(Registro entity) {
		return registroDao.delete(entity);
	}

	@Override
	public Registro buscar(Integer id) {
		return registroDao.find(id);
	}

	@Override
	public List<Registro> listarTodo() {
		return registroDao.findAll();
	}

	@Override
	public Integer contar() {
		return registroDao.count();
	}

	@Override
	public List<Registro> buscarHoraIngresoSalida(String cedula, Date desde, Date hasta) {
		return registroDao.findHoraIngresoSalida(cedula, desde, hasta);
	}

	@Override
	public List<Object[]> buscarHoraIngresoSalidaObjetos(String cedula, Date fecha) {
		return registroDao.findHoraIngresoSalidaObject(cedula, fecha);
	}

}
