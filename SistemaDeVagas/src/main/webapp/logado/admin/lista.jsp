<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Sistema De Vagas</title>
</head>
<body>
	<div align="center">
		<h1>Página do Administrador</h1>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Usuários</caption>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Papel</th>
			</tr>
			<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
					<td>${usuario.papel}</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/edicao?id=${usuario.id}">Edição</a>
						
						<a href="${pageContext.request.contextPath}/admin/remocao?id=${sessionScope.usuario.id}"
							onclick="return confirm('Tem certeza de que deseja excluir essa conta?');">
							Deletar conta
						 </a>                     
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>