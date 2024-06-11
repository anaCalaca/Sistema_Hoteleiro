package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Cliente;

public class ClienteDAO {
	
	public void incluirCliente(Cliente cliente) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Gravados com Sucesso :)"));		
	}


	public Cliente atualizarCliente(Cliente cliente) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		Cliente clienteRetorno = entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Atualizados com Sucesso :)"));
        return clienteRetorno;
	}
	
	
	public void removerCliente(Cliente cliente) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso :)"));		
	}
	
	public void removerPorId(Cliente cliente) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Cliente clienteDelecao = entityManager.find(Cliente.class, cliente.getId());
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(clienteDelecao);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso :)"));		
	}
	
	
	public List<Cliente> getListEntity() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	
		List<Cliente> listaCliente = entityManager.createQuery("FROM Cliente ORDER BY id ASC", Cliente.class).getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return listaCliente;
		
	}


}
