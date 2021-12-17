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
	            <h1 class="mt-4">Administradores de Tierra Media</h1>
	            <ol class="breadcrumb mb-4">
	              <li class="breadcrumb-item">
	                <a href="/visitatierramedia/admin/">Tablero</a>
	              </li>
	              <li class="breadcrumb-item active">Admins</li>
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
					<a href="/visitatierramedia/admin/create.do" class="btn btn-primary" role="button"> 
						<i class="fas fa-plus"></i> 
						Nuevo Administrador
					</a>
				</div>
	            
	            <div class="card mb-4">
	              <div class="card-header">
	                <i class="fas fa-table me-1"></i>
	                DataTable Admins
	              </div>
	              <div class="card-body">
	                <table class="table-striped" id="datatablesSimple">
	                  <thead>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Correo</th>
	                      <th>Acciones</th>
	                    </tr>
	                  </thead>
	                  <tfoot>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Correo</th>
	                      <th>Acciones</th>
	                    </tr>
	                  </tfoot>
	                  <tbody>
	                  	<c:forEach items="${admins}" var="admin">
	                    <tr>
	                      <td><c:out value="${admin.nombre}"></c:out></td>
	                      <td>
	                        <c:out value="${admin.correo}"></c:out>
	                      </td>
	                      <td class="text-center"> 
	                        <a class="btn btn-warning m-1" href="/visitatierramedia/admin/edit.do?id=${admin.id}" role="button" title="Editar">
	                          <i class="fas fa-pencil-alt"></i>
	                        </a>
	                        <!-- Button trigger modal -->
	                    <c:choose>
	                      <c:when test="${admin.equals(adminUser)}">
	                      	<a href="#" class="btn btn-danger disabled" tabindex="-1" role="button" aria-disabled="true" title="Borrar">
		                      <i class="fas fa-trash-alt"></i>
	                      	</a>
	                      </c:when>
	                      <c:otherwise>
	                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#adminModal${admin.id}" data-id="${admin.id}">
	                          <i class="fas fa-trash-alt"></i>
	                        </button>	
	                      </c:otherwise>  
						</c:choose>
	                        <!-- Modal -->
	                        <div class="modal fade" id="adminModal${admin.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	                          <div class="modal-dialog">
	                            <div class="modal-content">
	                              <div class="modal-header">
	                                <h5 class="modal-title" id="exampleModalLabel">Borrar admin</h5>
	                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                              </div>
	                              <div class="modal-body">
	                                Vas a borrar un admin. ¿Estás seguro/a?
	                              </div>
	                              <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	                                <a class="btn btn-danger" href="/visitatierramedia/admin/delete.do?id=${admin.id}" role="button">Borrar</a>
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