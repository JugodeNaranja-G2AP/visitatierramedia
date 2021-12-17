<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								<h3 class="mt-5 fw-bold ps-3">Ofertas</h3>
								<h2 class="subtitulo mb-5 ps-4">Ofertas Personalizadas</h2>
							</div>
							<div class="container-fluid">
								<div class="container pt-5">
									<div class="row row-cols-1 row-cols-md-3 g-4 mb-">
										<c:forEach items="${productos}" var="producto">
											<c:choose>
												<c:when test="${producto.esPromocion()}">
													<!-- Promo -->
													<div class="col">
														<div class="card card-oferta ">
															<img src='<c:out value="${producto.atracciones.get(0).imagen}"></c:out>'
																class="card-img-top" id="img-ofertas" alt="...">
															<div class="card-body d-flex flex-column">
																<h5 class="card-title">
																	<c:out value="${producto.nombre}"></c:out>
																</h5>
																<p class="card-text">
																	<c:out value="${producto.descripcion}"></c:out>
																</p>
																<div class="d-flex mt-auto align-items-end flex-column">
																	<a class="btn btn-detalle btn-secondary "
																		href="/visitatierramedia/product-details.do?id=${producto.id}&name=promocion"
																		role="button">
																		Detalles
																	</a>
																</div>
															</div>

														</div>
													</div>
													<!-- Fin de Promo -->
												</c:when>
												<c:otherwise>
													<!-- Atracción -->
													<div class="col">
														<div class="card card-oferta">
															<img src='<c:out value="${producto.imagen}"></c:out>'
																class="card-img-top" id="img-ofertas" alt="...">
																<div class="card-body d-flex flex-column">
																	<h5 class="card-title">
																		<c:out value="${producto.nombre}"></c:out>
																	</h5>
																	<p class="card-text">
																		<c:out value="${producto.descripcion}"></c:out>
																	</p>
																	<div class="d-flex mt-auto align-items-end flex-column">
																		<a class="btn btn-detalle btn-secondary "
																			href="/visitatierramedia/product-details.do?id=${producto.id}&name=atraccion"
																			role="button">
																			Detalles
																		</a>
																	</div>
																</div>

														</div>
													</div>
													<!-- Fin Atracción -->
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</article>
				</div>
			</main>

			<jsp:include page="/partials/footer.jsp"></jsp:include>
		</body>
		</html> 