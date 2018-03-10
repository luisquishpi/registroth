package ec.institutos.views.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

import ec.institutos.controllers.UsuarioController;
import ec.institutos.models.entities.Instituto;
import ec.institutos.models.entities.Usuario;
import ec.institutos.utils.Encrypt;
import ec.institutos.utils.Mensajes;
import ec.institutos.utils.Utils;

@ManagedBean
@SessionScoped
public class UsuarioLoginBean {

	private static final Logger LOGGER = Logger.getLogger(UsuarioLoginBean.class);

	private Usuario usuario;
	private Instituto instituto;

	@EJB
	private UsuarioController usuarioController;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public void login() {
		Usuario usuarioTmp = new Usuario();
		usuarioTmp = usuarioController.buscar(this.usuario.getCedula());
		if (usuarioTmp.getId() != null) {
			if (Encrypt.sonIguales(usuario.getClave(), usuarioTmp.getClave())) {
				usuario = usuarioTmp;
				instituto=usuarioTmp.getInstituto();
				Utils.redirectToPage("/pages/escritorio/index.xhtml");
			} else {
				Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: Usuario o clave incorrecto ");
				LOGGER.error("Login: Inicio fallido, intento de: " + this.usuario.getCedula());
			}
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: Usuario o clave incorrecto ");
			LOGGER.error("Login: Inicio fallido, intento de: " + this.usuario.getCedula());
		}
	}

	public void logOut() {
		this.usuario = new Usuario();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		Utils.redirectToPage("/pages/login/index.xhtml");
	}

	public void checkLogedIn() {
		if (this.usuario == null)
			logOut();
		if (this.usuario.getId() == null)
			logOut();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}

}
