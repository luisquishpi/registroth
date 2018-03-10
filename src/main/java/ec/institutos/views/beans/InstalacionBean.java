package ec.institutos.views.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.institutos.controllers.InstitutoController;
import ec.institutos.controllers.UsuarioController;
import ec.institutos.models.entities.Instituto;
import ec.institutos.models.entities.Usuario;
import ec.institutos.utils.Encrypt;
import ec.institutos.utils.Mensajes;
import ec.institutos.utils.UsuarioTipo;
import ec.institutos.utils.Utils;

@ManagedBean
@ViewScoped
public class InstalacionBean {

	private Usuario usuario;
	private Instituto instituto;
	private String clave;

	@EJB
	private UsuarioController usuarioController;
	@EJB
	private InstitutoController institutoController;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		instituto = new Instituto();
		Integer contarEmpresa = institutoController.contar();
		Integer contarUsuarios = usuarioController.contar();
		if (contarEmpresa > 0 || contarUsuarios > 0) {
			Utils.redirectToPage("/pages/login/index.xhtml");
		}
	}

	public void guardar() {
		String result = institutoController.guardar(instituto, true);
		if (result == null) {
			usuario.setUsuarioTipo(UsuarioTipo.ADMINISTRADOR);
			usuario.setClave(Encrypt.encriptar(usuario.getClave()));
			usuario.setInstituto(instituto);
			result = usuarioController.guardar(usuario, true);
			if (result == null) {
				usuario = new Usuario();
				instituto = new Instituto();
				Utils.redirectToPage("/pages/instalacion/exito.xhtml");
			} else {
				institutoController.eliminar(instituto);
				Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
			}
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
		}

	}

	public boolean existeParametros() {
		return false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto empresa) {
		this.instituto = empresa;
	}

}
