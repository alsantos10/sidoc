<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:url value="/" var="urlHome" />
<c:set var="titulo" value="Categoria" />
<title>SIDOC | ${titulo}</title>
</head>

<body>
	<c:import url="../main/menu.jsp" />
	<header id="head" class="secondary"></header>
	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="${urlHome}sistema?c=Painel">Painel de Controle</a></li>
			<li class="active">${titulo}s</li>
		</ol>

		<div class="row">
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Gerenciar ${titulo}s</h1>
				</header>

				<c:if test="${not empty mensagem}">
					<div class="alert alert-success" role="alert">
						<span>${mensagem}</span>
					</div>
				</c:if>

				<div class="row">
					<div class="col-md-12">
						<form action="${linkNovo}" method="post">
							<fieldset class="form-group">
								<legend>Adicionar ${titulo}</legend>

								<div class="form-group">
									<label class="control-label">Categoria: </label> <input
										class="form-control" type="text" name="categoria"
										value="${categ.categoria}" required />
								</div>
								<div class="form-group">
									<input type="hidden" name="acao" value="editar" /> <input
										class="btn btn-primary" type="submit" value="Gravar" /> <a
										href="${urlHome}sistema?c=Categoria" class="btn btn-default">Limpar</a>
								</div>
							</fieldset>
						</form>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<tr>
								<th>Categoria</th>
								<th>Ação</th>
							</tr>

							<jsp:useBean id="dao" class="br.com.sidoc.DAO.CategoriaDAO">
								<c:forEach var="dp" items="${dao.lista}">
									<tr>
										<td>${dp.categoria}</td>
										<td><c:url
												value="/sistema?c=Categoria&acao=exibir&id=${dp.id}"
												var="linkAlterar" /> <c:url
												value="/sistema?c=Categoria&acao=excluir&id=${dp.id}"
												var="linkExcluir" /> <a href="${linkAlterar}"><i
												class="fa fa-pencil-square-o fa-lg"></i></a> <a
											href="${linkExcluir}"><i
												class="fa fa-minus-square-o fa-lg "></i></a></td>
									</tr>
								</c:forEach>
							</jsp:useBean>

						</table>
					</div>
				</div>
			</article>
		</div>
	</div>
	<!-- /container -->

	<c:import url="../main/rodape.jsp" />

</body>
</html>


