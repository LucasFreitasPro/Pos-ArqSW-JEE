package view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.CategoriaDAO;
import model.Categoria;

@Named
@ViewScoped
public class CategoriaView implements Serializable {

    private Categoria categoria = new Categoria();

    private CategoriaDAO dao = new CategoriaDAO();

    private List<Categoria> categorias;

    private String nomePesquisa;

    @PostConstruct
    public void init() {
        try {
            categorias = dao.listar();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public String getNomePesquisa() {
        return nomePesquisa;
    }

    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }

    public void delete(int codigo) {
        Optional<Categoria> categoria = this.categorias.stream().filter(c -> c.getCodigo() == codigo).findFirst();
        categoria.ifPresent(c -> {
            try {
                dao.excluir(codigo);
                categorias.remove(c);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria deletada com sucesso"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addOrUpdate() throws Exception {
        if (this.categoria.getCodigo() != null) {
            dao.editar(categoria);
        } else {
            dao.inserir(categoria);
        }
        this.categorias = dao.listar();
        this.categoria = new Categoria();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria inserida com sucesso"));
    }

    public void update() {
        try {
            dao.editar(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria atualizada com sucesso"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar() {
        try {
            if (nomePesquisa != null && !nomePesquisa.isEmpty()) {
                categorias = dao.porNome(nomePesquisa.trim().toLowerCase());
            } else {
                categorias = dao.listar();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
