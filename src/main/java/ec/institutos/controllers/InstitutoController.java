package ec.institutos.controllers;

import java.util.List;

import ec.institutos.models.entities.Instituto;

public interface InstitutoController {

	public String guardar(Instituto entity, Boolean esNuevo);

	public String eliminar(Instituto entity);

	public Instituto buscar(Integer id);

	public List<Instituto> listarTodo();

	public Integer contar();

}
