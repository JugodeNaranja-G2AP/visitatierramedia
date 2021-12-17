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
      El campo nombre debe contener al menos 3 caracteres. Ejemplo: R�o
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-5">
      <label for="descripcion"
        class='form-label ${promocion.errors.get("descripcion") != null ? "is-invalid" : "" }'>Descripci�n</label>
      <textarea type="text" class="form-control" id="descripcion"
        placeholder="Escribir una breve descripci�n de la promo..." name="descripcion" minlength="50"
        required></textarea>
    </div>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      El campo descripci�n debe contener entre 50 caracteres como m�nimo y 450 como m�ximo.
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-2">
      <label for="tipo" class='form-label ${promocion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo seg�n
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
      Por favor elig� una de las opciones. Ejemplo: Aventura
    </div>
  </div>
  <div class="mb-3">
    <div class="col-lg-2">
      <label for="clase_de_promo" class='form-label ${promocion.errors.get("clase") != null ? "is-invalid" : "" }'>Clase
        de promoci�n</label>
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
      Por favor elig� una de las opciones. Ejemplo: PromoAxB
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
    <small class="text-success">Pod�s incluir m�s de una atracci�n*</small>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor elig� al menos una atracci�n o m�s de la lista.
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
      Por favor, ingres� un monto v�lido. El m�nimo es 5.
    </div>
  </div>
  <div class="mb-3 atraccion_gratis">
    <div class="col-lg-3">
      <label for="atraccion_gratis"
        class='form-label ${promocion.errors.get("atraccionGratis") != null ? "is-invalid" : "" }'>Atracci�n
        Gratis</label>
      <select class="form-select" aria-label="atraccion gratis" id="atraccion_gratis" name="atraccion_gratis">
        <option value="">
          -- Seleccionar atracci�n gratis --
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
      Por favor, seleccion� la atracci�n sin cargo.
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
      Por favor, ingres� un monto de descuento asociado a la promo. El l�mite es 60.
    </div>
  </div>