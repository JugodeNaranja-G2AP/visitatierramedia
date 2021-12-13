<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  El usuario puede estar o no logueado -->
<nav class="navbar navbar-expand-md navbar-color navbar-light fixed-top bgcolor"
            aria-label="Fourth navbar example">
	 <div class="container">
	     <a class="navbar-brand" href="index.html">
	         <img class="logo" src="/visitatierramedia/assets/img/logos/logoVTM.png" alt="" width="190px" />
	     </a>
	     <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
	         data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false"
	         aria-label="Toggle navigation">
	         <span class="navbar-toggler-icon"></span>
	     </button>
	
	     <div class="navbar-collapse collapse" id="navbarsExample04">
	         <ul class="navbar-nav me-auto mb-2 mb-md-0">
	             <li class="nav-item">
	                 <a class="nav-link nav-text fs-4 " aria-current="page" href="#destinos">Inicio</a>
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
	          <a href="/visitatierramedia/login" class="btn btn-log d-flex align-items-center text-decoration-none" role="button">
	              <img class="logoInicio text-white pe-1" src="/visitatierramedia/assets/img/icons/logIcoWhite.png" alt="icono de login">
	              Iniciar Sesión
	          </a>
			<!--  Fin botón de inicio de sesión -->
		  </c:otherwise>
		</c:choose>
	         <!-- Modal -->
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
			<!--   Fin del modal -->
	        </div>
	    </div>
</nav>