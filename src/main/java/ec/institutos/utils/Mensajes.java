package ec.institutos.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensajes {

	public static void addMsg(Severity severity, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensaje, null));

	}
}
