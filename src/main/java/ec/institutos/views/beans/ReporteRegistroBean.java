package ec.institutos.views.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import ec.institutos.controllers.RegistroController;
import ec.institutos.models.entities.Registro;

@ManagedBean
@ViewScoped
public class ReporteRegistroBean {

	private static final Logger LOGGER = Logger.getLogger(ReporteRegistroBean.class);

	private List<Registro> registros;
	private String cedula;
	private Date desde;
	private Date hasta;

	@Temporal(TemporalType.DATE)
	Date fecha;

	@EJB
	private RegistroController registroController;

	@ManagedProperty(value = "#{usuarioLoginBean}")
	private UsuarioLoginBean usuarioLoginBean;

	private List<Object[]> lista;

	@PostConstruct
	public void update() {
		lista = new ArrayList<Object[]>();
	}

	public void buscar() {
		lista.clear();
		LOGGER.info("Init: process of Buscar");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Integer yDesde = Integer.parseInt(sdf.format(desde));
		Integer yHasta = Integer.parseInt(sdf.format(hasta));

		sdf = new SimpleDateFormat("MM");
		Integer mDesde = Integer.parseInt(sdf.format(desde));
		Integer mHasta = Integer.parseInt(sdf.format(hasta));

		sdf = new SimpleDateFormat("dd");
		Integer dDesde = Integer.parseInt(sdf.format(desde));
		Integer dHasta = Integer.parseInt(sdf.format(hasta));

		for (int y = yDesde; y <= yHasta; y++) {
			for (int m = mDesde; m <= mHasta; m++) {
				Integer dDesdeFor = 1;
				Integer dHastaFor = 31;
				if (m == mDesde)
					dDesdeFor = dDesde;
				if (m == mHasta)
					dHastaFor = dHasta;

				for (int d = dDesdeFor; d <= dHastaFor; d++) {
					String dd = "" + d;
					String mm = "" + m;
					if (d < 10)
						dd = "0" + d;
					if (m < 10)
						mm = "0" + m;
					SimpleDateFormat sdfC = new SimpleDateFormat("dd/MM/yyyy");
					Date date = null;
					try {
						date = sdfC.parse(dd + "/" + mm + "/" + y);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					LocalDate dt = LocalDate.of(y, Integer.parseInt(mm), Integer.parseInt(dd));
					if (dt.getDayOfWeek().toString() != "SATURDAY" && dt.getDayOfWeek().toString() != "SUNDAY") {
						if (cedula.trim().isEmpty() || cedula == null) {
							lista.addAll(registroController.buscarHoraIngresoSalidaObjetos(null, date));
						} else {
							lista.addAll(registroController.buscarHoraIngresoSalidaObjetos(cedula, date));
						}
					}
				}
			}
		}
		/*
		 * if (cedula.trim().isEmpty() || cedula == null) { lista =
		 * registroController.buscarHoraIngresoSalidaObjetos(null, desde,
		 * hasta); } else { lista =
		 * registroController.buscarHoraIngresoSalidaObjetos(cedula, desde,
		 * hasta); }
		 */
		LOGGER.info("Finish: process of Buscar");
	}

	public String getApellidosNombres(String apellido, String nombre) {
		return apellido + ' ' + nombre;
	}

	public String getHoraEntrada(String horas) {
		String[] s = horas.split(",");
		if (s.length > 0) {
			return s[0].toString();
		}
		return "";
	}

	public String getHoraSalida(String horas) {
		String[] s = horas.split(",");
		String hs = "";
		if (s.length > 0) {
			hs = s[s.length - 1].toString();
		}
		if (!hs.equals(getHoraEntrada(horas))) {
			return hs;
		}
		return "";
	}

	public String getHoraSalidaAlmuerzo(String horas) {
		String[] s = horas.split(",");
		if (s.length > 2) {
			return s[1].toString();
		}
		return "";
	}

	public String getHoraRegresoAlmuerzo(String horas) {
		String[] s = horas.split(",");
		if (s.length > 3) {
			return s[2].toString();
		}
		return "";
	}

	public String getFecha(String fecha) {
		return fecha.substring(0, 10);

	}

	public String getNombreArchivo() {
		return "Reporte_EntradaSalida_Personal_" + usuarioLoginBean.getInstituto().getNombre();
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> tipoContratos) {
		this.registros = tipoContratos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public UsuarioLoginBean getUsuarioLoginBean() {
		return usuarioLoginBean;
	}

	public void setUsuarioLoginBean(UsuarioLoginBean usuarioLoginBean) {
		this.usuarioLoginBean = usuarioLoginBean;
	}

	public List<Object[]> getLista() {
		return lista;
	}

	public void setLista(List<Object[]> lista) {
		this.lista = lista;
	}

}
