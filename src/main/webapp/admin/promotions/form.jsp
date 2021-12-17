<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <div class="mb-3">
    <div class="col-lg-3">
      <label for="nombre"
        class='form-label ${promocion.errors.get("nombre") != null ? "is-invalid" : "" } '>Nombre</label>
      <input type="text" class="form-control" id="nombre" placeholder="Nombre" name="nombre" minlength="3"
        maxlength="250" required />
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      El campo nombre debe contener al menos 3 caracteres. Ejemplo: Río
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-5">
      <label for="descripcion"
        class='form-label ${promocion.errors.get("descripcion") != null ? "is-invalid" : "" }'>Descripción</label>
      <textarea type="text" class="form-control" id="descripcion"
        placeholder="Escribir una breve descripción de la promo..." name="descripcion" minlength="50"
        required></textarea>
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      El campo descripción debe contener entre 50 caracteres como mínimo y 450 como máximo.
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-2">
      <label for="tipo" class='form-label ${promocion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo según
        atracciones que incluye</label>
      <select class="form-select" aria-label="select tipo" id="tipo" name="tipo" required>
        <option value="" selected disabled>
          -- Seleccionar --
        </option>
        <c:forEach items="${tipos}" var="tipo">
          <option value="${tipo.nombre}">
            <c:out value="${tipo.nombre}"></c:out>
          </option>
        </c:forEach>
      </select>
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor eligí una de las opciones. Ejemplo: Aventura
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-2">
      <label for="clase_de_promo" class='form-label ${promocion.errors.get("clase") != null ? "is-invalid" : "" }'>Clase
        de promoción</label>
      <select class="form-select" aria-label="select clase_de_promo" id="clase_de_promo" name="clase_de_promo"
        onchange="update()" required>
        <option value="" selected disabled>
          -- Seleccionar --
        </option>
        <option value="PromoAxB">PromoAxB</option>
        <option value="Promo_Absoluta">Promo_Absoluta</option>
        <option value="Promo_Porcentual">Promo_Porcentual</option>
      </select>
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor eligí una de las opciones. Ejemplo: PromoAxB
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-3">
      <label for="lista_de_atracciones"
        class='form-label ${promocion.errors.get("atracciones") != null ? "is-invalid" : "" }'>Atracciones que incluye
      </label>
      <select class="form-select" multiple aria-label="lista de atracciones" id="lista_de_atracciones"
        name="lista_de_atracciones" required>
        <option value="" disabled>
          -- Seleccionar atracciones --
        </option>
        <c:forEach items="${atracciones}" var="atraccion">
          <option value="${atraccion.nombre}">
            <c:out value="${atraccion.nombre} - ${atraccion.tipo.nombre}"></c:out>
          </option>
        </c:forEach>
      </select>
    </div>
    <small class="text-success">Podés incluir más de una atracción*</small>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor eligí al menos una atracción o más de la lista.
    </div>
  </div>
  <div class="mb-3 costo_absoluto">
    <div class="col-lg-1">
      <label for="costo_absoluto"
        class='form-label ${promocion.errors.get("costoDePromo") != null ? "is-invalid" : "" }'>Costo Reducido</label>
      <input class="form-control" type="number" min="5" id="costo_absoluto" name="costo_absoluto"
        placeholder="Costo reducido" />
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor, ingresá un monto válido. El mínimo es 5.
    </div>
  </div>
  <div class="mb-3 atraccion_gratis">
    <div class="col-lg-3">
      <label for="atraccion_gratis"
        class='form-label ${promocion.errors.get("atraccionGratis") != null ? "is-invalid" : "" }'>Atracción
        Gratis</label>
      <select class="form-select" aria-label="atraccion gratis" id="atraccion_gratis" name="atraccion_gratis">
        <option value="">
          -- Seleccionar atracción gratis --
        </option>
        <c:forEach items="${atracciones}" var="atraccion">
          <option value="${atraccion.nombre}">
            <c:out value="${atraccion.nombre} - ${atraccion.tipo.nombre}"></c:out>
          </option>
        </c:forEach>
      </select>
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor, seleccioná la atracción sin cargo.
    </div>
  </div>
  <div class="mb-3 porcentaje_descuento">
    <div class="col-lg-1">
      <label for="porcentaje_descuento"
        class='form-label ${promocion.errors.get("porcentajeDescuento") != null ? "is-invalid" : "" }'>Porcentaje de
        descuento</label>
      <input class="form-control" type="number" id="porcentaje_descuento" min="1" max="60" name="porcentaje_descuento"
        placeholder="Porcentaje de descuento" />
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor, ingresá un monto de descuento asociado a la promo. El límite es 60.
    </div>
  </div>