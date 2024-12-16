<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Profissional</title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Cadastro de Profissional</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="cadastro">
            <table>
                <tr>
                    <th>E-mail: </th>
                    <td><input type="text" name="email"
                               value="${param.email}"/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <th>Nome: </th>
                    <td><input type="text" name="nome" /></td>
                </tr>
                <tr>
                    <th>CPF: </th>
                    <td><input type="text" name="cpf" /></td>
                </tr>
                <tr>
                    <th>Telefone: </th>
                    <td><input type="text" name="telefone" /></td>
                </tr>
                <tr>
                    <th>Data de nascimento: </th>
                    <td><input type="text" name="data_nasc" /></td>
                </tr>
                <tr>
                    <th>Sexo: </th>
                    <td><input type="text" name="sexo" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bFormularioProfissional" value="Enviar"/>
                    </td>
                </tr>
                
            </table>
        </form>
    </body>
</html>