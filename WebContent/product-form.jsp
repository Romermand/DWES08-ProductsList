<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Products Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Productos</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">		
				<c:if test="${product != null}">
					<form action="update" method="post" />
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Editar Producto
            		</c:if>
						<c:if test="${Product == null}">
            			Agregar Nuevo Producto
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id" value="<c:out value='${product.IDProducto}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nombre Producto</label> <input type="text"
						value="<c:out value='${product.NombreProducto}' />" class="form-control"
						name="NombreProducto" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Descripcion</label> <input type="text"
						value="<c:out value='${product.Descripcion}' />" class="form-control"
						name="Descripcion">
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${product.Precio}' />" class="form-control"
						name="Precio">
				</fieldset>

				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
