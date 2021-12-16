<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
  <label for="nombre" class='form-label ${promocion.errors.get("nombre") != null ? "is-invalid" : "" } '>Nombre</label>
  <input
    type="text"
    class="form-control"
    id="nombre"
    placeholder="Nombre"
    name="nombre"
    minlength="3"
    maxlength="250"
    value="${promocion.nombre}"
    required
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    El campo nombre debe contener al menos 3 caracteres. Ejemplo: Río
  </div>
</div>
<div class="mb-3">
  <label for="descripcion" class='form-label ${promocion.errors.get("descripcion") != null ? "is-invalid" : "" }'
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
  >${promocion.descripcion}</textarea>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    El campo descripción debe contener entre 50 caracteres como mínimo y 450 como máximo.
  </div>
</div>
<div class="mb-3">
  <label for="tipo" class='form-label ${promocion.errors.get("tipo") != null ? "is-invalid" : "" }'
    >Tipo según atracciones que incluye</label
  >
  <select
    class="form-select"
    aria-label="select tipo"
    id="tipo"
    name="tipo"
    required
  >
    <c:forEach items="${tipos}" var="tipo">
     <c:choose>
     	<c:when test="${promocion.tipo.nombre.equals(tipo.nombre)}">
     		<option value="${tipo.nombre}" selected><c:out value="${promocion.tipo.nombre}"></c:out></option>
     	</c:when>
     	<c:otherwise>
     		<option value="${tipo.nombre}"><c:out value="${tipo.nombre}"></c:out></option>
     	</c:otherwise>
      </c:choose>		
  </c:forEach>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí una de las opciones. Ejemplo: Aventura
  </div>
</div>
<div class="mb-3">
  <label for="clase_de_promo" class='form-label ${promocion.errors.get("clase") != null ? "is-invalid" : "" }'
    >Clase de promoción</label
  >
  <select
    class="form-select"
    aria-label="select clase_de_promo"
    id="clase_de_promo"
    name="clase_de_promo"
    required
  >
  	<c:if test="${promocion.clase == 'PROMOAXB'}">  
  		<option value="PromoAxB" selected>PromoAxB</option>
	    <option value="Promo_Absoluta">Promo_Absoluta</option>
	    <option value="Promo_Porcentual">Promo_Porcentual</option>
  	</c:if>
  	<c:if test="${promocion.clase == 'PROMO_ABSOLUTA'}">  
  		<option value="PromoAxB">PromoAxB</option>
	    <option value="Promo_Absoluta" selected>Promo_Absoluta</option>
	    <option value="Promo_Porcentual"><c:out value="${promocion.clase}"></c:out></option>
  	</c:if>
  	<c:if test="${promocion.clase == 'PROMO_PORCENTUAL'}">  
  		<option value="PromoAxB">PromoAxB</option>
	    <option value="Promo_Absoluta">Promo_Absoluta</option>
	    <option value="Promo_Porcentual" selected>Promo_Porcentual</option>
  	</c:if>

  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí una de las opciones. Ejemplo: PromoAxB
  </div>
</div>

<div class="mb-3">
  <label for="lista_de_atracciones" class='form-label ${promocion.errors.get("atracciones") != null ? "is-invalid" : "" }'
    >Atracciones que incluye </label
  >
  <select
    class="form-select"
    multiple
    aria-label="lista de atracciones"
    id="lista_de_atracciones"
    name="lista_de_atracciones"
    required
  >
    
    <c:forEach items="${atracciones}" var="atraccion">  
      <c:choose>
      <c:when test="${promocion.atracciones.contains(atraccion)}">
      	<option value="${atraccion.nombre}" selected><c:out value="${atraccion.nombre}"></c:out></option>
      </c:when>
      <c:otherwise>
      	<option value="${atraccion.nombre}"><c:out value="${atraccion.nombre}"></c:out></option> 
      </c:otherwise>
      </c:choose>
    </c:forEach>
  </select>
  <small class="text-success">Podés incluir más de una atracción*</small>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor eligí al menos una atracción o más de la lista.
  </div>
</div>

<c:if test="${promocion.clase == 'PROMO_ABSOLUTA'}">  
<div class="mb-3">
  <label for="costo_absoluto" class='form-label ${promocion.errors.get("costoDePromo") != null ? "is-invalid" : "" }'>Costo Reducido</label>
  <input
    class="form-control"
    type="number"
    min="5"
    id="costo_absoluto"
    name="costo_absoluto"
    value="${promocion.costoDePromo}"
    placeholder="Costo reducido"
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor, ingresá un monto válido. El mínimo es 5.
  </div>
</div>
</c:if>
<c:if test="${promocion.clase == 'PROMOAXB'}">  
<div class="mb-3">
  <label for="atraccion_gratis" class='form-label ${promocion.errors.get("atraccionGratis") != null ? "is-invalid" : "" }'>Atracción Gratis</label>
  <select
    class="form-select"
    aria-label="atraccion gratis"
    id="atraccion_gratis"
    name="atraccion_gratis"
  >
    <c:forEach items="${atracciones}" var="atraccion">  
      <c:choose>
      <c:when test="${promocion.atraccionGratis.equals(atraccion)}">
      	<option value="${atraccion.nombre}" selected><c:out value="${atraccion.nombre}"></c:out></option>
      </c:when>
      <c:otherwise>
      	<option value="${atraccion.nombre}"><c:out value="${atraccion.nombre}"></c:out></option> 
      </c:otherwise>
      </c:choose>
    </c:forEach>
  </select>
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor, seleccioná la atracción sin cargo.
  </div>
</div>
</c:if>
<c:if test="${promocion.clase == 'PROMO_PORCENTUAL'}">  
<div class="mb-3">
  <label for="porcentaje_descuento" class='form-label ${promocion.errors.get("porcentajeDescuento") != null ? "is-invalid" : "" }'>Porcentaje de descuento</label>
  <input
    class="form-control"
    type="number"
    id="porcentaje_descuento"
    min="1"
    max="60"
    name="porcentaje_descuento"
    placeholder="Porcentaje de descuento"
    value="${promocion.porcentajeDescuento}"
  />
  <div class="valid-feedback">ok!</div>
  <div class="invalid-feedback">
    Por favor, ingresá un monto de descuento asociado a la promo. El límite es 60.
  </div>
</div>
</c:if>