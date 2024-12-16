package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = { "/empresa/*" })
public class EmpresaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
		} else if (usuario.getPapel().equals("Empresa")) {

			String action = request.getPathInfo();

			if (action == null)
				action = "";

			try {
				switch (action) {
					case "/cadastroVaga":
						apresentarFormVaga(request, response);
						break;
					case "/insereVaga":
						cadastraVaga(request, response);
						break;
					case "/listarInscritos":
						listaInscritos(request, response);
						break;
					case "/alterarResultado":
						alteraResultado(request, response);
						break;
					case "/edicao":
						apresentaFormEdicao(request, response);
						break;
					case "/remocao":
						remove(request, response);
						break;
					case "/atualiza":
						atualiza(request, response);
						break;
					default:
						listaVagas(request, response, usuario);
						break;
				}
			} catch (RuntimeException | IOException | ServletException e) {
				throw new ServletException(e);
			}

		} else {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [Empresa] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
		}
	}

	private void apresentarFormVaga(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Empresa empresa = new EmpresaDAO().getByIdUsuario(usuario);
		request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/cadastroVaga.jsp");
        dispatcher.forward(request, response);
    }

	private void cadastraVaga(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		//System.out.println("Id do usuário: " + id);
		Float salario_vaga = Float.parseFloat(request.getParameter("salario_vaga"));
		String descricao_vaga = request.getParameter("descricao_vaga");
		String  data_limite = request.getParameter("data_limite");
		String status_vaga = request.getParameter("status_vaga");
		
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Empresa empresa = new EmpresaDAO().getByIdUsuario(usuario);
		
		Vaga vaga = new Vaga(salario_vaga, descricao_vaga, data_limite, empresa, status_vaga);

		new VagaDAO().insert(vaga);

		response.sendRedirect(request.getContextPath() + "/empresa");
	}

	private void listaInscritos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Long id_vaga = Long.parseLong(request.getParameter("id_vaga"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Empresa empresa = new EmpresaDAO().getByIdUsuario(usuario);

		List<Inscricao> listaInscricoes = new InscricaoDAO().getAllInscritosnaVaga(id_vaga, empresa);
		request.setAttribute("listaInscricoes", listaInscricoes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/listaInscricoes.jsp");
		dispatcher.forward(request, response);
	}

	private void alteraResultado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Long id = Long.parseLong(request.getParameter("id"));
		//pegando os valores passados
		String cpf = request.getParameter("cpf");
		int rst = Integer.parseInt(request.getParameter("rst"));
		Long id_vaga = Long.parseLong(request.getParameter("id_vaga"));


		System.out.println("cpf: " + cpf + "RESULTADO: " + rst + "ID_VAGA: " + id_vaga);

		Inscricao inscricao = new InscricaoDAO().getbyIDVagaIDCpf(cpf, id_vaga);
		inscricao.setResultado(rst);

		InscricaoDAO dao_inscricao = new InscricaoDAO();
		dao_inscricao.update(inscricao);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/listarInscritos"+"?id="+id);
		dispatcher.forward(request, response);
	}

	private void listaVagas(HttpServletRequest request, HttpServletResponse response, Usuario usuario) 
			throws ServletException, IOException {
		List<Vaga> listaVagas = new VagaDAO().getAllVagasEmpresa(usuario);
		request.setAttribute("listaVagas", listaVagas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/index.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Empresa empresa = new EmpresaDAO().getByIdUsuario(usuario);
		request.setAttribute("empresa", empresa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/empresa/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario usuario = new UsuarioDAO().getbyID(id);
		Empresa empresa = new EmpresaDAO().getByIdUsuario(usuario);
		new EmpresaDAO().delete(empresa);
		response.sendRedirect(request.getContextPath());
	}

	private void atualiza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String cnpj = request.getParameter("cnpj");
		String cidade = request.getParameter("cidade");
		String descricao = request.getParameter("descricao");

		Usuario usuario = new Usuario(id, nome, email, senha, "Empresa");
		Empresa empresa = new Empresa(cnpj, cidade, descricao, usuario);

		new UsuarioDAO().update(usuario);
		new EmpresaDAO().update(empresa);

		response.sendRedirect(request.getContextPath() + "/empresa");
	}

}
