<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
  <label for="nombre" class='form-label ${admin.errors.get("nombre") != null ? "is-invalid" : "" }'>Nombre</label>
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
    El campo nombre debe
    contener al menos 3 caracteres. Ejemplo: Ari
  </div>
</div>
<div class="mb-3">
  <label for="inputPassword" class='form-label ${admin.errors.get("contrasenia") != null ? "is-invalid" : "" }'>Contraseña</label>
  <input
    class="form-control"
    id="inputPassword"
    type="password"
    placeholder="Contraseña"
    name="password"
    pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Debe contener al menos 8 caracteres, una minúscula, una
    mayúscula y un carácter especial sin puntos y comas. Ejemplo: Arilopez$12
  </div>
</div>
<div class="mb-3">
  <label for="correo" class='form-label ${admin.errors.get("correo") != null ? "is-invalid" : "" }'
    >Correo electrónico</label
  >
  <input
    type="email"
    class="form-control"
    id="correo"
    placeholder="Correo electrónico"
    name="correo"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Debe ser válido. Ejemplo: agustin@gmail.com
  </div>
</div>