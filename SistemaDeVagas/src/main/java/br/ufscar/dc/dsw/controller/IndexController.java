package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/", "/index.jsp"})
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "/pesquisaCidade":
                    pesquisaCidade(request, response);
                    break;
                default:
                    doGet(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();

        request.getSession().invalidate();
        request.setAttribute("mensagens", erros);

        VagaDAO dao = new VagaDAO();
        List<Vaga> listaVagas = dao.getAll();
        request.setAttribute("listaVagas", listaVagas);

        String URL = "lista.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    private void pesquisaCidade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtro = request.getParameter("filtro");

        VagaDAO dao = new VagaDAO();
        List<Vaga> listaVagas = dao.getPorCidade(filtro);
        request.setAttribute("listaVagas", listaVagas);

        RequestDispatcher rd = request.getRequestDispatcher("lista.jsp");
        rd.forward(request, response);
    }


}
