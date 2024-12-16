<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Página do Profissional</title>
</head>
<body>
    <h1>Olá ${sessionScope.usuarioLogado.nome}</h1>

    <div align="center">
        <h1>Lista de vagas inscritas</h1>
    </div>

    <div align="center">
        <table border="1">
            <caption>Vagas inscritas</caption>
            <tr>
                <th>ID</th>
                <th>Empresa</th>
                <th>Descrição de Vaga</th>
                <th>Salário</th>
                <th>Data limite</th>
                <th>Cidade</th>
                <th>Status</th>
                <th>Resultado</th>
            </tr>
            <c:set var="indexList" value="${fn:length(requestScope.listaVagasInscritas)}" />
            <c:choose>
                <c:when test="${indexList ==0 }">
                    <h2>Ainda não possui nenhuma inscrição</h2>
                </c:when>
                <c:otherwise>
                    <c:forEach var="index" begin="0" end="${indexList - 1}">
                        <c:set var="vaga" value="${requestScope.listaVagasInscritas[index]}" />
                        <c:set var="inscricao" value="${requestScope.listaInscricoes[index]}" />
                        <tr>
                            <td>${vaga.id_vaga}</td>
                            <td>${vaga.empresa.usuario.nome}</td>
                            <td>${vaga.descricao}</td>
                            <td>${vaga.salario}</td>
                            <td>${vaga.data_limite}</td>
                            <td>${vaga.empresa.cidade}</td>
                            <td>${vaga.status_vaga}</td>
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
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            
        </table>
    </div>

    <ul>
        <li><a href="${pageContext.request.contextPath}/profissional">Voltar</a></li>
    </ul>
</body>
</html>
