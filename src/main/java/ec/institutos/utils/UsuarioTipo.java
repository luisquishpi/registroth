package ec.institutos.utils;

public enum UsuarioTipo {
	ADMINISTRADOR("Administrador"), TALENTO_HUMANO("Talento Humano"),SECRETARIA("Secretaría");

	private String description;

	private UsuarioTipo(String description) {
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
