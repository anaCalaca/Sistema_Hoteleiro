package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import entidades.Consumo;

public class ConsumoDAO {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");

	public void registrarConsumo(Consumo consumo) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(consumo);
			entityManager.getTransaction().commit();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consumo registrado com sucesso!"));
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao registrar consumo!", e.getMessage()));
		} finally {
			entityManager.close();
		}
	}

	public List<Consumo> getConsumosPorCliente(Long clienteId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Consumo> consumos = null;
		try {
			consumos = entityManager.createQuery(
							"SELECT c FROM Consumo c WHERE c.cliente.id = :clienteId", Consumo.class)
					.setParameter("clienteId", clienteId)
					.getResultList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar consumos!", e.getMessage()));
		} finally {
			entityManager.close();
		}
		return consumos;
	}

	public void close() {
		entityManagerFactory.close();
	}
}
