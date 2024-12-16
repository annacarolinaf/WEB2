<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
    <caption>Criar Vaga</caption> 
    
    <form action="insereVaga" method="post">
        <input type="hidden" name="id" value="${empresa.usuario.id}"/>
        <tr>
            <td><label for="salario_vaga">Salário</label></td>
            <td>
                <input type="text" id="salario_vaga" name="salario_vaga" size="45"/>
            </td>
        </tr>
        <tr>
            <td><label for="descricao_vaga">Descrição</label></td>
            <td>
                <input type="text" id="descricao_vaga" name="descricao_vaga" size="45"/>
            </td>
        </tr>
        <tr>
            <td><label for="data_limite">Data limite</label></td>
            <td>
                <input type="text" id="data_limite" name="data_limite" size="45" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Salva" /></td>
        </tr>
    </form>
</table>