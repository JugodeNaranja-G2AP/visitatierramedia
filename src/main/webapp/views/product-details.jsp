<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="utils.Reloj" %>   
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<title>Detalles de producto</title>
</head>
<body>
	<header>
		<jsp:include page="/partials/navbar.jsp"></jsp:include>
	</header>
	<main class="content-wrapper py-5">
        <div class="container">
            <article>
                <div class="row">
                    <div class="col-12 col-md-9">
                        <h3 class="mt-5 fw-bold ps-3">Detalles de oferta</h3>
                        <h2 class="subtitulo mb-5 ps-4">Ofertas Personalizadas</h2>
                    </div>
                    
					<!--   Mensajes de error o éxito -->
                    <c:if test="${flash != null}">
                    	<c:choose>
                    		<c:when test="${errors != null}">
                    			<div class="alert alert-danger red-alert" role="alert">
			                        <div>
			                            <p>
			                                <span class="me-1"><i class="fas fa-exclamation-circle"></i></span> 
			                                <c:out value="${flash}"></c:out>
			                            </p>
			                            <ul>
			                                <c:forEach items="${errors}" var="entry">
												<li><c:out value="${entry.getValue()}"></c:out></li>
											</c:forEach>
			                            </ul>
			                        </div>
			                    </div>	
                    		</c:when>
                    		<c:otherwise>
                    			<div class="alert alert-success" role="alert">
			                        <p>
			                            <i class="fas fa-check-circle me-1"></i>
			                            <c:out value="${flash}"></c:out>
			                        </p>
			                    </div>
                    		</c:otherwise>
                    	</c:choose>             
                    </c:if>
                    
                    
                  <c:choose>
                  	<c:when test="${producto.esPromocion()}">
                    <div class="card card-principal text-center mb-5 shadow">
                        <div class="card-body p-0">
                            <div class="d-flex">
                                <h5 class="card-title fs-4 pt-2 ps-2 fs-3 fw-bold me-auto text-start">Promo "<c:out value="${producto.nombre}"></c:out>"</h5>
                                <div class="d-inline justify-content-center align-item-center ">
                                    <span class="badge bg-warning text-black mx-1">Promoción</span>
                                    <span class="badge bg-success "><c:out value="${producto.getTipo().getNombre()}"></c:out></span>
                                </div>
                            </div>
                            <p class="card-text card-descripcion-detalle fs-5"><c:out value="${producto.descripcion}"></c:out></p>
                            <ul class="nav nav-tabs " id="myTab" role="tablist">
                            <c:forEach items="${producto.atracciones}" var="atraccion">
                            	<c:choose>
                            		<c:when test="${producto.atracciones.indexOf(atraccion) == 0}">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab"
                                        data-bs-target="#home" type="button" role="tab" aria-controls="home"
                                        aria-selected="true"><c:out value="${atraccion.nombre}"></c:out></button>
                                </li>
                                	</c:when>
                                	<c:otherwise>
                                	<li class="nav-item" role="presentation">
	                                    <button class="nav-link" id="profile-tab" data-bs-toggle="tab"
	                                        data-bs-target='#<c:out value="${atraccion.nombre}"></c:out>' type="button" role="tab" aria-controls="profile"
	                                        aria-selected="false"><c:out value="${atraccion.nombre}"></c:out></button>
                                	</li>
                                	</c:otherwise>
                                </c:choose>
                            </c:forEach>

                            </ul>
                            <div class="tab-content" id="myTabContent">
                            <c:forEach items="${producto.obtenerAtracciones()}" var="atraccion">
                            	<c:choose>
                            		<c:when test="${producto.obtenerAtracciones().indexOf(atraccion) == 0}">
                                <div class="tab-pane fade show active" id="home" role="tabpanel"
                                    aria-labelledby="home-tab">
                                    <div class="card mb-3 border-top-0">
                                        <div class="row g-0">
                                            <div class="col-md-8">
                                                <div class="card-body fs-5">
                                                    
                                                    <p class="card-text"><c:out value="${atraccion.descripcion}"></c:out></p>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <img src='<c:out value="${atraccion.imagen}"></c:out>'
                                                    class="img-fluid rounded-end card-foto" alt='<c:out value="${atraccion.nombre}"></c:out>'>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                  </c:when>
                                  <c:otherwise>
                                  	<div class="tab-pane fade" id='<c:out value="${atraccion.nombre}"></c:out>' role="tabpanel" aria-labelledby="profile-tab">
	                                    <div class="card mb-3 border-top-0">
	                                        <div class="row g-0">
	                                            <div class="col-md-8">
	                                                <div class="card-body fs-5">
	                                                    
	                                                    <p class="card-text"><c:out value="${atraccion.descripcion}"></c:out></p>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-4">
	                                                <img src='<c:out value="${atraccion.imagen}"></c:out>'
	                                                    class="img-fluid rounded-end card-foto" alt='<c:out value="${atraccion.nombre}"></c:out>'>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
                                  
                                  </c:otherwise>
                                </c:choose>
                               </c:forEach>

                                <ul class="list-group list-group-flush card-datos text-end">
                                    <li class="list-group-item dato-descuento text-muted p-0 fs-6 "><del> <c:out value="${producto.ahorro() + producto.getCosto()}"></c:out> oros</del>
                                    </li>
                                    <li class="list-group-item dato-oro fs-3 p-0 "><c:out value="${producto.getCosto()}"></c:out> oros</li>
							<!--      <li class="list-group-item dato-promo p-0 fs-5 ">¡Doriath es gratis!</li> -->
                                    <li class="list-group-item dato-cupo text-muted p-0 ">¡Quedan <c:out value="${producto.getCupoMin()}"></c:out> lugares!</li>
                                </ul>
                            </div>
                        </div>
                        <div class="container d-flex justify-content-end mb-3 pe-0">
                        	<c:choose>
                        		<c:when test="${usuario.comproElProducto(producto)}">
                        			<a href="/visitatierramedia/product-details/purchase.do" class="btn btn-primary btn-comprar fs-4 shadow disabled">Comprado!</a>
                        		</c:when>
                        		<c:when test="${!producto.puedeSerOfertadoA(usuario) }">
                        			<a href="/visitatierramedia/product-details/purchase.do" class="btn btn-primary btn-comprar fs-4 shadow disabled">Comprar</a>
                        		</c:when>
                        		<c:otherwise>	
                           			 <a href="/visitatierramedia/product-details/purchase.do?id=${producto.id}&name=promocion" class="btn btn-primary btn-comprar mt-3 fs-4">Comprar</a>
                        		</c:otherwise>
                        	</c:choose>
                        </div>
                    </div>
					<!--  Fin Promoción -->
					</c:when>
					<c:otherwise>
					<!-- 	Atracción -->
                    <div class="card card-principal my-3 shadow">
                        <img src='<c:out value="${producto.imagen}"></c:out>' class="card-img-top card-big pt-3" alt="minasT">
                        <div class="card-body">
                            <div class="d-flex ">
                                <h5 class="card-title fs-4 me-auto text-start"><c:out value="${producto.nombre}"></c:out></h5>
                                <div class="d-inline justify-content-center align-item-center ">
                                    <span class="badge bg-info text-black mb-1">Atracción</span>
                                    <span class="badge bg-success mb-1"><c:out value="${producto.getTipo().getNombre()}"></c:out></span>
                                </div>
                            </div>
                            <p class="card-text"><c:out value="${producto.descripcion}"></c:out></p>
                        </div>

                        <ul class="list-group list-group-flush card-datos text-end">
                            <li class="list-group-item dato-oro fs-3 p-0 "><c:out value="${producto.costoDeVisita}"></c:out> oros</li>
                            <li class="list-group-item fs-5 text-muted p-0 ">Tiempo de visita: <c:out value="${Reloj.conversor(producto.tiempoDeVisita)}"></c:out></li>
                            <li class="list-group-item dato-cupo text-muted p-0 ">¡Quedan <c:out value="${producto.cupoDePersonas}"></c:out> lugares!</li>
                        </ul>
                        <div class="container d-flex justify-content-end my-3 pe-0">
                       	<c:choose>
                       		<c:when test="${usuario.comproElProducto(producto)}">
                       			<a href="/visitatierramedia/product-details/purchase.do" class="btn btn-primary btn-comprar fs-4 shadow disabled">Comprado!</a>
                       		</c:when>
                       		<c:when test="${!producto.puedeSerOfertadoA(usuario) }">
                       			<a href="/visitatierramedia/product-details/purchase.do" class="btn btn-primary btn-comprar fs-4 shadow disabled">Comprar</a>
                       		</c:when>
                       		<c:otherwise>
                           <a href="/visitatierramedia/product-details/purchase.do?id=${producto.id}&name=atraccion" class="btn btn-primary btn-comprar shadow fs-4">Comprar</a>
                       		</c:otherwise>
                       	
                       	</c:choose>
                        </div>
                    </div>
					<!--	Fin atracción -->
					</c:otherwise>
				</c:choose>	
                </div>
            </article>
        </div>
    </main>
	
	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>
</html>