<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/admin/head.jsp"></jsp:include>
</head>
<body class="sb-nav-fixed">
	<!-- nav -->
	<jsp:include page="/partials/admin/nav.jsp"></jsp:include>
	<!-- nav -->
	<div id="layoutSidenav">
		<jsp:include page="/partials/admin/sidenav.jsp"></jsp:include>
		
		<div id="layoutSidenav_content"> 
			<main>
	          <div class="container-fluid px-4">
	            <h1 class="mt-4">Usuarios de Tierra Media</h1>
	            <ol class="breadcrumb mb-4">
	              <li class="breadcrumb-item">
	                <a href="/visitatierramedia/admin/">Tablero</a>
	              </li>
	              <li class="breadcrumb-item active">Usuarios</li>
	            </ol>
	            
	            <c:if test="${flash != null}">
					<div class="alert alert-success alert-dismissible">
						<button type="button" class="btn-close" data-bs-dismiss="alert"
								aria-label="Close"></button>
						<i class="fas fa-check-circle me-1"></i>
						<span><c:out value="${flash}"></c:out></span>
					</div>	
				</c:if>
				
				<div class="mb-4">
					<a href="/visitatierramedia/admin/users/create.do" class="btn btn-primary" role="button"> 
						<i class="fas fa-plus"></i> 
						Nuevo Usuario
					</a>
				</div>
	            
	            <div class="card mb-4">
	              <div class="card-header">
	                <i class="fas fa-table me-1"></i>
	                Tabla de Usuarios
	              </div>
	              <div class="card-body">
	                <table class="table-striped" id="datatablesSimple">
	                  <thead>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Monedas de oro</th>
	                      <th>Tiempo disponible</th>
	                      <th>Tipo de atracción preferida</th>
	                      <th>Imagen</th>
	                      <th>Itinerario</th>
	                      <th data-sortable="false">Acciones</th>
	                    </tr>
	                  </thead>
	                  <tfoot>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Monedas de oro</th>
	                      <th>Tiempo disponible</th>
	                      <th>Tipo de atracción preferida</th>
	                      <th>Imagen</th>
	                      <th>Itinerario</th>
	                      <th data-sortable="false">Acciones</th>
	                    </tr>
	                  </tfoot>
	                  <tbody>
	                  <c:forEach items="${usuarios}" var="usuario">
	                    <tr>
	                      <td><c:out value="${usuario.nombre}"></c:out></td>
	                      <td><c:out value="${usuario.presupuesto}"></c:out></td>
	                      <td><c:out value="${usuario.tiempoDisponible}"></c:out></td>
	                      <td><c:out value="${usuario.tipoAtraccionPreferida.nombre}"></c:out></td>
	                      <td><c:out value="${usuario.imagen}"></c:out></td>
	                      <c:choose>
	                      <c:when test="${!usuario.productosComprados.isEmpty()}">
	                      	<td><a class="btn btn-link link-primary text-decoration-none" 
	                      	href="/visitatierramedia/admin/users/itinerary.do?id=${usuario.id}" role="button">Ver más -></a></td>
	                      </c:when>
	                      <c:otherwise>
	                      	<td class="text-center">----</td>
	                      </c:otherwise> 
	                      </c:choose>
	                      <td class="text-center">
	                        <a class="btn btn-warning m-1" href="/visitatierramedia/admin/users/edit.do?id=${usuario.id}" role="button" title="Editar">
	                          <i class="fas fa-pencil-alt"></i>
	                        </a>
	                        <!-- Button trigger modal -->
	                        <button type="button" class="btn btn-danger" title="Borrar" data-bs-toggle="modal" data-bs-target="#usuarioModal${usuario.id}">
	                          <i class="fas fa-trash-alt"></i>
	                        </button>
	
	                        <!-- Modal -->
	                        <div class="modal fade" id="usuarioModal${usuario.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	                          <div class="modal-dialog">
	                            <div class="modal-content">
	                              <div class="modal-header">
	                                <h5 class="modal-title" id="exampleModalLabel">Borrar Usuario</h5>
	                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                              </div>
	                              <div class="modal-body">
	                                Vas a borrar un usuario. ¿Estás seguro/a?
	                              </div>
	                              <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	                                <a class="btn btn-danger" href="/visitatierramedia/admin/users/delete.do?id=${usuario.id}" role="button">Borrar</a>
	                              </div>
	                            </div>
	                          </div>
	                        </div>
	                        <!-- Modal -->
	                      </td>
	                    </tr>
	                   </c:forEach>
	                  </tbody>
	                </table>
	              </div>
	            </div>
	          </div>
	        </main>
			<jsp:include page="/partials/admin/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>