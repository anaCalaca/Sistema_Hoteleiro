package dao;

import entidades.Produto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoDAO {

    private final EntityManagerFactory entityManagerFactory;

    public ProdutoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
    }

    public void inserirProduto(Produto produto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Gravados com Sucesso :)"));
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao gravar dados: " + e.getMessage(), null));
        } finally {
            entityManager.close();
        }
    }

    public Produto atualizarProduto(Produto produto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Produto produtoRetorno = null;
        try {
            entityManager.getTransaction().begin();
            produtoRetorno = entityManager.merge(produto);
            entityManager.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Atualizados com Sucesso :)"));
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar dados: " + e.getMessage(), null));
        } finally {
            entityManager.close();
        }
        return produtoRetorno;
    }

    public void removerProduto(Produto produto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            produto = entityManager.find(Produto.class, produto.getId());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dados Excluídos com Sucesso :)"));
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir dados: " + e.getMessage(), null));
        } finally {
            entityManager.close();
        }
    }

    public List<Produto> listarProdutos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Produto> listaProduto = null;
        try {
            listaProduto = entityManager.createQuery("FROM Produto ORDER BY id ASC", Produto.class).getResultList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao listar produtos: " + e.getMessage(), null));
        } finally {
            entityManager.close();
        }
        return listaProduto;
    }


    public List<Produto> getListEntity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projetojsf");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Produto> listaProduto = entityManager.createQuery("FROM Produto ORDER BY id ASC", Produto.class).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return listaProduto;

    }

}
