package ec.institutos.controllers;

import java.util.List;

import ec.institutos.models.entities.Funcionario;

public interface FuncionarioController {

	public String guardar(Funcionario entity, Boolean esNuevo);

	public String eliminar(Funcionario entity);

	public Funcionario buscar(Integer id);
	
	public Funcionario buscar(String cedula);

	public List<Funcionario> listarTodo();

	public Integer contar();

}
