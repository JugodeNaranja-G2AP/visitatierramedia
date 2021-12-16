<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navbar para pï¿½gs donde el usuario ya estï¿½ loguedo -->

<!-- Navbar -->
    <nav class="navbar navbar-expand-md navbar-color navbar-light fixed-top bgcolor" aria-label="Fourth navbar example">
      <div class="container">
        <a class="navbar-brand" href="/visitatierramedia/">
          <img class="logo" src="/visitatierramedia/assets/img/logos/logoVTM.png" alt="" width=190px />
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04"
          aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="navbar-collapse collapse" id="navbarsExample04">
          <ul class="navbar-nav me-auto mb-2 mb-md-0">
            <li class="nav-item">
              <a class="nav-link fs-4 nav-text" aria-current="page" href="#">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link fs-4 nav-text" href="/visitatierramedia/products.do">Ofertas</a>
            </li>
          </ul>
          <div class="btn-group">
            <button type="button" class="btn btn-menu dropdown-toggle d-flex align-items-center"
              data-bs-toggle="dropdown" aria-expanded="false">
             
              <img src='<c:out value="${usuario.imagen}"></c:out>' id="fotoPerfil" alt="foto perfil"
                class=" border border-2 border-success rounded-circle">
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
                  <img class="pb-1 pe-1" src="/visitatierramedia/assets/img/icons/sign-out-alt-solid.svg" alt="icono de salida">
                  Cerrar sesión
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>
<!-- Navbar -->