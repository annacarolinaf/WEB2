<!-- Lista de vagas sem estar logado-->
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">
            <head>
                <title><fmt:message key="page.title" /></title>
            </head>

            <body>
                <h1>
                    <fmt:message key="apply.now" />
                </h1>

                <div align="center">
                    <h1>
                        <fmt:message key="list.of.vacancies" />
                    </h1>
                    <h2>
                        <a href="${pageContext.request.contextPath}/login.jsp">Login</a> &nbsp;&nbsp;&nbsp;
                    </h2>
                    <form method="post" action="pesquisaCidade">
                        <h2><fmt:message key="search.by.city" /></h2>
                        <input type="text" name="filtro" id="filtro" placeholder=<fmt:message key="search.for.a.city" />>
                        <button type="submit"> Buscar </button>
                    </form>
                </div>

                <div align="center">
                    <table border="1">
                        <caption><fmt:message key="open.vacancies" /></caption>
                        <tr>
                            <th><fmt:message key="company" /></th>
                            <th><fmt:message key="job.description" /></th>
                            <th><fmt:message key="wage" /></th>
                            <th><fmt:message key="deadline" /></th>
                            <th><fmt:message key="city" /></th>
                            <th><fmt:message key="status" /></th>
                            <th><fmt:message key="action" /></th>
                        </tr>
                        <c:forEach var="vaga" items="${requestScope.listaVagas}">
                            <c:if test="${vaga.status_vaga == 'ABERTA'}">
                                <tr>
                                    <td>${vaga.empresa.usuario.nome}</td>
                                    <td>${vaga.descricao}</td>
                                    <td>${vaga.salario}</td>
                                    <td>${vaga.data_limite}</td>
                                    <td>${vaga.empresa.cidade}</td>
                                    <td>${vaga.status_vaga}</td>
                                    <td><a href="login.jsp"><fmt:message key="sign.up" /></a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                    <form method="post" action="showAll">
                        <button type="submit"> <fmt:message key="show.all" /> </button>
                    </form>
                </div>
            </body>
            
</fmt:bundle>

            </html>