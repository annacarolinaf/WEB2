<!-- Lista de vagas sem estar logado-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Página da Empresa</title>
            </head>

            <body>

                <h1>Olá ${sessionScope.usuarioLogado.nome}</h1>

                <div align="center">
                    <table border="1">
                        <h1>Lista de Vagas</h1>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Salário</th>
                            <th>Data limite</th>
                            <th>Status</th>
                            <th>Ação</th>
                        </tr>
                        <c:forEach var="vaga" items="${requestScope.listaVagas}">
                            <tr>
                                <td>${vaga.id_vaga}</td>
                                <td>${vaga.descricao}</td>
                                <td>${vaga.salario}</td>
                                <td>${vaga.data_limite}</td>
                                <td>${vaga.status_vaga}</td>
                                <td><a href="${pageContext.request.contextPath}/empresa/listarInscritos?id=${sessionScope.usuarioLogado.id}&id_vaga=${vaga.id_vaga}">Visualizar inscritos</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <ul>
                    <li>
                        <a
                            href="${pageContext.request.contextPath}/empresa/cadastroVaga?id=${sessionScope.usuarioLogado.id}">Adicionar
                            vaga</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/empresa/edicao?id=${sessionScope.usuarioLogado.id}">Atualizar
                            dados</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/empresa/remocao?id=${sessionScope.usuarioLogado.id}"
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