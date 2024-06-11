package projetojsf;

import dao.ClienteDAO;
import entidades.Cliente;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@ManagedBean(name = "clienteBean")
public class ClienteBean {
    private List<Cliente> clientes = new ArrayList<Cliente>();

    public ClienteBean() {
        setCliente(new Cliente());
        atualizarClientes();  // Carrega os clientes ao inicializar o bean
    }

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }




    public String inserirCliente() {
        System.out.println("iniciou inserir");
        System.out.println(cliente);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.atualizarCliente(cliente);
        atualizarClientes();
        limparCliente();
//        redirecionar();
        return "";
    }
//    public String inserirCliente() {
//        ClienteDAO clienteDAO = new ClienteDAO();
//        if (cliente.getId() == null) {
//            // Cliente novo
//            clienteDAO.salvarCliente(cliente);
//        } else {
//            // Cliente existente, atualiza
//            clienteDAO.atualizarCliente(cliente);
//        }
//        carregarClientes();
//        limparCliente();
//        return "";
//    }


    public String removerCliente(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.removerPorId(cliente);
        atualizarClientes();
        limparCliente();
        return "";
    }

    public void editarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atualizarClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        setClientes(clienteDAO.getListEntity());
    }

    public String limparCliente() {
        setCliente(new Cliente());
        return "";
    }

    public List<Cliente> getListaClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.getListEntity();
    }

    private Cliente clienteSelecionado;



    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }




    public void test() {
        System.out.println("==========Entrou==========");
        System.out.println(cliente);
    }
}
