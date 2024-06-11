package projetojsf;

import entidades.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
//import pbc.jsf.modelo.Usuario;


@ManagedBean
public class UsuarioBean {

    private Usuario usuario = new Usuario();

    public String doEfetuarLogin() {
        if("usuario01".equals(usuario.getLogin())
                && "123".equals(usuario.getSenha())) {
            return "tela_inserir.xhtml";
        } else {
            /* Cria uma mensagem. */
            FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
            /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
            FacesContext.getCurrentInstance().addMessage("Erro", msg);
            return null;
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
