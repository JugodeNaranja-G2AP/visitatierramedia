<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion sb-sidenav-light side-admin" id="sidenavAccordion">
		<div class="sb-sidenav-menu">
			<div class="nav">
				<div class="sb-sidenav-menu-heading titulos-tabla">Menu principal</div>
				<a class="nav-link side-lista" href="/visitatierramedia/admin/">
					<div class="sb-nav-link-icon">
						<i class="fas fa-tachometer-alt"></i>
					</div> Tablero
				</a>
				<div class="sb-sidenav-menu-heading titulos-tabla">Formularios</div>
				<a class="nav-link side-lista collapsed" href="#" data-bs-toggle="collapse"
					data-bs-target="#collapseLayouts" aria-expanded="false"
					aria-controls="collapseLayouts">
					<div class="sb-nav-link-icon">
						<i class="fas fa-folder-plus"></i>
					</div> Registrar
					<div class="sb-sidenav-collapse-arrow">
						<i class="fas fa-angle-down"></i>
					</div>
				</a>
				<div class="collapse" id="collapseLayouts"
					aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
					<nav class="sb-sidenav-menu-nested nav">
						<a class="nav-link" href="/visitatierramedia/admin/users/create.do">Nuevo
							Usuario</a> <a class="nav-link"
							href="/visitatierramedia/admin/attractions/create.do">Nueva
							Atracción</a> <a class="nav-link"
							href="/visitatierramedia/admin/promotions/create.do">Nueva
							Promoción</a> <a class="nav-link"
							href="/visitatierramedia/admin/attraction-types/create.do">Nuevo
							Tipo de Atracción</a> <a class="nav-link"
							href="/visitatierramedia/admin/create.do">Nuevo Admin</a>
					</nav>
				</div>
				<div class="sb-sidenav-menu-heading titulos-tabla">Tablas</div>
				<a class="nav-link side-lista" href="/visitatierramedia/admin/users/data-table.do">
					<div class="sb-nav-link-icon">
						<i class="fas fa-table"></i>
					</div> Usuarios
				</a> <a class="nav-link side-lista"
					href="/visitatierramedia/admin/attractions/data-table.do">
					<div class="sb-nav-link-icon">
						<i class="fas fa-table"></i>
					</div> Atracciones
				</a> <a class="nav-link side-lista"
					href="/visitatierramedia/admin/promotions/data-table.do">
					<div class="sb-nav-link-icon">
						<i class="fas fa-table"></i>
					</div> Promociones
				</a> <a class="nav-link side-lista"
					href="/visitatierramedia/admin/attraction-types/data-table.do">
					<div class="sb-nav-link-icon">
						<i class="fas fa-table"></i>
					</div> Tipos de atraccón
				</a> <a class="nav-link side-lista" href="/visitatierramedia/admin/data-table.do">
					<div class="sb-nav-link-icon">
						<i class="fas fa-table"></i>
					</div> Administradores
				</a>
			</div>
		</div>
		<div class="sb-sidenav-footer">
			<div class="small">Sesión iniciada como:</div>
			Admin <c:out value="${admin.nombre}"></c:out>
		</div>
	</nav>
</div>