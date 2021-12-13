<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <h1 class="mt-4">Formulario editar promoción</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item">
                <a href="/visitatierramedia/admin/">Tablero</a>
              </li>
              <li class="breadcrumb-item">
                <a href="/visitatierramedia/admin/promotions/edit.do">Editar</a>
              </li>
              <li class="breadcrumb-item active">Promocion</li>
            </ol>

            <div class="card mb-4">
              <div class="card-header">
                <i class="fas fa-edit"></i>
                Editar promoción
              </div>
              <div class="card-body">
                <div class="alert alert-warning alert-dismissible" role="alert">
                  <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="alert"
                    aria-label="Close"
                  ></button>
                  <i class="fas fa-exclamation-triangle mx-1"></i>
                  ¡Todos los campos son obligatorios!
                </div>
                <form action="/visitatierramedia/admin/promotions/edit.do" method="post" class="needs-validation" novalidate>
                  <jsp:include page="/admin/promotions/form.jsp"></jsp:include>
                  <div class="d-grid gap-2 d-md-block mt-5">
                    <button class="btn btn-warning" type="submit">Guardar</button>
                    <a onclick="window.history.back();" class="btn btn-secondary"
					 role="button">Cancelar</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
	    </main>
		<jsp:include page="/partials/admin/footer.jsp"></jsp:include>
	  </div>
	</div>
</body>
</html>