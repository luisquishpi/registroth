package ec.institutos.controllers;

import java.util.Date;
import java.util.List;

import ec.institutos.models.entities.Registro;

public interface RegistroController {

	public String guardar(Registro entity, Boolean esNuevo);

	public String eliminar(Registro entity);

	public Registro buscar(Integer id);

	public List<Registro> buscarHoraIngresoSalida(String cedula, Date desde, Date hasta);

	public List<Object[]> buscarHoraIngresoSalidaObjetos(String cedula, Date fecha);

	public List<Registro> listarTodo();

	public Integer contar();

}
