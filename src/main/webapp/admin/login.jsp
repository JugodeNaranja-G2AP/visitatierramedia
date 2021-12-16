<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/admin/head.jsp"></jsp:include>
</head>
<body class="bg-light">
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
					<!-- Admin Login Form -->
                    <form action="/visitatierramedia/admin/login" method="post">
                      <div class="form-floating mb-3">
                        <input
                          class="form-control"
                          id="inputNombre"
                          type="text"
                          minlength="3"
                          placeholder="Nombre de usuario o correo electrónico"
                          name="username"
                          required
                        />
                        <label for="inputNombre">Nombre de usuario o correo electrónico</label>
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
                      <div class="d-grid gap-2">
                        <button class="btn btn-primary btn-ingresar-login p-2 fs-5" type="submit">Ingresar</button>
                      </div>
                    </form>
                    <!-- Admin Login Form -->
                  </div>
                  <hr />
                  <div class="py-3">
                    <div class="fw-bold d-flex justify-content-center align-items-baseline">
                      <i class="fas fa-user-lock"></i>
                      <p class="ms-2">ADMIN PANEL LOGIN</p>
                    </div>
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