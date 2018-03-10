package ec.institutos.views.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ec.institutos.controllers.InstitutoController;
import ec.institutos.models.entities.Instituto;
import ec.institutos.utils.Mensajes;

@ManagedBean
@ViewScoped
public class InstitutoIndexBean {

	private static final Logger LOGGER = Logger.getLogger(InstitutoIndexBean.class);

	private Instituto instituto;
	private UploadedFile file;

	@EJB
	private InstitutoController institutoController;

	@ManagedProperty(value = "#{usuarioLoginBean}")
	private UsuarioLoginBean usuarioLoginBean;

	@PostConstruct
	public void init() {
		instituto = new Instituto();
		instituto = usuarioLoginBean.getInstituto();
	}

	public StreamedContent getImageFromDB() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			byte[] image = null;
			image = new Instituto().getLogo();

			return new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpg");

		}
	}

	public void guardar() {
		LOGGER.info("Init: Actualizar");
		try {
			byte[] imgData = getByte(file.getInputstream());
			instituto.setLogo(imgData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = institutoController.guardar(instituto, false);
		if (result == null) {
			Mensajes.addMsg(FacesMessage.SEVERITY_INFO, " Actualizado correctamente");
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
		}
	}

	public static byte[] getByte(InputStream input) throws IOException {
		byte[] buffer = new byte[8192];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		return output.toByteArray();
	}

	public UsuarioLoginBean getUsuarioLoginBean() {
		return usuarioLoginBean;
	}

	public void setUsuarioLoginBean(UsuarioLoginBean usuarioLoginBean) {
		this.usuarioLoginBean = usuarioLoginBean;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
