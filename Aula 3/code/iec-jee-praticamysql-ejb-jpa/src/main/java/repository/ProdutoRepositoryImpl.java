package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Produto;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private EntityManager em;

    public ProdutoRepository setEntityManager(EntityManager em) {
        this.em = em;
        return this;
    }

    @Override
    public void inserir(Produto produto) {
        em.persist(produto);
    }

    @Override
    public List<Produto> listar() {
        return em.createQuery("select p from Produto p", Produto.class)
                .getResultList();
    }

    @Override
    public List<Produto> porNome(String nome) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> cr = cb.createQuery(Produto.class);
        Root<Produto> root = cr.from(Produto.class);
        cr.select(root).where(cb.like(root.get("nome"), nome + "%"));
        return em.createQuery(cr).getResultList();
    }

    @Override
    public void editar(Produto produto) throws Exception {
        em.merge(produto);
    }

    @Override
    public void deletar(Integer id) throws Exception {
        var produto = em.find(Produto.class, id);
        em.remove(produto);
    }

    @Override
    public List<Produto> listarPorCategoria(Integer idCategoria) {
        return em.createQuery("SELECT p FROM Produto p JOIN FETCH p.categoria c WHERE c.idCategoria = ?1",
                Produto.class).setParameter(1, idCategoria)
                .getResultList();
    }
}
