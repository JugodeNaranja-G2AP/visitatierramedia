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
		            <h1 class="mt-4">Tipos de atracción de Tierra Media</h1>
		            <ol class="breadcrumb mb-4">
		              <li class="breadcrumb-item">
		                <a href="/visitatierramedia/admin/">Tablero</a>
		              </li>
		              <li class="breadcrumb-item active">Tipos de atraccion</li>
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
						<a href="/visitatierramedia/admin/attraction-types/create.do" class="btn btn-primary" role="button"> 
							<i class="fas fa-plus"></i> 
							Nuevo Tipo de atracción
						</a>
					</div>
		            
		            <div class="card mb-4">
		              <div class="card-header">
		                <i class="fas fa-table me-1"></i>
		                Tabla de Tipos de atracción
		              </div>
		              <div class="card-body">
		                <table class="table-striped" id="datatablesSimple">
		                  <thead>
		                    <tr>
		                      <th>Tipo</th>
		                      <th>Acciones</th>
		                    </tr>
		                  </thead>
		                  <tfoot>
		                    <tr>
		                      <th>Tipo</th>
		                      <th>Acciones</th>
		                    </tr>
		                  </tfoot>
		                  <tbody>
		                  <c:forEach items="${tiposDeAtraccion}" var="tipo">
		                    <tr>
		                      <td><c:out value="${tipo.nombre}"></c:out></td>
		                      <td class="text-center">
		                        <a class="btn btn-warning m-1" href="/visitatierramedia/admin/attraction-types/edit.do?id=${tipo.id}" role="button" title="Editar">
		                          <i class="fas fa-pencil-alt"></i>
		                        </a>
								<!--  Button trigger modal -->
		                        <button type="button" class="btn btn-danger" title="Borrar" data-bs-toggle="modal" 
		                        data-bs-target="#tipoModal${tipo.id}">
		                          <i class="fas fa-trash-alt"></i>
		                        </button>
		
		                        <!-- Modal -->
		                        <div class="modal fade" id="tipoModal${tipo.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		                          <div class="modal-dialog">
		                            <div class="modal-content">
		                              <div class="modal-header">
		                                <h5 class="modal-title" id="exampleModalLabel">Borrar tipo de atracción</h5>
		                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                              </div>
		                              <div class="modal-body">
		                                Vas a borrar un tipo de atracción. ¿Estás seguro/a?
		                              </div>
		                              <div class="modal-footer">
		                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		                                <a class="btn btn-danger" href="/visitatierramedia/admin/attraction-types/delete.do?id=${tipo.id}" role="button">Borrar</a>
		                              </div>
		                            </div>
		                          </div>
		                        </div>
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