package ec.institutos.views.beans;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

import ec.institutos.controllers.FuncionarioController;
import ec.institutos.controllers.RegistroController;
import ec.institutos.models.entities.Funcionario;
import ec.institutos.models.entities.Registro;
import ec.institutos.utils.Mensajes;

@ManagedBean
@ViewScoped
public class RegistroCreateBean {

	private static final Logger LOGGER = Logger.getLogger(RegistroCreateBean.class);

	private Registro registro;
	private Funcionario funcionario;
	private String cedula;

	@EJB
	private RegistroController registroController;
	@EJB
	private FuncionarioController funcionarioController;

	@PostConstruct
	public void init() {
		setRegistro(new Registro());
		setFuncionario(new Funcionario());
		cedula = "";
	}

	public void guardar() {
		LOGGER.info("Init: Guardar");
		buscarFuncionario(cedula);
		if (funcionario.getNombre() != null) {
			Date fecha = new Date();
			registro.setFecha(fecha);
			registro.setHora(fecha);
			registro.setFuncionario(funcionario);
			String result = registroController.guardar(registro, true);
			if (result == null) {
				Mensajes.addMsg(FacesMessage.SEVERITY_INFO, "Gracias! "+funcionario.getApellido()+" "+funcionario.getNombre());
				registro = new Registro();
				funcionario = new Funcionario();
				setCedula("");
			} else {
				Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, " Error: " + result);
			}
		} else {
			Mensajes.addMsg(FacesMessage.SEVERITY_ERROR, "Funcionario no encontrado");
		}

	}

	private void buscarFuncionario(String cedula) {
		funcionario = funcionarioController.buscar(cedula);
		System.out.println("--> Funcionario: " + funcionario.getNombre());

	}

	public void actualizar() {

	}

	public void eliminar() {

	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
