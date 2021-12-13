let nav = document.querySelector('.navbar-transp');
let padre = document.querySelector('.navbar-brand');
let imagen = document.querySelector('.logo');
let logoSign = document.querySelector('.logoInicio');
/* let txt = document.querySelector('.navbar-nav>li>a') */

document.addEventListener('scroll', function (e) {
  if (window.scrollY > 30) {
    nav.classList.add('navbar-color');
    nav.classList.remove('navbar-transp');

    padre.classList.add('navbar-brand-scrolled');
    padre.classList.remove('navbar-brand');

    imagen.src = '/visitatierramedia/assets/img/logos/logoVTM.png';

    logoSign.src = '/visitatierramedia/assets/img/icons/logIco.png';
  } else {
    nav.classList.add('navbar-transp');
    nav.classList.remove('navbar-color');

    padre.classList.add('navbar-brand');
    padre.classList.remove('navbar-brand-scrolled');

    imagen.src = '/visitatierramedia/assets/img/logos/logo-blanco-oro.png';

    logoSign.src = '/visitatierramedia/assets/img/icons/logIcoWhite.png';
  }
});