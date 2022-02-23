package business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Produto;
import repository.ProdutoRepository;
import repository.RepositorySession;

@Stateless
public class ProdutoService {
    @EJB
    private RepositorySession repository;
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void initialize() {
        produtoRepository = repository.getProdutoRepository();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserir(Produto produto) {
        produtoRepository.inserir(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.listar();
    }

    public List<Produto> porNome(String nome) throws Exception {
        return produtoRepository.porNome(nome);
    }

    public List<Produto> listarPorCategoria(Integer idCategoria) throws Exception {
        return produtoRepository.listarPorCategoria(idCategoria);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editar(Produto produto) throws Exception {
        produtoRepository.editar(produto);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletar(Integer id) throws Exception {
        produtoRepository.deletar(id);
    }
}
