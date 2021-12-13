<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/admin/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="/partials/admin/nav.jsp"></jsp:include>

	<div id="layoutSidenav">
		<jsp:include page="/partials/admin/sidenav.jsp"></jsp:include>

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Atracciones de la promo <c:out value="${promocion.nombre}"></c:out> </h1>
		            <ol class="breadcrumb mb-4">
		              <li class="breadcrumb-item">
		                <a href="/visitatierramedia/admin/">Tablero</a>
		              </li>
		              <li class="breadcrumb-item">
		              	<a href="/visitatierramedia/admin/promotions/data-table.do">Promociones</a>
		              </li>
		              <li class="breadcrumb-item active">Lista de Atracciones</li>
		            </ol>
		            
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> DataTable Atracciones
						</div>
						<div class="card-body">
							<table class="table-striped" id="datatablesSimple">
								<thead>
				                    <tr>
				                      <th>Nombre</th>
				                      <th>Descripción</th>
				                      <th>Costo</th>
				                      <th>Tiempo (hs)</th>
				                      <th>Cupo</th>
				                      <th>Tipo</th>
				                      <th>Imagen</th>
				                    </tr>
				                  </thead>
				                  <tfoot>
				                    <tr>
				                      <th>Nombre</th>
				                      <th>Descripción</th>
				                      <th>Costo</th>
				                      <th>Tiempo (hs)</th>
				                      <th>Cupo</th>
				                      <th>Tipo</th>
				                      <th>Imagen</th>
				                    </tr>
				                  </tfoot>
				                  <tbody>
				                  <c:forEach items="${atracciones}" var="atraccion">
				                    <tr>
				                      <td><c:out value="${atraccion.nombre}"></c:out></td>
				                      <td>
				                        <c:out value="${atraccion.descripcion}"></c:out>
				                      </td>
				                      <td><c:out value="${atraccion.costoDeVisita}"></c:out></td>
				                      <td><c:out value="${atraccion.tiempoDeVisita}"></c:out></td>
				                      <td><c:out value="${atraccion.cupoDePersonas}"></c:out></td>
				                      <td><c:out value="${atraccion.tipo.getNombre()}"></c:out></td>
				                      <td><c:out value="${atraccion.imagen}"></c:out></td>
				                      
				                      
				                    </tr>
				                   </c:forEach>
				                 </tbody>
							</table>
						</div>
					</div>
					
					<a class="btn btn-primary mb-4" href="/visitatierramedia/admin/promotions/data-table.do" role="button">
		              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
		                <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
		              </svg>
		              Volver
		            </a>
					
				</div>
			</main>
			<jsp:include page="/partials/admin/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>