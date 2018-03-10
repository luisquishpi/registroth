package ec.institutos.utils;

public enum TipoFuncionario {
	DOCENTE("Docente"), ADMINISTRATIVO("Administrativo");

	private String description;

	private TipoFuncionario(String description) {
		this.description = description;
	}

	public String getUsuarioTipo() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}

}
