package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaDAO {

    public void inserir(Categoria categoria) throws SQLException, ClassNotFoundException {
        Statement stm = null;
        Conexao conexao = null;
        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            stm.execute("INSERT INTO categoria (codigo,nome) VALUES((select max(codigo) + 1 from categoria), '"
                    + categoria.getNome() + "')");
        } finally {
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
    }

    public List<Categoria> listar() throws SQLException, ClassNotFoundException {
        List<Categoria> categorias = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;
        Conexao conexao = null;

        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            rs = stm.executeQuery("SELECT * FROM categoria");
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("codigo"), rs.getString("nome")));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
        return categorias;
    }

    public Categoria porCodigo(Integer codigo) throws SQLException, ClassNotFoundException {
        Categoria categoria = null;
        Statement stm = null;
        ResultSet rs = null;
        Conexao conexao = null;

        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            rs = stm.executeQuery("SELECT * FROM categoria WHERE codigo = " + codigo);
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("codigo"), rs.getString("nome"));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
        return categoria;
    }

    public List<Categoria> porNome(String nome) throws SQLException, ClassNotFoundException {
        List<Categoria> categorias = new ArrayList<>();
        Statement stm = null;
        ResultSet rs = null;
        Conexao conexao = null;

        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            rs = stm.executeQuery("SELECT * FROM categoria WHERE LOWER(nome) LIKE '%" + nome + "%'");
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("codigo"), rs.getString("nome")));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
        return categorias;
    }

    public void editar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Statement stm = null;
        Conexao conexao = null;
        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            stm.execute("UPDATE categoria SET nome = '" + categoria.getNome() + "' WHERE codigo = "
                    + categoria.getCodigo());
        } finally {
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
    }

    public void excluir(Integer codigo) throws SQLException, ClassNotFoundException {
        Statement stm = null;
        Conexao conexao = null;
        try {
            conexao = new Conexao();
            stm = conexao.getConnection().createStatement();
            stm.execute("DELETE FROM categoria WHERE codigo = " + codigo);
        } finally {
            if (stm != null)
                stm.close();
            if (conexao != null)
                conexao.fecharConexao();
        }
    }
}
