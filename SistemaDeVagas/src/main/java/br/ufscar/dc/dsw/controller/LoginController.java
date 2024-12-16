package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Login", urlPatterns = { "/login/*", "/logout.jsp" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			if (email == null || email.isEmpty()) {
				erros.add("E-mail não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getbyEmail(email);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
						if (usuario.getPapel().equals("Empresa")) {
							response.sendRedirect("empresa/");
						} else if (usuario.getPapel().equals("Profissional")){
							response.sendRedirect("profissional/");
						}
						else {
							response.sendRedirect("admin/");
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} 

				else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		else if (request.getParameter("bCadastro") != null) {
			response.sendRedirect("cadastro.jsp");
			return;
		}

		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
		String URL = "login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}