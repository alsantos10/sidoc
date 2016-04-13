<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<c:url value="/" var="urlHome" />
<c:url value="/assets/uploads/" var="dir_images" />

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:set var="titulo" value="Arquivo" />
<title>SIDOC | ${titulo}</title>
</head>

<body>
	<c:import url="../main/menu.jsp" />
	<header id="head" class="secondary"></header>
	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="${urlHome}sistema?c=Painel">Painel de Controle</a></li>
			<li><a href="${urlHome}sistema?c=${titulo}">${titulo}s</a></li>
			<li class="active">Cadastrar ${titulo}</li>
		</ol>

		<div class="row">
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Cadastrar ${titulo}</h1>
				</header>

				<c:import url="../main/messages.jsp" />

               <div class="row">
					<div class="col-md-12">
						<form action="${linkNovo}" method="post"
							enctype="multipart/form-data">

							<fieldset class="form-group">
								<div class="clearfix">
									<c:if test="${not empty file.arquivo}">
										<span style="float: left; margin-right: 10px;"> <img
											class="img-responsive" alt="${file.titulo}"
											src="${dir_images}${file.arquivo}"
											style="max-width: 200px; max-height: 170px; text-align: center;" />
										</span>
									</c:if>
								</div>

								<cF:cFormBasic id="titulo" valor="${file.titulo}"
									rotulo="Título do arquivo" type="text" classe="form-control" outro="required" />
								
								<c:if test="${usuario_usuariotipo == 'funcionario'}">
                                                                    <cF:cFormHidden id="id_usuario" valor="${usuario_id}" type="hidden" />
                                                                </c:if>
                                                                <c:if test="${usuario_usuariotipo != 'funcionario'}">
									<cF:cSelectUsu id="id_usuario" valor="${file.usuario.id}"
										rotulo="Funcionário Responsável" classe="form-control" outro="required" />
								</c:if>
									
								<cF:cSelectDoc id="id_documento" valor="${file.documento.id}"
									rotulo="Documento pertencente" classe="form-control" outro="required" />

								<c:if test="${empty file.arquivo}">
									<div class="form-group">
										<label class="control-label">Selecione um arquivo: </label> <input
											class="btn btn-file" type="file" name="file" />
									</div>
								</c:if>

								<div class="form-group">
									<input class="btn btn-primary" type="submit" value="Enviar" />
									<a href="${urlHome}sistema?c=Arquivo" class="btn btn-default">Voltar</a>
								</div>
							</fieldset>

						</form>
					</div>
				</div>
			</article>
		</div>
	</div>
	<!-- /container -->

	<c:import url="../main/rodape.jsp" />

</body>
</html>


