package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

@WebServlet(urlPatterns = "/index")
public class Index extends HttpServlet {
    private CategoriaDAO dao = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            listarCategoria(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarCategoria(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException, ClassNotFoundException {
        List<Categoria> categorias = dao.listar();
        req.setAttribute("listaCategoria", categorias);
        RequestDispatcher dispatcher = req.getRequestDispatcher("inicial.jsp");
        dispatcher.forward(req, resp);
    }
}
