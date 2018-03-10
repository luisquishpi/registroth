package ec.institutos.models.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "instituto")
public class Instituto {

	public Instituto() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "ruc", unique = true, nullable = false, length = 13)
	private String ruc;

	@Column(name = "nombre", unique = false, nullable = false, length = 100)
	private String nombre;

	@Column(name = "direccion", unique = false, nullable = true, length = 100)
	private String direccion;

	@Column(name = "telefono", unique = false, nullable = true, length = 100)
	private String telefono;

	@Lob
	@Column(name = "logo", unique = false, columnDefinition = "mediumblob")
	private byte[] logo;

	@Column(name = "email", unique = false, nullable = true, length = 100)
	private String email;

	@Column(name = "url", unique = false, nullable = true, length = 100)
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
