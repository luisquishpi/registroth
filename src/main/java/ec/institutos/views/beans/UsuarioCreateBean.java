package ec.institutos.views.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

import ec.institutos.controllers.UsuarioController;
import ec.institutos.models.entities.Usuario;
import ec.institutos.utils.Encrypt;
import ec.institutos.utils.Mensajes;
import ec.institutos.utils.UsuarioTipo;

@ManagedBean
@ViewScoped
public class UsuarioCreateBean {

	private static final Logger LOGGER = Logger.getLogger(UsuarioCreateBean.class);

	private Usuario usuario;
	private String clave;

	@EJB
	private UsuarioController usuarioController;

	@ManagedProperty(value = "#{usuarioLoginBean}")
	private UsuarioLoginBean usuarioLoginBean;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public void guardar() {
		LOGGER.info("Init: Guardar");
		usuario.setClave(Encrypt.encriptar(usuario.getClave()));
		usuario.setInstituto(usuarioLoginBean.getInstituto());
		String result = usuarioController.guardar(usuario, true);
		if (result == null) {
			usuario = new Usuario();
			Mensajes.addMsg(FacesMessage.SEVERITY_INFO, " Guardado correctamente");
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
		}
	}

	public UsuarioTipo[] getUsuarioTipos() {
		return UsuarioTipo.values();
	}

	public void actualizar() {

	}

	public void eliminar() {

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

	public UsuarioLoginBean getUsuarioLoginBean() {
		return usuarioLoginBean;
	}

	public void setUsuarioLoginBean(UsuarioLoginBean usuarioLoginBean) {
		this.usuarioLoginBean = usuarioLoginBean;
	}

}
