<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
  <label for="nombre" class='form-label ${tipo.errors.get("nombre") != null ? "is-invalid" : "" }'>Nombre</label>
  <input
    type="text"
    class="form-control"
    id="nombre"
    placeholder="Nombre"
    name="nombre"
    minlength="3"
    maxlength="150"
    value="${tipo.nombre}"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    El campo nombre no puede estar vacío. Debe contener al
    menos 5 caracteres. 
  </div>
</div>