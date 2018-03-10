package ec.institutos.models.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import ec.institutos.utils.UsuarioTipo;;

@Entity
@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cedula", unique = true, nullable = false, length = 10)
	private String cedula;

	@Column(name = "clave", nullable = false, length = 250)
	private String clave;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;

	@Column(name = "telefono", nullable = true, length = 50)
	private String telefono;

	@Column(name = "usuarioTipo", nullable = false)
	private UsuarioTipo usuarioTipo;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	private Instituto instituto;

	public Usuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", cedula=" + cedula + "]";
	}

}
