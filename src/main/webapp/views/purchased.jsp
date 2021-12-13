<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
    href="https://fonts.googleapis.com/css2?family=Barlow+Semi+Condensed:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
    rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
<link rel="shortcut icon" href="/visitatierramedia/assets/img/icons/loguitoICO.ico">
<link rel="stylesheet" href="/visitatierramedia/assets/sass/custom.css">
<link href="/visitatierramedia/assets/css/compras.css" rel="stylesheet">
<script type="text/javascript" src="/visitatierramedia/assets/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js " defer></script>
<title>Compras</title>
</head>
<body>
	<header>
		<jsp:include page="/partials/navbar.jsp"></jsp:include>
	</header>
	<main class="content-wrapper min-vh-100 pt-5 ">
	    <div class="container">
	      <h3 class="my-5 fw-bold ps-4">Compras</h3>
	      <c:choose>
	      		<c:when test="${productos.isEmpty()}">
	      		<!--- Si no tiene compras se muestra lo siguiente -->
	      		<small>0 compras</small>
		        <div class="card">
			        <div class="card-body bg-light">
			          <p>No tenés ninguna compra en estos momentos. En <a class="text-decoration-none" href="/visitatierramedia/products.do" target="_blank" rel="noopener noreferrer">ofertas</a> 
			            podés conocer más acerca de nuestros productos y visitas a Tierra Media. </p>
			        </div>
			     </div> 
		      	<!--- Fin de no tiene compras --->
	      		</c:when>
	      		<c:otherwise>
	      			<small><c:out value="${productos.size()}"></c:out> compras</small>
	      			<c:forEach items="${productos}" var="producto">
	      				<c:choose>
	      					<c:when test="${producto.esPromocion()}">
						      <div class="card mt-3">
						        <div class="card-body">
						          <div class="row g-0 gx-2">
						            <div class="col-lg-2">
						              <img class="img-fluid rounded-start rounded-2" src='<c:out value="${producto.atracciones.get(0).imagen}"></c:out>' alt="imagen-compras">
						            </div>
						            <div class="col-lg-8">
						              <h5 class="card-title mt-2 mt-lg-0 mt-xl-0 mt-xxl-0"><c:out value="${producto.nombre}"></c:out></h5>
						              <span class="badge bg-info text-black mb-1">Promoción</span>
						              <span class="badge bg-success mb-1"><c:out value="${producto.tipo.nombre}"></c:out></span>
						              <p class="card-text mt-2 mb-3 my-lg-0 my-xl-0 my-xxl-0">
						              <c:out value="${producto.descripcion}"></c:out>
						              </p>
						            </div>
						            <div class="col-lg-2 text-center my-auto">
						              <a class="btn btn-warning shadow-sm" href="/visitatierramedia/product-details.do?id=${producto.id}" id="btn-compra" role="button">Ver compra</a>
						            </div>
						          </div>
						        </div>
						      </div>
				      	</c:when>
				      	<c:otherwise>
				      		<div class="card mt-3">
						        <div class="card-body">
						          <div class="row g-0 gx-2">
						            <div class="col-lg-2">
						              <img class="img-fluid rounded-start rounded-2" src='<c:out value="${producto.imagen}"></c:out>' alt="imagen-compras">
						            </div>
						            <div class="col-lg-8">
						              <h5 class="card-title mt-2 mt-lg-0 mt-xl-0 mt-xxl-0"><c:out value="${producto.nombre}"></c:out></h5>
						              <span class="badge bg-info text-black mb-1">Atracción</span>
						              <span class="badge bg-success mb-1"><c:out value="${producto.tipo.nombre}"></c:out></span>
						              <p class="card-text mt-2 mb-3 my-lg-0 my-xl-0 my-xxl-0">
						              <c:out value="${producto.descripcion}"></c:out>
						              </p>
						            </div>
						            <div class="col-lg-2 text-center my-auto">
						              <a class="btn btn-warning shadow-sm" href="/visitatierramedia/product-details.do?id=${producto.id}" id="btn-compra" role="button">Ver compra</a>
						            </div>
						          </div>
						        </div>
						      </div>
				      	
				      	</c:otherwise>
				      </c:choose>
				    </c:forEach>
	      		</c:otherwise>
	      </c:choose>
	      
	
	      <!-- Paginación --->
	      <nav class="my-5" aria-label="paginacion-compras">
	        <ul class="pagination justify-content-end">
	          <li class="page-item disabled">
	            <span class="page-link">Previous</span>
	          </li>
	          <li class="page-item"><a class="page-link" href="#">1</a></li>
	          <li class="page-item active" aria-current="page">
	            <span class="page-link">2</span>
	          </li>
	          <li class="page-item"><a class="page-link" href="#">3</a></li>
	          <li class="page-item">
	            <a class="page-link" href="#">Next</a>
	          </li>
	        </ul>
	      </nav>
	      <!-- Paginación --->
	
	    </div>
	  </main>
	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>
</html>