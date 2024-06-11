package projetojsf;

import dao.ProdutoDAO;
import entidades.Produto;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@RequestScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean {

    private Produto produto = new Produto();
    private List<Produto> itens;
    private ProdutoDAO produtoDAO;

    public ProdutoBean() {
        produtoDAO = new ProdutoDAO();
        listarProdutos(); // Carrega a lista de produtos no construtor
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    // Operação de criar produto
    public String inserirProduto() {
        produtoDAO.inserirProduto(produto);
        limparProduto(); // Limpa os campos após inserção
        listarProdutos(); // Atualiza a lista de produtos após inserção
        return "";
    }

    // Operação de atualizar produto
    public String atualizarProduto() {
        produtoDAO.atualizarProduto(produto);
        limparProduto(); // Limpa os campos após atualização
        listarProdutos(); // Atualiza a lista de produtos após atualização
        return "";
    }

    // Operação de remover produto
    public String removerProduto(Produto produto) {
        produtoDAO.removerProduto(produto);
        listarProdutos(); // Atualiza a lista de produtos após exclusão
        return "";
    }

    // Operação de listar produtos
    public void listarProdutos() {
        itens = produtoDAO.listarProdutos();
    }

    // Limpa os campos do produto após inserção ou atualização
    private void limparProduto() {
        produto = new Produto();
    }

//    public List<Produto> getListaProdutos() {
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        return produtoDAO.getListEntity();
//    }

    private Produto produtoSelecionado;



    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    private List<Produto> listaProdutos;

    public List<Produto> getListaProdutos() {
        if (listaProdutos == null) {
            ProdutoDAO produtoDao = new ProdutoDAO();
            listaProdutos = produtoDao.listarProdutos();
        }
        return listaProdutos;
    }

}
