<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
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
<link href="/visitatierramedia/assets/css/styles.css" rel="stylesheet">

<script defer src="/visitatierramedia/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="/visitatierramedia/assets/js/custom.js" defer></script>


<title>VisitaTierraMedia</title>
</head>
<body>
	<header class="header">
		<!--  Navbar -->
	   <nav class="navbar navbar-expand-md navbar-light navbar-transp fixed-top bgcolor"
		  aria-label="Fourth navbar example">
		  <div class="container">
		      <a class="navbar-brand" href="#">
		          <img class="logo" src="/visitatierramedia/assets/img/logos/logo-blanco-oro.png" alt="" width=190px />
		      </a>
		      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		          data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false"
		          aria-label="Toggle navigation">
		          <span class="navbar-toggler-icon"></span>
		      </button>
		
		      <div class="navbar-collapse collapse" id="navbarsExample04">
		          <ul class="navbar-nav me-auto mb-2 mb-md-0">
		              <li class="nav-item">
		                  <a class="nav-link nav-text fs-4 " aria-current="page" href="/visitatierramedia/index.jsp">Inicio</a>
		              </li>
		              <li class="nav-item">
		                  <a class="nav-link nav-text fs-4 " href="#actividades">Actividades</a>
		              </li>
		              <li class="nav-item">
		                  <a class="nav-link nav-text fs-4 " href="#experiencias">Experiencias</a>
		              </li>
		              <li class="nav-item">
		              	<c:choose>
		              		<c:when test="${usuario != null}">
		              			<a class="nav-link nav-text fs-4 " href="/visitatierramedia/products.do">Ofertas</a>
		              		</c:when>
		              		<c:otherwise>
		                  		 <!-- Button trigger modal -->
          						<a class="nav-link nav-text fs-4" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">Ofertas</a>
		              		</c:otherwise>          	
		              	</c:choose>
		              </li>
		          </ul>
		          
		     <c:choose>
		        <c:when test="${usuario != null}">
		          <!-- Perfil Iniciado -->
		          <div class="btn-group">
		              <button type="button" class="btn btn-menu dropdown-toggle d-flex align-items-center"
		                  data-bs-toggle="dropdown" aria-expanded="false">
		                  
		          <img src='<c:out value="${usuario.imagen}"></c:out>' id="fotoPerfil" alt="foto perfil"
		                      class=" border border-2 border-success rounded-circle ">
		                  <p class="fs-5 ps-1 pt-2 nav-text"><c:out value="${usuario.nombre}"></c:out></p>
		
		              </button>
		              <ul class="dropdown-menu dropdown-menu-end">
		                  <li>
		                      <a class="dropdown-item " href="/visitatierramedia/purchased.do" aria-current="true">
		                          <img class="pb-2 pe-1" src="/visitatierramedia/assets/img/icons/cart-fill.svg" alt="icono de compras">
		                          Compras
		                      </a>
		                  </li>
		                  <li>
		                      <a class="dropdown-item" href="/visitatierramedia/profile.do?id=${usuario.id}">
		                          <img class="pb-1 pe-1" src="/visitatierramedia/assets/img/icons/person-fill.svg" alt="icono de perfil">
		                          Perfil
		                      </a>
		                  </li>
		                  <li>
		                      <a class="dropdown-item" href="/visitatierramedia/logout">
		                          <img class="pb-1 pe-1" src="/visitatierramedia/assets/img/icons/sign-out-alt-solid.svg"
		                              alt="icono de salida">
		                          Cerrar sesión
		                      </a>
		                  </li>
		              </ul>
		          </div>
		          <!-- FIN Perfil Iniciado  -->
		       </c:when> 
		       <c:otherwise>
				<!--  Botón de inicio de sesión -->
		          <a href="/visitatierramedia/login.jsp" class="btn btn-log d-flex align-items-center text-decoration-none" role="button">
		              <img class="logoInicio text-white pe-1" src="/visitatierramedia/assets/img/icons/logIcoWhite.png" alt="icono de login">
		              Iniciar Sesion
		          </a>
				<!--  Fin botón de inicio de sesión -->
				</c:otherwise>
			</c:choose>
				
		          <!-- Modal de inicio de sesión -->
		            <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-body"> 
					        <div class="py-4">
					          <div class="text-center px-3">
					            <img
					              class="mw-100 img-fluid"
					              src="/visitatierramedia/assets/img/logos/logoVTM.png"
					              alt="logo"
					            />
					          </div>
					        </div>
					        <div class="card-body">
					         <!-- Se muestra cuando el ingreso es incorrecto -->
			                  <c:if test="${flash != null}">
			                    <div class="alert alert-danger" role="alert">
			                      <i class="fas fa-exclamation-triangle mx-1"></i>
			                      <c:out value="${flash}" />
			                    </div>
			                  </c:if>
					          <form action="login" method="post">
					            <div class="form-floating mb-3">
					              <input
					                class="form-control"
					                id="inputNombre"
					                type="text"
					                placeholder="Arwen"
					                minlength="3"
					                name="username"
					                required
					              />
					              <label for="inputNombre">Nombre de usuario</label>
					            </div>
					            <div class="form-floating mb-3">
					              <input
					                class="form-control"
					                id="inputPassword"
					                type="password"
					                placeholder="Contraseña"
					                minlength="8"
					                name="password"
					                required
					              />
					              <label for="inputPassword">Contraseña</label>
					            </div>
					            <button
					              class="btn btn-primary btn-lg w-100"
					              type="submit"
					              >Ingresar</button
					            >
					          </form>
					        </div>
					        <hr />
					        <div class="text-center py-3">
					          <div class="small"><p>Ingresá para continuar</p></div>
					        </div>         
					      </div>
					    </div>
					  </div>
					</div>        
					<!--  Fin de Modal -->
					
		        </div>
		    </div>
		</nav>
	   
	   
	   
		<div id="carouselExampleCaptions" class="carousel slide carousel-fade" data-bs-ride="carousel">
           <div class="carousel-indicators">
               <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                   aria-current="true" aria-label="Slide 1"></button>
               <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                   aria-label="Slide 2"></button>
               <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                   aria-label="Slide 3"></button>
           </div>
           <div class="carousel-inner">
               <div class="carousel-item active">
                   <img src="/visitatierramedia/assets/img/TM-Fotos/eriador.jpg" class="d-block w-100 " alt="Imagen de eriador">
                   <div class="carousel-caption justify-content-center">
                       <div class="titulo" col-10 align-center>
                           <h1>Descubre, vive, respira Tierra Media</h1>
                           <div class="d-inline-flex p-2 bd-highlight">
                               <p>Montañas Azules - Eriador</p>
                           </div>
                       </div>
                   </div>
               </div>
               <div class="carousel-item">
                   <img src="/visitatierramedia/assets/img/TM-Fotos/rohan.jpg" class="d-block w-100" alt="Imagen de rohan">
                   <div class="carousel-caption justify-content-center">
                       <div class="titulo" col-10 align-center>
                           <h1>Descubre, vive, respira Tierra Media</h1>
                           <div class="d-inline-flex p-2 bd-highlight">
                               <p>Viñedos Puro - Rohan</p>
                           </div>
                       </div>
                   </div>
               </div>
               <div class="carousel-item">
                   <img src="/visitatierramedia/assets/img/TM-Fotos/mordor2.jpg" class="d-block w-100" alt="Imagen de mordor">
                   <div class="carousel-caption justify-content-center">
                       <div class="titulo" col-10 align-center>
                           <h1>Descubre, vive, respira Tierra Media</h1>
                           <div class="d-inline-flex p-2 bd-highlight">
                               <p>Volcan Orodruin - Mordor</p>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       </div>
       <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
           data-bs-slide="prev">
           <span class="carousel-control-prev-icon" aria-hidden="true"></span>
           <span class="visually-hidden">Previous</span>
       </button>
       <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
           data-bs-slide="next">
           <span class="carousel-control-next-icon" aria-hidden="true"></span>
           <span class="visually-hidden">Next</span>
       </button>
	</header>
	<main class="mt-5">
        <div class="container">
            <!--Section: Content-->
            <section class="seccion mx-auto my-md-5" id="destinos">
                <article>
                    <h4 class="subtitulo text-center">¡Bienvenido a Tierra Media!</h4>
                    <div class="row">
                        <div class="col-12 col-lg-6 bg-image hover-overlay ripple shadow-2-strong"
                            data-mdb-ripple-color="light">
                            <img src="/visitatierramedia/assets/img/TM-Fotos/tmmap.jpg" alt="Mapa" class="img-fluid" />
                            <a href="#!">
                                <div class="mask"></div>
                            </a>

                        </div>
                        <div class=" col-12 col-lg-6 mb-4">

                            <p class="fs-5">
                                Tierra Media y sus paisajes invitan a adentrarse en una experiencia fantástica, llena de
                                colores, sabores, sonidos que interpelan
                                a todos los turistas de todos los continentes.
                            </p>
                            <p class="fs-5"> Desde los más verdes valles de Eriador, hasta nuestros crudos montes de Mordor vivirás
                                un sinfín de aventuras que te harán sentir el valor y el vigor de nuestros primeros
                                habitantes.
                            </p>
                            <p class="fs-5 "> Podrás recorrer y sentir, la mas rica y antigua historia que definen lo que hoy somos...
                                lo que hoy es Tierra Media.
                            </p>
                        </div>
                    </div>
                </article>
            </section>
        </div>
        <section class=" seccion mx-auto my-md-5 " id="actividades">
            <div class="container text-center">
                <div class="card-group ">
                    <div class="col-12 col-md-4">
                        <div class="card h-100 ms-1 me-1">
                            <h4 class="subtitulo">Actividades</h4>
                        </div>
                    </div>
                    <div class="col-12 col-md-4">
                        <div class="card cardInter ps-1 pe-1 pb-1">
                            <div class="wrapper h-100 text-white">
                                <img class="card-img-top h-100" src="/visitatierramedia/assets/img/TM-Fotos/lacomarca3.jpg" alt="Comarca">
                                <div class="card-img-overlay d-flex align-items-end">
                                    <h3 class="card-title">Gastronomía</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-4">
                        <div class="card cardInter ps-1 pe-1 pb-1">
                            <div class="wrapper h-100 text-white">
                                <img class="card-img-top h-100" src="/visitatierramedia/assets/img/TM-Fotos/Beleriand.jpg" alt="fotoPaisaje">
                                <div class="card-img-overlay d-flex align-items-end">
                                    <h3 class=" card-title">Paisajismo</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-12">
                        <div class="card cardInter h-100 ps-1 pe-1 pt-1">
                            <div class="wrapper h-100 text-white">
                                <img src="/visitatierramedia/assets/img/TM-Fotos/gondor2.jpg" class="card-img-top card-big" alt="fotoAventura">
                                <div class="card-img-overlay card-big-txt d-flex align-items-end">
                                    <h3 class="card-title">Aventura</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </section> 
    </main>

	<jsp:include page="/partials/footer.jsp"></jsp:include>
	
</body>
</html>