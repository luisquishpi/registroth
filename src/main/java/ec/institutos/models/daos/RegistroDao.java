package ec.institutos.models.daos;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.institutos.models.entities.Registro;

@Local
public interface RegistroDao extends GenericDao<Registro, Integer> {

	List<Registro> findHoraIngresoSalida(String cedula, Date desde, Date hasta);

	List<Object[]> findHoraIngresoSalidaObject(String cedula, Date fecha);
}
