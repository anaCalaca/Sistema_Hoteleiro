package projetojsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ConsumoDAO;
import entidades.Consumo;
import entidades.Cliente;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ConsumoBean {

	private Long clienteId;
	private String produto;
	private int quantidade;
	private double valor;
	private List<Consumo> consumos;
	private List<Consumo> consumo;

	public void registrarConsumo() {
		ConsumoDAO consumoDao = new ConsumoDAO();
		Consumo consumo = new Consumo();

		Cliente cliente = new Cliente();
		cliente.setId(clienteId); // Define o ID do cliente
		consumo.setCliente(cliente);
		consumo.setProduto(produto);
		consumo.setQuantidade(quantidade);
		consumo.setDataConsumo(new Date()); // Define a data de consumo como a data atual
		consumo.setValor(valor);

		consumoDao.registrarConsumo(consumo);
		listarConsumos(); // Atualiza a lista de consumos após o registro
	}

	public void listarConsumos() {
		ConsumoDAO consumoDao = new ConsumoDAO();
		consumos = consumoDao.getConsumosPorCliente(clienteId);
	}

	public List<Consumo> getConsumo() {
		return consumos;
	}

	public void setConsumo(List<Consumo> consumo) {
		this.consumo = consumo;
	}

	// Getters e Setters
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}
}
