<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:url value="/" var="urlHome" />
<c:set var="titulo" value="Erro" />
<title>SIDOC | ${titulo}</title>
</head>
<body>
	<c:import url="../main/menu.jsp" />
	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="${urlHome}">Home</a></li>
			<li class="active">Erro</li>
		</ol>

		<div class="row">
			<!-- Article main content -->
			<section class="col-md-12 maincontent">

				<h2 class="alert alert-danger" role="alert">
					<span>Ocorreu um erro</span>
				</h2>

			</section>
		</div>
	</div>
	<!-- /container -->

	<c:import url="../main/rodape.jsp" />

</body>
</html>