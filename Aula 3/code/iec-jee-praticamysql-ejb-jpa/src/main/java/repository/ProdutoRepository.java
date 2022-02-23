package repository;

import java.util.List;

import model.Produto;

public interface ProdutoRepository {
    void inserir(Produto produto);

    List<Produto> listar();

    List<Produto> listarPorCategoria(Integer idCategoria);

    List<Produto> porNome(String nome) throws Exception;

    void editar(Produto produto) throws Exception;

    void deletar(Integer id) throws Exception;
}
