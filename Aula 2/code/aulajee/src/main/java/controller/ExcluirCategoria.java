package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;

@WebServlet(urlPatterns = "/delete")
public class ExcluirCategoria extends HttpServlet {
    private CategoriaDAO dao = new CategoriaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer codigo = Integer.parseInt(request.getParameter("id"));
            dao.excluir(codigo);
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
