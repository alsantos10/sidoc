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
<c:set var="titulo" value="Relatório" />
<title>SIDOC | ${titulo}</title>
</head>

<body class="home">

	<c:import url="../main/menu.jsp" />
	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="${urlHome}sistema?c=Painel">Painel de Controle</a></li>
			<li class="active">Relatório</li>
		</ol>


		<div class="row">
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Tela de ${titulo}</h1>
				</header>

				<c:import url="../main/messages.jsp" />

				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Emitir Relatório</h3>
							<p class="text-center text-muted">
								Informe a data inicial e final para gerar o relatório
							</p>
							<hr>

							<form action="${linkNovo}" method="post" id="form-login">
								<cF:campoData id="dt_inicial" valor="${usuario}"
									rotulo="Data inicial" type="text" classe="form-control"
									outro="required" />
								<cF:campoData id="dt_final" valor="${senha}" rotulo="Senha"
									type="text" classe="form-control" outro="required" />
								<hr>

								<div class="row">
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">Gerar</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>

			</article>
			<!-- /Article -->

		</div>
	</div>
	<!-- ./container -->

	<c:import url="../main/rodape.jsp" />

</body>
</html>