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
    El campo nombre no puede estar vac�o. Debe
    contener al menos 3 caracteres. Ejemplo: R�o
  </div>
</div>
<div class="mb-3">
  <label for="descripcion" class="form-label"
    >Descripci�n</label
  >
  <textarea
    type="text"
    class="form-control"
    id="descripcion"
    placeholder="Escribir una breve descripci�n de la promo..."
    name="descripcion"
    minlength="50"
    required
  ></textarea>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor rellen� el campo con una descripci�n acerca de
    la promoci�n. La descripci�n debe contener al menos 50
    caracteres.
  </div>
</div>
<div class="mb-3">
  <label for="tipo" class="form-label"
    >Tipo seg�n atracciones que incluye</label
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
    <option value="1">Degustaci�n</option>
    <option value="2">Aventura</option>
    <option value="3">Paisaje</option>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor elig� una de las opciones. Ejemplo: Aventura
  </div>
</div>
<div class="mb-3">
  <label for="clase_de_promo" class="form-label"
    >Clase de promoci�n</label
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
    Por favor elig� una de las opciones. Ejemplo: PromoAxB
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
    Por favor elig� al menos una atracci�n o m�s de la lista.
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
    Por favor sub� una imagen que mejor describa la promoci�n.
  </div>
</div>