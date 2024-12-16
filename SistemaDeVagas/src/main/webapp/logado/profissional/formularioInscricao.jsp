<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String contextPath = request.getContextPath().replace("/", "");%>

<table border="1">
    <caption>Inscreva-se</caption> 
    <form action="${pageContext.request.contextPath}/profissional/inscricao" method="post">
        <input type="hidden" name="id" value="${profissional.usuario.id}"/>
        <input type="hidden" name="id_vaga" value="${vaga.id_vaga}"/>
        <tr>
            <td><label for="email">E-mail</label></td>
            <td>
                <input type="text" id="email" name="email" size="45" required 
                       value="${profissional.usuario.email != null ? profissional.usuario.email : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="nome">Nome</label></td>
            <td>
                <input type="text" id="nome" name="nome" size="45" required 
                       value="${profissional.usuario.nome != null ? profissional.usuario.nome : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="Senha">Senha</label></td>
            <td>
                <input type="text" id="senha" name="senha" size="45" required 
                       value="${profissional.usuario.senha != null ? profissional.usuario.senha : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="cpf">CPF</label></td>
            <td>
                <input type="text" id="cpf" name="cpf" size="45" required 
                       value="${profissional.cpf != null ? profissional.cpf : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="data_nasc">Data de Nascimento</label></td>
            <td>
                <input type="text" id="data_nasc" name="data_nasc" size="45" required 
                       value="${profissional.data_nasc != null ? profissional.data_nasc : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="sexo">Sexo</label></td>
            <td>
                <input type="text" id="sexo" name="sexo" size="45" 
                       value="${profissional.sexo != null ? profissional.sexo : ''}" />
            </td>
        </tr>
        <tr>
            <td><label for="telefone">Telefone</label></td>
            <td>
                <input type="text" id="telefone" name="telefone" size="45" 
                       value="${profissional.telefone != null ? profissional.telefone : ''}" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="Prosseguir" /></td>
        </tr>
    </form>
    <form action="upandoArquivo" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td><input type="hidden" name="id_inscricao" id="id_inscricao"></td>
                <td><label for="Currículo">Currículo</label></td>
                <td>
                    <input type="file" name="uploadFile" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Finalizar">
    </form>
</table>
