<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mb-3">
    <label for="nombre" class='form-label ${atraccion.errors.get("nombre") != null ? "is-invalid" : "" }'>Nombre</label>
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
    <label for="descripcion" class='form-label ${atraccion.errors.get("descripcion") != null ? "is-invalid" : "" }'
      >Descripci�n</label
    >
    <textarea
      type="text"
      class="form-control"
      id="descripcion"
      placeholder="Escribir una breve descripci�n acerca de la atracci�n..."
      name="descripcion"
      minlength="50"
      maxlength="450"
      required
    ></textarea>
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      El campo descripci�n debe contener entre 50 caracteres como m�nimo y 450 como m�ximo..
    </div>
  </div>
  <div class="mb-3">
    <label for="costo" class='form-label ${atraccion.errors.get("costoDeVisita") != null ? "is-invalid" : "" }'>Costo</label>
    <input
      type="number"
      class="form-control"
      id="costo"
      placeholder="Costo. Ejemplo: 15"
      name="costo"
      min="3"
      required
    />
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor, ingres� un monto v�lido. El costo m�nimo es 3.
    </div>
  </div>
  <div class="mb-3">
    <label for="tiempo" class='form-label ${atraccion.errors.get("tiempoDeVisita") != null ? "is-invalid" : "" }'
      >Tiempo de visita</label
    >
    <input
      type="text"
      inputmode="decimal"
      class="form-control"
      id="tiempo"
      placeholder="Tiempo de visita. Ejemplo: 1.5"
      name="tiempo"
      min="0.5"
      required
    />
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor, ingres� un numero v�lido. El
      tiempo m�nimo es 0.5 hs.
    </div>
  </div>
  <div class="mb-3">
    <label for="cupo" class='form-label ${atraccion.errors.get("cupoDePersonas") != null ? "is-invalid" : "" }'>Cupo</label>
    <input
      type="number"
      class="form-control"
      id="cupo"
      placeholder="Cupo de visita. Ejemplo: 4"
      name="cupo"
      min="1"
      required
    />
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      Por favor ingres� un numero v�lido. Ejemplo: 4
    </div>
  </div>
  <div class="mb-3">
    <label for="tipo_atraccion" class='form-label ${atraccion.errors.get("tipo") != null ? "is-invalid" : "" }'
      >Tipo de atracci�n</label
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
    <label for="imagen_atraccion" class='form-label ${atraccion.errors.get("imagen") != null ? "is-invalid" : "" }'
      >Imagen</label
    >
    <input
      class="form-control"
      type="url"
      id="imagen_atraccion"
      name="imagen"
      placeholder="imagen URI"
      required
    />
    <div class="valid-feedback">ok!</div>
    <div class="invalid-feedback">
      La imagen debe ser una url v�lida.
    </div>
  </div>
  