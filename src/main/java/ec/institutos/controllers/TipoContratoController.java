package ec.institutos.controllers;

import java.util.List;

import ec.institutos.models.entities.TipoContrato;

public interface TipoContratoController {

	public String guardar(TipoContrato entity, Boolean esNuevo);

	public String eliminar(TipoContrato entity);

	public TipoContrato buscar(Integer id);

	public List<TipoContrato> listarTodo();

	public Integer contar();

}
