<%@page import="model.PromoAxB"%>
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
	            <h1 class="mt-4">Promociones de Tierra Media</h1>
	            <ol class="breadcrumb mb-4">
	              <li class="breadcrumb-item">
	                <a href="/visitatierramedia/admin/">Tablero</a>
	              </li>
	              <li class="breadcrumb-item active">Promociones</li>
	            </ol>
	            
	            <div class="mb-4">
					<a href="/visitatierramedia/admin/promotions/create.do" class="btn btn-primary" role="button"> 
						<i class="fas fa-plus"></i> 
						Nueva Promoción
					</a>
				</div>
	            
	            <div class="card mb-4">
	              <div class="card-header">
	                <i class="fas fa-table me-1"></i>
	                DataTable Promociones
	              </div>
	              <div class="card-body">
	                <table class="table-striped" id="datatablesSimple">
	                  <thead>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Descripción</th>
	                      <th>Tipo</th>
	                      <th>Tipo de promo</th>
	                      <th>Lista de atracciones</th>
	                      <th>Atraccion gratis</th>
	                      <th>Precio absoluto</th>
	                      <th>Porcentaje descuento</th>
	                      <th>Acciones</th>
	                    </tr>
					  </thead>       
		              <tfoot>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Descripción</th>
	                      <th>Tipo</th>
	                      <th>Tipo de promo</th>
	                      <th>Lista de atracciones</th>
	                      <th>Atraccion gratis</th>
	                      <th>Precio absoluto</th>
	                      <th>Porcentaje descuento</th>
	                      <th>Acciones</th>
	                    </tr>
		              </tfoot>
		              <tbody>
		              
		              
		             <c:forEach items="${promociones}" var="promocion">
		              	
	                     <tr>
	                      <td><c:out value="${promocion.nombre}"></c:out></td>
	                      <td><c:out value="${promocion.descripcion}"></c:out></td>
	                      <td><c:out value="${promocion.tipo.nombre}"></c:out></td>
	                      <td><c:out value="${promocion.clase}"></c:out></td>
	                      <td> 

                          <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${promocion.id}" aria-expanded="true" aria-controls="collapsedOne">
                                  Atracciones
                                </button>
                              </h2>
                              <div id="collapse${promocion.id}" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                  <ul>
                                  	<c:forEach items="${promocion.atracciones}" var="atraccion">
                                    <li>><c:out value="${atraccion.nombre}"></c:out></li>
                                    </c:forEach>
                                  </ul>
                                  <div class="text-center"><a href="/visitatierramedia/admin/promotions/listattractions.do?id=${promocion.id}">Ver mas -></a></div>
                                </div>
                              </div>
                            </div>
                          </div>
	                      </td>
	                      <c:choose>
	                      	<c:when test="${promocion.clase == 'PROMOAXB'}">        		
	                      	  <td><c:out value="${promocion.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>
	                      </c:choose>
	                      <c:choose>
	                      	<c:when test="${promocion.clase == 'PROMO_ABSOLUTA'}">
	                      	  <td><c:out value="${promocion.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>
	                      </c:choose>
	                      <c:choose>
	                      	<c:when test="${promocion.clase == 'PROMO_PORCENTUAL'}">
	                      	  <td><c:out value="${promocion.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>  
	                      </c:choose>
	                      
	                      <td class = "text-center">
	                      	<a class="btn btn-warning m-1" href="/visitatierramedia/admin/promotions/edit.jsp" role="button" title="Editar">
	                          <i class="fas fa-pencil-alt"></i>
	                        </a>
	                        <!-- Button trigger modal -->
	                        <button type="button" class="btn btn-danger" title="Borrar" data-bs-toggle="modal" data-bs-target="#adminModal${promocion.id}">
	                          <i class="fas fa-trash-alt"></i>
	                        </button>
	
	                        <!-- Modal -->
	                        <div class="modal fade" id="adminModal${promocion.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	                          <div class="modal-dialog">
	                            <div class="modal-content">
	                              <div class="modal-header">
	                                <h5 class="modal-title" id="exampleModalLabel">Borrar promo</h5>
	                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                              </div>
	                              <div class="modal-body">
	                                Vas a borrar una promo. ¿Estás seguro/a?
	                              </div>
	                              <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	                                <a class="btn btn-danger" href="/visitatierramedia/admin/promotions/delete.do?id=${promotion.id}" role="button">Borrar</a>
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