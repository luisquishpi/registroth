package ec.institutos.views.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import ec.institutos.controllers.FuncionarioController;
import ec.institutos.controllers.TipoContratoController;
import ec.institutos.models.entities.Funcionario;
import ec.institutos.models.entities.TipoContrato;
import ec.institutos.utils.Mensajes;
import ec.institutos.utils.Seguridad;

@ManagedBean
@ViewScoped
public class FuncionarioCreateBean {

	private static final Logger LOGGER = Logger.getLogger(FuncionarioCreateBean.class);

	private Funcionario funcionario;
	private List<TipoContrato> tipoContratos;

	@EJB
	private FuncionarioController funcionarioController;
	@EJB
	private TipoContratoController tipoContratoController;

	private Boolean esNuevo;

	@PostConstruct
	public void init() {
		funcionario = new Funcionario();
		setTipoContratos(tipoContratoController.listarTodo());
		LoadToEdit();
	}

	private void LoadToEdit() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (params.size() > 0) {
			Integer id = 0;
			try {
				id = Integer.parseInt(Seguridad.decode(params.get("a")));
				funcionario = funcionarioController.buscar(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (funcionario != null) {
				setEsNuevo(false);
			}
		} else {
			setEsNuevo(true);
		}

	}

	public void guardar() {
		LOGGER.info("Init: Guardar");
		funcionario.setFechaCreacion(new Date());
		String result = funcionarioController.guardar(funcionario, esNuevo);
		if (result == null) {
			funcionario = new Funcionario();
			Mensajes.addMsg(FacesMessage.SEVERITY_INFO, " Guardado correctamente");
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
		}
	}

	public void actualizar() {

	}

	public void eliminar() {

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<TipoContrato> getTipoContratos() {
		return tipoContratos;
	}

	public void setTipoContratos(List<TipoContrato> tipoContratos) {
		this.tipoContratos = tipoContratos;
	}

	public Boolean getEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(Boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

}
