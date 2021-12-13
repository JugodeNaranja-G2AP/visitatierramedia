<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/admin/head.jsp"></jsp:include>
</head>
<body class="bg-image">
	<div id="layoutAuthentication">
      <div id="layoutAuthentication_content">
        <main>
          <div class="container my-5">
            <div class="row justify-content-center">
              <div class="col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
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
                          placeholder="Nombre de usuario"
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
        </main>
      </div>
    </div>
</body>
</html>