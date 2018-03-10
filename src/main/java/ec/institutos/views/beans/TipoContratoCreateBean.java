package ec.institutos.views.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import ec.institutos.controllers.TipoContratoController;
import ec.institutos.models.entities.TipoContrato;
import ec.institutos.utils.Mensajes;
import ec.institutos.utils.Seguridad;

@ManagedBean
@ViewScoped
public class TipoContratoCreateBean {

	private static final Logger LOGGER = Logger.getLogger(TipoContratoCreateBean.class);

	private TipoContrato tipoContrato;

	@EJB
	private TipoContratoController tipoContratoController;

	@ManagedProperty(value = "#{usuarioLoginBean}")
	private UsuarioLoginBean usuarioLoginBean;

	private String HoraIngreso;
	private String HoraSalida;

	private Boolean esNuevo;

	@PostConstruct
	public void init() {
		tipoContrato = new TipoContrato();
		LoadToEdit();
	}

	private void LoadToEdit() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (params.size() > 0) {
			Integer id = 0;
			try {
				id = Integer.parseInt(Seguridad.decode(params.get("a")));
				tipoContrato = tipoContratoController.buscar(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (tipoContrato != null) {
				setEsNuevo(false);
				HoraIngreso=tipoContrato.getHoraIngreso().toString();
				HoraSalida=tipoContrato.getHoraSalida().toString();
			}
		} else {
			setEsNuevo(true);
		}

	}

	public void guardar() {
		LOGGER.info("Init: Guardar");
		tipoContrato.setInstituto(usuarioLoginBean.getInstituto());
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try {
			tipoContrato.setHoraIngreso(formatter.parse(HoraIngreso));
			tipoContrato.setHoraSalida(formatter.parse(HoraSalida));
		} catch (ParseException e) {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: Formato de Horas incorrecto");
			e.printStackTrace();
			return;
		}
		String result = tipoContratoController.guardar(tipoContrato, esNuevo);
		if (result == null) {
			tipoContrato = new TipoContrato();
			HoraIngreso = "";
			HoraSalida = "";
			Mensajes.addMsg(FacesMessage.SEVERITY_INFO, " Guardado correctamente");
			esNuevo = true;
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
		}
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public UsuarioLoginBean getUsuarioLoginBean() {
		return usuarioLoginBean;
	}

	public void setUsuarioLoginBean(UsuarioLoginBean usuarioLoginBean) {
		this.usuarioLoginBean = usuarioLoginBean;
	}

	public String getHoraIngreso() {
		return HoraIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		HoraIngreso = horaIngreso;
	}

	public String getHoraSalida() {
		return HoraSalida;
	}

	public void setHoraSalida(String horaSalida) {
		HoraSalida = horaSalida;
	}

	public Boolean getEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(Boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

}
