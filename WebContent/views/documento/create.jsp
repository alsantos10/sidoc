<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="urlHome" />
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.text.SimpleDateFormat, java.util.Date"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:set var="titulo" value="Documento" />
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
			<li class="active">Cadastro</li>
		</ol>

		<div class="row">
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				<header class="page-header">

					<h1 class="page-title">
						Cadastro de ${titulo}
						<c:url var="linkListaDoc" value="sistema?c=Documento" />

						<small class="pull-right"> <a class="btn btn-primary"
							href="${linkListaDoc}">Listar documentos</a>
						</small>
					</h1>
				</header>

				<c:import url="../main/messages.jsp" />

				<div class="row">
					<div class="col-sm-12">
						<form action="${linkNovo}" method="post">
							<fieldset class="form-group">
								<legend>Cadastro de Documento</legend>
								<div class="row">
									<div class="col-sm-6">
										<cF:cFormBasic id="titulo" valor="${doc.titulo}"
											rotulo="Título" type="text" classe="form-control"
											outro="required" />
									</div>
									<div class="col-sm-6">
										<cF:cFormBasic id="ref_doc_fisico" valor="${doc.refDocFisico}"
											rotulo="Referência ao Documento Físico" type="text"
											classe="form-control" outro="" />
									</div>
								</div>

								<div class="row">

									<c:if test="${not empty doc.dtCadastro}">
										<div class="col-sm-3">
											<cF:campoDataDoc id="dt_cadastro" valor="cad"
												rotulo="Data de Cadastro" type="text" classe="form-control"
												outro="disabled='disabled'" />
										</div>
									</c:if>
									<div class="col-sm-${doc.dtCadastro.time!=null?'3':'6'}">
										<cF:campoDataDoc id="dt_validade" valor="val"
											rotulo="Data de Validade" type="text" classe="form-control" outro="required='true'" />
									</div>
									<div class="col-sm-6">
										<cF:cSelectCat id="id_categoria" valor="${doc.categoria.id}"
											rotulo="Categoria" classe="form-control" outro="required" />
									</div>
								</div>
                                
                                
								<c:if test="${usuario_usuariotipo == 'funcionario'}">
									<cF:cFormHidden id="id_usuario" valor="${usuario_id}" type="hidden" />
									<cF:cFormHidden id="id_departamento" valor="${usuario_departamento.id}" type="hidden" />
								</c:if>
								<c:if test="${usuario_usuariotipo != 'funcionario'}">
									<div class="row">
									    <div class="col-sm-6">
	                                        <cF:cSelectUsu id="id_usuario" valor="${doc.usuario.id}" rotulo="Responsável" classe="form-control" outro="required" />
									    </div>
									    <div class="col-sm-6">
										   <cF:cSelectDepto id="id_departamento" valor="${doc.departamento.id}" rotulo="Departamento" classe="form-control" outro="required" />
										</div>
									</div>
								</c:if>

								<div class="row">
									<div class="col-sm-12">
										<cF:cFormTextarea id="descricao" valor="${doc.descricao}"
											rotulo="Descrição do Documento" classe="form-control" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<cF:cSelectSouN id="ativo"
											valor="${doc.ativo=='s'?doc.ativo:'n'}"
											rotulo="Status do Documento" classe="form-control" />
									</div>
								</div>
							</fieldset>

							<div class="form-group">
								<input class="btn btn-primary" type="submit" value="Gravar" />
								<a href="${urlHome}sistema?c=Documento" class="btn btn-default">Voltar</a>
							</div>
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




