package ec.institutos.views.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ec.institutos.controllers.TipoContratoController;
import ec.institutos.models.entities.TipoContrato;
import ec.institutos.utils.Seguridad;
import ec.institutos.utils.Utils;

@ManagedBean
@ViewScoped
public class TipoContratoIndexBean {

	private static final Logger LOGGER = Logger.getLogger(TipoContratoIndexBean.class);

	private List<TipoContrato> tipoContratos;

	@EJB
	private TipoContratoController tipoContratoController;

	@PostConstruct
	public void update() {
		LOGGER.info("Init: process of TipoContratoIndexBean");
		setTipoContratos(tipoContratoController.listarTodo());
		LOGGER.info("Finish: process of TipoContratoIndexBean");
	}

	public List<TipoContrato> getTipoContratos() {
		return tipoContratos;
	}

	public void setTipoContratos(List<TipoContrato> tipoContratos) {
		this.tipoContratos = tipoContratos;
	}

	public void goToEdit(Integer id) throws Exception {
		String Url = "/pages/tipocontrato/create.xhtml?a=" + Seguridad.encode(id.toString());
		Utils.redirectToPage(Url);
	}

}
