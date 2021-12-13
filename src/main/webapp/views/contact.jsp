<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
<title>Contacto</title>
</head>
<body>
	<header>
		<jsp:include page="/partials/navbar2.jsp"></jsp:include>
	</header>
	<main class="content-wrapper py-5">
        <div class="container">
            <h3 class="mt-5 fw-bold ps-4">Información</h3>
            <h2 class="subtitulo mb-4 ps-5">Contacto</h2>
            <p class="fs-3 ps-2 my-0 fw-normal">
                Dejanos tu consulta! Nuestro equipo te responderá a la brevedad.
            </p>
            <div class="ps-5 col-12 mb-5 col-md-7">
                <label for="exampleInputEmail1" class="form-label">E-mail*</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                <div id="emailHelp" class="form-text">Responderemos a esta dirección</div>
                <label for="exampleFormControlTextarea1" class="form-label">Tu consulta*</label>
                <textarea class="form-control rounded-0" id="exampleFormControlTextarea1" rows="10"></textarea>
                <button type="submit" class="btn btn-primary mt-2 mb-5">Enviar</button>

            </div>
            <article>
                <div class="row">
                    <div class="col-12 col-md-9">

                        <p class="fs-3 fw-bold mb-0 fw-normal">
                            VisitaTierraMedia
                        </p>
                        <p class="fs-3 my-0 fw-normal">
                            Raghet 523, 53º
                        </p>
                        <p class="fs-3 mt-0 pb-2 fw-normal">
                            Minas Tirith - Gondor
                        <p class="fs-3 mb-0 fw-normal">
                            T 147 0800 111 222 4444
                        </p>
                        <p class="fs-3 fw-normal">
                            info@visitatierramedia.com.tm
                        </p>
                    </div>
                </div>
            </article>
        </div>
    </main>
	<jsp:include page="/partials/footer.jsp"></jsp:include>
</body>
</html>