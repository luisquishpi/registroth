package ec.institutos.views.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import ec.institutos.controllers.FuncionarioController;
import ec.institutos.models.entities.Funcionario;
import ec.institutos.utils.Seguridad;
import ec.institutos.utils.Utils;

@ManagedBean
@ViewScoped
public class FuncionarioIndexBean {

	private static final Logger LOGGER = Logger.getLogger(FuncionarioIndexBean.class);

	private List<Funcionario> funcionarios;

	@EJB
	private FuncionarioController funcionarioController;

	@PostConstruct
	public void update() {
		LOGGER.info("Init: process of TipoContratoIndexBean");
		setFuncionarios(funcionarioController.listarTodo());
		LOGGER.info("Finish: process of TipoContratoIndexBean");
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public void goToEdit(Integer id) throws Exception {
		String Url = "/pages/funcionario/create.xhtml?a=" + Seguridad.encode(id.toString());
		Utils.redirectToPage(Url);
	}

}
