package ec.institutos.models.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tipocontrato")
public class TipoContrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", unique = true, nullable = false, length = 50)
	private String nombre;

	@Column(name = "horasSemana", nullable = false)
	private Integer HorasSemana = 40;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaIngreso", nullable = false)
	private Date HoraIngreso;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaSalida", nullable = false)
	private Date HoraSalida;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	private Instituto instituto;

	public TipoContrato() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}

	@Override
	public String toString() {
		return "TipoContrato [id=" + id + ", nombre=" + nombre + "]";
	}

	public Integer getHorasSemana() {
		return HorasSemana;
	}

	public void setHorasSemana(Integer horasSemana) {
		HorasSemana = horasSemana;
	}

	public Date getHoraIngreso() {
		return HoraIngreso;
	}

	public void setHoraIngreso(Date horaIngreso) {
		HoraIngreso = horaIngreso;
	}

	public Date getHoraSalida() {
		return HoraSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		HoraSalida = horaSalida;
	}

	

}
