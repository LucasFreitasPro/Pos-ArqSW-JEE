package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

@WebServlet("/inserir")
public class InserirCategoria extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CategoriaDAO dao = new CategoriaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        Categoria categoria = new Categoria(nome);

        try {
            dao.inserir(categoria);
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.print("<html>");
            out.print("<h2> Nao foi possivel inserir a categoria!</h2>");
            out.print("<br");
            out.print("<a href = 'index.jsp'> Voltar </a>");
            out.print("</html>");
        }
    }
}
