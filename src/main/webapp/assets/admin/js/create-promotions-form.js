
// Lógica para el form de la creación de Promos en Admin

	const descuento = document.querySelector('.porcentaje_descuento');
	const atraccionGratis = document.querySelector('.atraccion_gratis');
	const costoAbsoluto = document.querySelector('.costo_absoluto');
	descuento.style.display = 'none';
	costoAbsoluto.style.display = 'none';
	atraccionGratis.style.display = 'none';
	
	function update() {
	  const select = document.getElementById('clase_de_promo');
	  const option = select.options[select.selectedIndex].text;
	
	  if (option === 'PromoAxB') {
	    atraccionGratis.style.display = 'block';
	    descuento.style.display = 'none';
	    costoAbsoluto.style.display = 'none';
	  }
	  if (option === 'Promo_Absoluta') {
	    costoAbsoluto.style.display = 'block';
	    atraccionGratis.style.display = 'none';
	    descuento.style.display = 'none';
	  }
	  if (option === 'Promo_Porcentual') {
	    descuento.style.display = 'block';
	    costoAbsoluto.style.display = 'none';
	    atraccionGratis.style.display = 'none';
	  }
	}
