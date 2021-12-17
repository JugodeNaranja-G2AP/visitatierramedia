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
	            <h1 class="mt-4">Itinerario de <c:out value="${usuario.nombre}"></c:out></h1>
	            <ol class="breadcrumb mb-4">
	              <li class="breadcrumb-item">
	                <a href="/visitatierramedia/admin/">Tablero</a>
	              </li>
	              <li class="breadcrumb-item">
	              	<a href="/visitatierramedia/admin/users/data-table.do">Usuarios</a>
	              </li>
	              <li class="breadcrumb-item active">Itinerario</li>
	            </ol>
	            
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
	                      <th>Atracción gratis</th>
	                      <th>Precio absoluto</th>
	                      <th>Porcentaje descuento</th>
	                    </tr>
					  </thead>       
		              <tfoot>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Descripción</th>
	                      <th>Tipo</th>
	                      <th>Tipo de promo</th>
	                      <th>Lista de atracciones</th>
	                      <th>Atracción gratis</th>
	                      <th>Precio absoluto</th>
	                      <th>Porcentaje descuento</th>
	                    </tr>
		              </tfoot>
		              <tbody>
		              
		              
		             <c:forEach items="${productos}" var="producto">
		              	<c:if test="${producto.esPromocion()}"> 
	                     <tr>
	                      <td><c:out value="${producto.nombre}"></c:out></td>
	                      <td><c:out value="${producto.descripcion}"></c:out></td>
	                      <td><c:out value="${producto.tipo.nombre}"></c:out></td>
	                      <td><c:out value="${producto.clase}"></c:out></td>
	                      <td> 
							<!--   Lista de atracciones   -->
                          <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                              <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                  Atracciones
                                </button>
                              </h2>
                              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                <div class="accordion-body">
                                  <ul>
                                  	<c:forEach items="${producto.obtenerAtracciones()}" var="atraccion">
                                    <li>><c:out value="${atraccion.nombre}"></c:out></li>
                                    </c:forEach>
                                  </ul>
                                  <div class="text-center"><a href="/visitatierramedia/admin/promotions/AttractionList.do?id=${producto.id}">Ver mas -></a></div>
                                </div>
                              </div>
                            </div>
                          </div>
	                      </td>
	                      <c:choose>
	                      	<c:when test="${producto.clase =='PROMOAXB')}">        		
	                      	  <td><c:out value="${producto.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>
	                      </c:choose>
	                      <c:choose>
	                      	<c:when test="${producto.clase =='PROMO_ABSOLUTA'}">
	                      	  <td><c:out value="${producto.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>
	                      </c:choose>
	                      <c:choose>
	                      	<c:when test="${producto.clase =='PROMO_PORCENTUAL'}">
	                      	  <td><c:out value="${producto.beneficio()}"></c:out></td>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<td>----</td>
	                      	</c:otherwise>  
	                      </c:choose>
	                      	                         
	                    </tr>
	                    
	            	  </c:if>
	            	  <c:if test="${producto.esPromocion() == null}">
	            	  	<tr>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>
	                      <td></td>   
	                    </tr>
	            	  </c:if>
	                </c:forEach>
	                  </tbody>
	                </table>
	              </div>
	            </div>
	            <!-- Fin data-table promociones -->
	            
	            <!-- Data-table Atracciones -->
	            <div class="card mb-4">
	              <div class="card-header">
	                <i class="fas fa-table me-1"></i>
	                DataTable Atracciones
	              </div>
	              <div class="card-body">
	                <table class="table-striped" id="datatablesSimple2">
	                  <thead>
	                    <tr>
	                      <th>Nombre</th>
	                      <th>Descripción</th>
	                      <th>Costo</th>
	                      <th>Tiempo</th>
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
	                      <th>Tiempo</th>
	                      <th>Cupo</th>
	                      <th>Tipo</th>
	                      <th>Imagen</th>
	                    </tr>
	                  </tfoot>
	                  <tbody>
	                  <c:forEach items="${productos}" var="producto">
		                <c:if test="${!producto.esPromocion()}"> 
	
	                    <tr>
	                      <td><c:out value="${producto.nombre}"></c:out></td>
	                      <td><c:out value="${producto.descripcion}"></c:out></td>
	                      <td><c:out value="${producto.costoDeVisita}"></c:out></td>
	                      <td><c:out value="${producto.tiempoDeVisita}"></c:out></td>
	                      <td><c:out value="${producto.cupoDePersonas}"></c:out></td>
	                      <td><c:out value="${producto.tipo.nombre}"></c:out></td>
	                      <td><c:out value="${producto.imagen}"></c:out></td>
	                    </tr>
	                    
	                    </c:if>
	                    <c:if test="${!producto.esPromocion() == null}">
	                    <tr>
	                    <td></td>
	                    <td></td>
	                    <td></td>
	                    <td></td>
	                    <td></td>
	                    <td></td>
	                    <td></td>
	                    </tr>
	                    </c:if>
		              </c:forEach>
	                  </tbody>
	                </table>
	              </div>
	            </div>
				<!--   Data-table Atracciones -->
				
				<a class="btn btn-primary mb-4" href="/visitatierramedia/admin/users/data-table.do" role="button">
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