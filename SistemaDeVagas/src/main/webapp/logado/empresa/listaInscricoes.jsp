<!-- Lista de vagas sem estar logado-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <html>

            <head>
                <title>Inscrições da vaga</title>
            </head>

            <body>

                <div align="center">
                    <table border="1">
                        <h1>Lista de Inscrições</h1>
                        <tr>
                            <th>CPF</th>
                            <th>Nome</th>
                            <th>Qualificação</th>
                            <th>Descrição da vaga</th>
                            <th>Resultado</th>
                            <th>Ação</th>
                        </tr>
                        <c:forEach var="inscricao" items="${requestScope.listaInscricoes}">
                            <tr>
                                <td>${inscricao.profissional.cpf}</td>
                                <td>${inscricao.profissional.usuario.nome}</td>
                                <td>${inscricao.qualificacao}</td>
                                <td>${inscricao.vaga.descricao}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${inscricao.resultado == 0}">
                                            NÃO SELECIONADO
                                        </c:when>
                                        <c:when test="${inscricao.resultado == 1}">
                                            ENTREVISTA
                                        </c:when>
                                        <c:otherwise>
                                            ANÁLISE
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>

                                    <center>
                                    <a href="${pageContext.request.contextPath}/empresa/alterarResultado?id=${sessionScope.usuarioLogado.id}&cpf=${inscricao.profissional.cpf}&rst=1&id_vaga=${inscricao.vaga.id_vaga}">
                                        &#9989;
                                    </a>

                                    <a href="${pageContext.request.contextPath}/empresa/alterarResultado?id=${sessionScope.usuarioLogado.id}&cpf=${inscricao.profissional.cpf}&rst=0&id_vaga=${inscricao.vaga.id_vaga}">
                                        &#9940;&#65039;
                                    </a>
                                </center>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <ul>
                    <li>
                        <a
                            href="${pageContext.request.contextPath}/empresa/">Voltar</a>
                    </li>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
                    </li>
                
                </ul>

            </body>

            </html>