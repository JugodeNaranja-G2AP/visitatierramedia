<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
  <label for="nombre" class='form-label ${usuario.errors.get("nombre") != null ? "is-invalid" : "" }'>Nombre</label>
  <input
    type="text"
    class="form-control"
    id="nombre"
    placeholder="Nombre"
    name="nombre"
    minlength="3"
    maxlength="150"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    El campo nombre no puede estar vac�o. Debe
    contener al menos 3 caracteres. Ejemplo: Ari
  </div>
</div>
<div class="mb-3">
  <label for="inputPassword" class='form-label ${usuario.errors.get("contrasenia") != null ? "is-invalid" : "" }'>Contrase�a</label>
  <input
    class="form-control"
    id="inputPassword"
    type="password"
    placeholder="Contrase�a"
    name="password"
    pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor ingres� una contrase�a de usuario. La contrase�a
    debe contener al menos 8 caracteres, una min�scula, una
    may�scula y un car�cter especial sin puntos y comas. Ejemplo: Arilopez$12
  </div>
</div>
<div class="mb-3">
  <label for="presupuesto" class='form-label ${usuario.errors.get("presupuesto") != null ? "is-invalid" : "" }'
    >Presupuesto</label
  >
  <input
    type="number"
    class="form-control"
    id="presupuesto"
    placeholder="Presupuesto. Ejemplo: 15"
    name="presupuesto"
    min="3"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor, ingres� un monto v�lido. El saldo m�nimo son 3 monedas de oro.
  </div>
</div>
<div class="mb-3">
  <label for="tiempo" class='form-label ${usuario.errors.get("tiempoDisponible") != null ? "is-invalid" : "" }'
    >Tiempo disponible</label
  >
  <input
    type="text"
    inputmode="decimal"
    class="form-control"
    id="tiempo"
    placeholder="Tiempo disponible. Ejemplo: 2.5"
    name="tiempo"
    min="0.5"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor, ingres� horas decimales v�lidas. El tiempo m�nimo es 0.5 hs
  </div>
</div>
<div class="mb-3">
  <label for="tipo_atraccion" class='form-label ${atraccion.errors.get("tipo") != null ? "is-invalid" : "" }'
    >Tipo de atracci�n preferida</label
  >
  <select
    class="form-select"
    aria-label="select tipo atraccion"
    id="tipo_atraccion"
    name="tipo_atraccion"
    required
  >
    <option value="" selected disabled>
      -- Seleccionar --
    </option>
    <c:forEach items="${tipos}" var="tipo">  
      <option value="${tipo.nombre}"><c:out value="${tipo.nombre}"></c:out></option>
    </c:forEach>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor elig� una de las opciones. Ejemplo: Aventura
  </div>
</div>

<div class="mb-3">
  <label for="imagen_usuario" class='form-label ${usuario.errors.get("imagen") != null ? "is-invalid" : "" }'
    >Imagen</label
  >
  <input
    class="form-control"
    type="url"
    id="imagen_usuario"
    name="imagen_usuario"
    placeholder="imagen URI"
    required
  />
  <div class="valid-feedback">ok!</div>
   <div class="invalid-feedback">
    La imagen debe ser una url v�lida.
  </div>
</div>