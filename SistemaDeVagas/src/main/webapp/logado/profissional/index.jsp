<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Página do Profissional</title>
            </head>

            <body>
                <h1>Olá ${sessionScope.usuarioLogado.nome}</h1>

					<div align="center">
						<h1>Lista de vagas</h1>
						<form method="post" action="${pageContext.request.contextPath}/profissional/pesquisaCidade">
							<h2>Pesquisar por cidade:</h2>
							<input type="text" name="filtro" id="filtro" placeholder="Procure uma cidade">
							<button type="submit"> Buscar </button>
						</form>
					</div>
	
					<div align="center">
						<table border="1">
							<caption>Vagas em aberto</caption>
							<tr>
								<th>ID</th>
								<th>Empresa</th>
								<th>Descrição de Vaga</th>
								<th>Salário</th>
								<th>Data limite</th>
								<th>Cidade</th>
								<th>Status</th>
								<th>Ação</th>
							</tr>
							<c:forEach var="vaga" items="${requestScope.listaVagas}">
								<c:if test="${vaga.status_vaga == 'ABERTA'}">
								<tr>
									<td>${vaga.id_vaga}</td> 
									<td>${vaga.empresa.usuario.nome}</td>
									<td>${vaga.descricao}</td>
									<td>${vaga.salario}</td>
									<td>${vaga.data_limite}</td>
									<td>${vaga.empresa.cidade}</td>
									<td>${vaga.status_vaga}</td>
									<td><a href="${pageContext.request.contextPath}/profissional/inscricaoForm?id=${sessionScope.usuarioLogado.id}&id_vaga=${vaga.id_vaga}">
										Inscreva-se</a></td>
									
									&nbsp;&nbsp;&nbsp;&nbsp; </td>
								</tr>
								</c:if>
							</c:forEach>
						</table>
						<form method="post" action="showAll">
							<button type="submit"> Mostrar todos </button>
						</form>
					</div>

                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/profissional/apresentaVagasInscritas?id=${sessionScope.usuarioLogado.id}">Vagas inscritas</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/profissional/edicao?id=${sessionScope.usuarioLogado.id}">Atualizar dados</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/profissional/remocao?id=${sessionScope.usuarioLogado.id}"
                        onclick="return confirm('Tem certeza de que deseja excluir sua conta?');">
                        Deletar conta
                     </a>                     
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
                    </li>   
                </ul>

            </body>

            </html>
