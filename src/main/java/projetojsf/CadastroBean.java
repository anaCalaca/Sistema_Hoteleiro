package projetojsf;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CadastroDAO;
import entidades.Cadastro;

@ViewScoped
@ManagedBean(name = "cadastroBean")
public class CadastroBean {
	private List<Cadastro> cadastros = new ArrayList<Cadastro>();
	
	public CadastroBean() {
		setCadastro(new Cadastro());;
	}

	public Cadastro cadastro;
	
	public Cadastro getCadastro() {
		return cadastro;
	}
	public void setCadastro(Cadastro cadastro) {
		this.cadastro = cadastro;
	}		
	public List<Cadastro> getCadastros() {
		return cadastros;
	}
	public void setCadastros(List<Cadastro> cadastros) {
		this.cadastros = cadastros;
	}
	
	public String inserirCadastro() {
		if (cadastro.getNome() == null || cadastro.getNome().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo nome é obrigatório.", null));
            return null;
        }
        if (cadastro.getSenha() == null || cadastro.getSenha().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo senha é obrigatório.", null));
            return null;
        }
		CadastroDAO cadastroDAO = new CadastroDAO();
		cadastroDAO.atualizarCadastro(cadastro);
		carregarCadastros();
		limparCadastro();
		return "";
	}

	public String removerCadastro() {
		CadastroDAO cadastroDAO = new CadastroDAO();
		cadastroDAO.removerPorId(getCadastro());
		carregarCadastros();
		limparCadastro();
		return "";
	}
		
		public void carregarCadastros() {
		CadastroDAO cadastroDAO = new CadastroDAO();
		setCadastros(cadastroDAO.getListEntity());
	}
	
	public String limparCadastro() {
		setCadastro(new Cadastro());
		return "";
	}


}












