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
										<c:set var="j" value= "1" />
										<c:forEach items="${productos}" var="producto">
											<c:choose>
												<c:when test="${producto.esPromocion()}">
													<!-- Promo -->
													<div class="col">
														<div class="card card-oferta ">
														
														<div id='carouselExampleIndicators<c:out value="${j}"></c:out>' class="carousel slide" data-interval="false">
                                            				<div class="carousel-indicators">
                                            				<c:set var="i" value= "1" />
                                            					<c:forEach items="${producto.atracciones}" var="atraccion">                                            					
                            										<c:choose>
                            											<c:when test="${producto.atracciones.indexOf(atraccion) == 0}">
                                            								<button type="button" data-bs-target='#carouselExampleIndicators<c:out value="${j}"></c:out>'
                                                    						data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                                    					</c:when>
                                										<c:otherwise>
                                                							<button type="button" data-bs-target='#carouselExampleIndicators<c:out value="${j}"></c:out>'
                                                    						data-bs-slide-to='<c:out value="${i}"></c:out>' aria-label='Slide <c:out value="${i+1}"></c:out>'></button>
                                                    					<c:set var="i" value= "${i+1}" />
                                                    					</c:otherwise>
                                                    				</c:choose>
                                                    			</c:forEach>
                                            				</div>
                                            				<div class="carousel-inner">
                                            				<c:forEach items="${producto.obtenerAtracciones()}" var="atraccion">
                            									<c:choose>
                            										<c:when test="${producto.obtenerAtracciones().indexOf(atraccion) == 0}">
                                            							<div class="carousel-item active">
                                                    						<img src='<c:out value="${atraccion.imagen}"></c:out>' class="card-img-top" id="img-ofertas" alt="...">
                                                						</div>
                                                					</c:when>
                                                					<c:otherwise>
                                                						<div class="carousel-item">
                                                    						<img src='<c:out value="${atraccion.imagen}"></c:out>' class="card-img-top" id="img-ofertas" alt="...">
                                                						</div>
                                                					</c:otherwise>
                                                				</c:choose>
                                                			</c:forEach>
                                                			</div>
                                                			<button class="carousel-control-prev" type="button" data-bs-target='#carouselExampleIndicators<c:out value="${j}"></c:out>' data-bs-slide="prev">
                                                				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                				<span class="visually-hidden">Previous</span>
                                            				</button>
                                            				<button class="carousel-control-next" type="button" data-bs-target='#carouselExampleIndicators<c:out value="${j}"></c:out>' data-bs-slide="next">
                                                				<span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                				<span class="visually-hidden">Next</span>
                                           			 		</button>
                                           			 	</div>
														
<%-- 															<img src='<c:out value="${producto.atracciones.get(0).imagen}"></c:out>' --%>
<!-- 																class="card-img-top" id="img-ofertas" alt="..."> -->
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
													<c:set var="j" value= "${j+1}" />
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