package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Cadastro;

public class CadastroDAO {

	public void incluirCadastro(Cadastro cadastro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.persist(cadastro);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Gravados com Sucesso :)"));		
	}
	
	public Cadastro atualizarCadastro(Cadastro cadastro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		Cadastro cadastroRetorno = entityManager.merge(cadastro);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Atualizados com Sucesso :)"));
        return cadastroRetorno;
	}
	
	
	public void removerCadastro(Cadastro cadastro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(cadastro);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso :)"));		
	}
	
	public void removerPorId(Cadastro cadastro) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Cadastro cadastroDelecao = entityManager.find(Cadastro.class, cadastro.getId());
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(cadastroDelecao);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso :)"));		
	}
	
	
	public List<Cadastro> getListEntity() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	
		List<Cadastro> listaCadastro = entityManager.createQuery("FROM Cadastro ORDER BY id ASC", Cadastro.class).getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return listaCadastro;
		
	}

}
