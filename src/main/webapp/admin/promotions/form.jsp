<div class="mb-3">
  <label for="nombre" class="form-label">Nombre</label>
  <input
    type="text"
    class="form-control"
    id="nombre"
    placeholder="Nombre"
    name="nombre"
    minlength="3"
    maxlength="250"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    El campo nombre no puede estar vacío. Debe
    contener al menos 3 caracteres. Ejemplo: Río
  </div>
</div>
<div class="mb-3">
  <label for="descripcion" class="form-label"
    >Descripción</label
  >
  <textarea
    type="text"
    class="form-control"
    id="descripcion"
    placeholder="Escribir una breve descripción de la promo..."
    name="descripcion"
    minlength="50"
    required
  ></textarea>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor rellená el campo con una descripción acerca de
    la promoción. La descripción debe contener al menos 50
    caracteres.
  </div>
</div>
<div class="mb-3">
  <label for="tipo" class="form-label"
    >Tipo según atracciones que incluye</label
  >
  <select
    class="form-select"
    aria-label="select tipo"
    id="tipo"
    name="tipo"
    required
  >
    <option value="" selected disabled>
      -- Seleccionar --
    </option>
    <option value="1">Degustación</option>
    <option value="2">Aventura</option>
    <option value="3">Paisaje</option>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí una de las opciones. Ejemplo: Aventura
  </div>
</div>
<div class="mb-3">
  <label for="clase_de_promo" class="form-label"
    >Clase de promoción</label
  >
  <select
    class="form-select"
    aria-label="select clase_de_promo"
    id="clase_de_promo"
    name="clase_de_promo"
    required
  >
    <option value="" selected disabled>
      -- Seleccionar --
    </option>
    <option value="1">PromoAxB</option>
    <option value="2">Promo_Absoluta</option>
    <option value="3">Promo_Porcentual</option>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí una de las opciones. Ejemplo: PromoAxB
  </div>
</div>
<div class="mb-3">
  <label for="lista_de_atracciones" class="form-label"
    >Atracciones que incluye</label
  >
  <select
    class="form-select"
    multiple
    aria-label="lista de atracciones"
    id="lista_de_atracciones"
    name="lista_de_atracciones"
    required
  >
    <option value="" disabled>
      -- Seleccionar atracciones --
    </option>
    <option value="1">One</option>
    <option value="2">Two</option>
    <option value="3">Three</option>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí al menos una atracción o más de la lista.
  </div>
</div>
<div class="mb-3">
  <label for="imagen_promo" class="form-label">Imagen</label>
  <input
    class="form-control"
    type="text"
    id="imagen_promo"
    name="imagen_promo"
    placeholder="imagen URI"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor subí una imagen que mejor describa la promoción.
  </div>
</div>