<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="utils.Reloj" %> 
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

<script defer src="/visitatierramedia/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>

<title>Perfil</title>
</head>
<body>
	<header>
		<jsp:include page="/partials/navbar.jsp"></jsp:include>
	</header>
	<main class="content-wrapper py-5">
	    <div class="container">
	      <h3 class="my-5 fw-bold ps-4">Perfil</h3>
	      <div class="card mx-auto profile-card">
	        <div class="profile-card_image-wrapper">
	          <img src='<c:out value="${usuario.imagen}"></c:out>' class="card-img-top rounded-circle profile-card_image"
	            alt="foto de perfil">
	        </div>
	        <div class="card-body text-center">
	          <h5 class="card-title profile-card_title"><c:out value="${usuario.nombre}"></c:out></h5>
	          <div class="profile-card_atraccion-fav">
	            <span class="card-title">Tipo de atraccion favorita:</span>
	            <c:choose>
	            	<c:when test='${usuario.tipoAtraccionPreferida.nombre.equals("Paisaje")}'>
	            		<span class="card-title"><c:out value="${usuario.tipoAtraccionPreferida.nombre}"></c:out> 🌲</span>
	            	</c:when>
	            	<c:when test='${usuario.tipoAtraccionPreferida.nombre.equals("Degustacion")}'>
	            		<span class="card-title"><c:out value="${usuario.tipoAtraccionPreferida.nombre}"></c:out> 🥄</span>
	            	</c:when>
	            	<c:otherwise>
	            		<span class="card-title"><c:out value="${usuario.tipoAtraccionPreferida.nombre}"></c:out> ⚔</span>
	            	</c:otherwise>
	            </c:choose>
	            
	          </div>
	          <div class="profile-card_tiempo">
	            <span class="card-title">Tiempo disponible:</span>
	            <span class="card-title"><c:out value="${Reloj.conversor(usuario.tiempoDisponible)}"></c:out> ⏳</span>
	          </div>
	          <div class="profile-card_presupuesto">
	            <span class="card-title">Presupuesto:</span>
	            <span class="card-title"><c:out value="${usuario.presupuesto}"></c:out> monedas de oro 🟡</span>
	          </div>
	        </div>
	      </div>
	    </div>
	</main>
	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>
</html>