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
					<h1 class="mt-4">Atracciones de Tierra Media</h1>
		            <ol class="breadcrumb mb-4">
		              <li class="breadcrumb-item">
		                <a href="/visitatierramedia/admin/">Tablero</a>
		              </li>
		              <li class="breadcrumb-item active">Atracciones</li>
		            </ol>
		            <div class="row">
                       <div class="col-xl-3 col-md-6">
                           <div class="card cartaUno text-white mb-4">
                               <div class="card-body d-flex align-items-center justify-content-between">
                                   <div class="title">Atracciones</div>
                                   <div class="text"><c:out value="${atracciones.size()}"></c:out></div>
                               </div>
                               <div class="card-footer d-flex align-items-center justify-content-between">
                                   <a class="small text-white stretched-link" href="/visitatierramedia/admin/attractions/data-table.do">
                                     Ver Detalles
                                   </a>
                                   <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                               </div>
                           </div>
                       </div>
                       <div class="col-xl-3 col-md-6">
                           <div class="card cartaDos text-white mb-4">
                               <div class="card-body d-flex align-items-center justify-content-between">
                                   <div class="title">Tipos de Atracción</div>
                                   <div class="text"><c:out value="${tipos.size()}"></c:out></div>
                               </div>
                               <div class="card-footer d-flex align-items-center justify-content-between">
                                   <a class="small text-white stretched-link" href="/visitatierramedia/admin/attraction-types/data-table.do">
                                   	  Ver Detalles
                                   </a>
                                   <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                               </div>
                           </div>
                       </div>
                       <div class="col-xl-3 col-md-6">
                           <div class="card cartaTres text-white mb-4">
                               <div class="card-body d-flex align-items-center justify-content-between">
                                   <div class="title">Promociones</div>
                                   <div class="text"><c:out value="${promociones}"></c:out></div>
                               </div>
                               <div class="card-footer d-flex align-items-center justify-content-between">
                                   <a class="small text-white stretched-link" href="/visitatierramedia/admin/promotions/data-table.do">Ver Detalles</a>
                                   <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                               </div>
                           </div>
                       </div>
                       <div class="col-xl-3 col-md-6">
                           <div class="card cartaCuatro text-white mb-4">
                               <div class="card-body d-flex align-items-center justify-content-between">
                                   <div class="title">Usuarios</div>
                                   <div class="text"><c:out value="${usuarios}"></c:out></div>
                               </div>
                               <div class="card-footer d-flex align-items-center justify-content-between">
                                   <a class="small text-white stretched-link" href="/visitatierramedia/admin/users/data-table.do">
                                   	  Ver Detalles
                                   </a>
                                   <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                               </div>
                           </div>
                       </div>
                   </div>
		            
		            <c:if test="${flash != null}">
						<div class="alert alert-success alert-dismissible">
							<button type="button" class="btn-close" data-bs-dismiss="alert"
									aria-label="Close"></button>
							<i class="fas fa-check-circle me-1"></i>
							<span><c:out value="${flash}"></c:out></span>
						</div>	
					</c:if>
		            
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
				                      <th>Acciones</th>
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
				                      <th>Acciones</th>
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
				                      <td class="text-center">
				                        <a class="btn btn-warning m-1" href="/visitatierramedia/admin/attractions/edit.do?id=${atraccion.id}" role="button" title="Editar">
				                          <i class="fas fa-pencil-alt"></i>
				                        </a>
				                        <!-- Button trigger modal -->
				                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#atraccionModal${atraccion.id}" title="Borrar">
				                          <i class="fas fa-trash-alt"></i>
				                        </button>
				
				                        <!-- Modal -->
				                        <div class="modal fade" id="atraccionModal${atraccion.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				                          <div class="modal-dialog">
				                            <div class="modal-content">
				                              <div class="modal-header">
				                                <h5 class="modal-title" id="exampleModalLabel">Borrar Atracción</h5>
				                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				                              </div>
				                              <div class="modal-body">
				                                Vas a borrar una atracción. ¿Estás seguro/a?
				                              </div>
				                              <div class="modal-footer">
				                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
				                                <a class="btn btn-danger" href="/visitatierramedia/admin/attractions/delete.do?id=${atraccion.id}" role="button">Borrar</a>
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