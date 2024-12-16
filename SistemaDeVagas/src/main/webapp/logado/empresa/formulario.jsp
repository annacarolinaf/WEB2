<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
    <caption>Atualizar dados</caption> 
    <form action="${pageContext.request.contextPath}/empresa/atualiza" method="post">
        <input type="hidden" name="id" value="${empresa.usuario.id}"/>
        <tr>
            <td><label for="email">E-mail</label></td>
            <td>
                <input type="text" id="email" name="email" size="45" required 
                       value="${empresa.usuario.email != null ? empresa.usuario.email : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="nome">Nome</label></td>
            <td>
                <input type="text" id="nome" name="nome" size="45" required 
                       value="${empresa.usuario.nome != null ? empresa.usuario.nome : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="Senha">Senha</label></td>
            <td>
                <input type="text" id="senha" name="senha" size="45" required 
                       value="${empresa.usuario.senha != null ? empresa.usuario.senha : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="cnpj">CNPJ</label></td>
            <td>
                <input type="text" id="cnpj" name="cnpj" size="45" required 
                       value="${empresa.cnpj != null ? empresa.cnpj : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="cidade">Cidade</label></td>
            <td>
                <input type="text" id="cidade" name="cidade" size="45" required 
                       value="${empresa.cidade != null ? empresa.cidade : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="descricao">Descrição</label></td>
            <td>
                <input type="text" id="descricao" name="descricao" size="45" 
                       value="${empresa.descricao != null ? empresa.descricao : ''}" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Salva" /></td>
        </tr>
    </form>
</table>
