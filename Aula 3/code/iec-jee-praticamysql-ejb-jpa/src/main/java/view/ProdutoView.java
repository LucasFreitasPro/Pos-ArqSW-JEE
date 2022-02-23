package view;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import business.CategoriaService;
import business.ProdutoService;
import model.Categoria;
import model.Produto;

@Named
@ViewScoped
public class ProdutoView implements Serializable {

    @EJB
    private ProdutoService produtoService;

    @EJB
    private CategoriaService categoriaService;

    private Categoria categoria;

    private String produtoPesquisa;

    private Produto produto;

    private List<Produto> produtos;

    private List<Categoria> categorias;

    @PostConstruct
    public void init() {
        limparObjetos();
        produtos = this.produtoService.listar();
        categorias = this.categoriaService.listar();
    }

    private void limparObjetos() {
        produto = new Produto();
        produto.setCategoria(new Categoria(0));
        categoria = new Categoria(0);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getProdutoPesquisa() {
        return produtoPesquisa;
    }

    public void setProdutoPesquisa(String produtoPesquisa) {
        this.produtoPesquisa = produtoPesquisa;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void pesquisar() {
        try {
            if (produtoPesquisa == null || produtoPesquisa.isEmpty()) {
                produtos = produtoService.listar();
            } else {
                produtos = this.produtoService.porNome(produtoPesquisa);
            }
            limparObjetos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisarPorCategoria() {
        try {
            if (categoria.getIdCategoria() == null || categoria.getIdCategoria() == 0) {
                produtos = produtoService.listar();
            } else {
                produtos = this.produtoService.listarPorCategoria(categoria.getIdCategoria());
            }
            limparObjetos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            this.produtoService.deletar(id);
            produtos = this.produtoService.listar();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Produto excluído com sucesso"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdate() throws Exception {
        String mensagem = "Produto inserido com sucesso";
        if (this.produto.getIdProduto() != null) {
            mensagem = "Produto atualizado com sucesso";
            this.produtoService.editar(produto);
        } else {
            this.produtoService.inserir(produto);
        }

        this.produtos = this.produtoService.listar();
        limparObjetos();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(mensagem));
    }
}
