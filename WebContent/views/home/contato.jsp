<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<c:url value="/" var="urlHome" />

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:url value="/" var="urlHome" />
<c:set var="titulo" value="Login" />
<title>SIDOC | ${titulo}</title>
</head>

<body class="home">

	<c:import url="../main/menu.jsp" />
	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="${urlHome}">Home</a></li>
			<li class="active">Contato</li>
		</ol>


		<div class="row">
			<!-- Article main content -->
			<article class="col-sm-9 maincontent">
				<header class="page-header">
					<h1 class="page-title">Entre em Contato</h1>
				</header>
				
				<c:import url="../main/messages.jsp" />

				<p>Caso você tenha dúvidas, sugestões ou precise de ajuda para
					utilizar o sistema SIDOC, preencha este formulário que em breve
					entraremos em contato.</p>
				<br>
				<form action="" method="post">
					<div class="row">
						<div class="col-sm-4">
							<cF:cFormBasic id="nome" valor="${nome}" rotulo="Nome"
								type="text" classe="form-control" outro="required" />
						</div>
						<div class="col-sm-4">
							<cF:cFormBasic id="email" valor="${email}" rotulo="E-mail"
								type="text" classe="form-control" outro="required" />

						</div>
						<div class="col-sm-4">
							<cF:cSelectDeptoOff id="id_departamento" valor="${departamento}"
								rotulo="Departamento" classe="form-control" outro="required" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-12">
							<textarea placeholder="Escreva sua mensagem aqui..."
								class="form-control" rows="9"></textarea>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-lg-6 text-right">
							<input class="btn btn-action" type="submit"
								value="Enviar mensagem">
						</div>
					</div>
				</form>

			</article>
			<!-- /Article -->

			<!-- Sidebar -->
			<aside class="col-sm-3 sidebar sidebar-right">

				<div class="widget">
					<h4>Endereço</h4>
					<address>
						Rua Frederico Grotte, 322 <br /> Jardim São Luis, São Paulo/SP
					</address>
					<h4>Telefone:</h4>
					<address>(11) 5851-9315</address>
				</div>

			</aside>
			<!-- /Sidebar -->

		</div>
	</div>
	<!-- ./container -->

	<section class="container-full top-space">
		<div id="map"></div>
	</section>

	<c:import url="../main/rodape.jsp" />


	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	

	<!-- Google Maps -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=&amp;sensor=false&amp;extension=.js"></script>
	<script src="assets/js/google-map.js"></script>



</body>
</html>