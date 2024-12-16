package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Empresa;


import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Cadastro", urlPatterns = { "/cadastro/*"})
public class CadastroController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Erro erros = new Erro();
                
		if (request.getParameter("bFormularioProfissional") != null) {
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String data_nasc = request.getParameter("data_nasc");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");

			validateProfissionalInputs(email, senha, nome, cpf, data_nasc, sexo, telefone, erros);

			if (!erros.isExisteErros()) {
				UsuarioDAO dao_usuario = new UsuarioDAO();

                Usuario usuario = new Usuario(nome, email, senha, "Profissional");
                dao_usuario.insert(usuario);
				usuario = dao_usuario.getbyEmail(email);

                ProfissionalDAO dao_profissional = new ProfissionalDAO();
                Profissional profissional = new Profissional (cpf, data_nasc, sexo, telefone, usuario);
                dao_profissional.insert(profissional);

                request.getSession().setAttribute("usuarioLogado", usuario);
                response.sendRedirect("profissional/");
				return;
			}
            
		}

		else if (request.getParameter("bFormularioEmpresa") != null) {
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String cnpj = request.getParameter("cnpj");
            String cidade = request.getParameter("cidade");
            String descricao = request.getParameter("descricao");

			validateEmpresaInputs(email, senha, nome, cnpj, cidade, descricao, erros);

			if (!erros.isExisteErros()) {
				UsuarioDAO dao_usuario = new UsuarioDAO();

                Usuario usuario = new Usuario(nome, email, senha, "Empresa");
                dao_usuario.insert(usuario);
				usuario = dao_usuario.getbyEmail(email);

                EmpresaDAO dao_empresa = new EmpresaDAO();
                Empresa empresa = new Empresa(cnpj, cidade, descricao, usuario);
                dao_empresa.insert(empresa);

                request.getSession().setAttribute("usuarioLogado", usuario);
                response.sendRedirect("empresa/");
				return;
			}
		}

		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);

		String URL = "/cadastro.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}

	private void validateProfissionalInputs(String email, String senha, String nome, String cpf, String data_nasc, String sexo, String telefone, Erro erros) {
        if (email == null || email.isEmpty()) erros.add("Email não informado!");
        if (senha == null || senha.isEmpty()) erros.add("Senha não informada!");
        if (nome == null || nome.isEmpty()) erros.add("Nome não informado!");
        if (cpf == null || cpf.isEmpty()) erros.add("CPF não informado!");
        if (data_nasc == null || data_nasc.isEmpty()) erros.add("Data de nascimento não informada!");
        if (sexo == null || sexo.isEmpty()) erros.add("Sexo não informado!");
        if (telefone == null || telefone.isEmpty()) erros.add("Telefone não informado!");
    }

    private void validateEmpresaInputs(String email, String senha, String nome, String cnpj, String cidade, String descricao, Erro erros) {
        if (email == null || email.isEmpty()) erros.add("Email não informado!");
        if (senha == null || senha.isEmpty()) erros.add("Senha não informada!");
        if (nome == null || nome.isEmpty()) erros.add("Nome não informado!");
        if (cnpj == null || cnpj.isEmpty()) erros.add("CNPJ não informado!");
        if (cidade == null || cidade.isEmpty()) erros.add("Cidade não informada!");
        if (descricao == null || descricao.isEmpty()) erros.add("Descrição não informada!");
    }
}