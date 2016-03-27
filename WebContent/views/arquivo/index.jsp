<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<c:url value="/" var="urlHome" />
<c:url value="assets/uploads/" var="dir_images" />

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
			<li class="active">${titulo}s</li>
		</ol>

		<div class="row">
			<!-- Article main content -->
			<article class="col-md-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">
						Gerenciar ${titulo}s <small class="pull-right"> <a
							class="btn btn-primary" href="${linkNovo}">Novo ${titulo}</a>
						</small>
					</h1>
				</header>

				<c:import url="../main/messages.jsp" />

				<div class="row">


					<div class="col-md-12">
						<table id="table-${titulo}" class="table table-striped display">
							<tr>
								<th>Arquivo</th>
								<th>Título</th>
								<th>Documento</th>
								<th>Cadastro</th>
								<th>Ação</th>
							</tr>

							<jsp:useBean id="dao" class="br.com.sidoc.DAO.ArquivoDAO">
								<c:forEach var="file" items="${dao.lista}">
								    <c:if test="${usuario_usuariotipo ==  'funcionario' && usuario_id == file.usuario.id}">
								        <tr>
	                                        <td><img alt="${file.titulo}" src="${dir_images}${file.arquivo}" style="max-width: 100px; max-height: 60px; text-align: center;" />
	                                        </td>
	                                        <td>${file.titulo}</td>
	                                        <td>${file.documento.titulo}</td>
	                                        <td><fmt:formatDate value="${file.dtCadastro.time}" pattern="dd/MM/yyyy" /></td>
	                                        <td><c:url value="/sistema?c=Arquivo&acao=exibir&id=${file.id}" var="linkAlterar" /> 
                                                <c:url value="/sistema?c=Arquivo&acao=excluir&id=${file.id}" var="linkExcluir" /> 
                                                <a href="${linkAlterar}"><i class="fa fa-pencil-square-o fa-lg"></i></a> <a
	                                            href="${linkExcluir}"><i class="fa fa-minus-square-o fa-lg "></i></a></td>
	                                    </tr>								    
								    </c:if>
								
								    <c:if test="${usuario_usuariotipo == 'gerente' && departamento.id == file.usuario.departamento.id}">
								        <tr>
	                                        <td><img alt="${file.titulo}" src="${dir_images}${file.arquivo}" style="max-width: 100px; max-height: 60px; text-align: center;" />
	                                        </td>
	                                        <td>${file.titulo}</td>
	                                        <td>${file.documento.titulo}</td>
	                                        <td><fmt:formatDate value="${file.dtCadastro.time}" pattern="dd/MM/yyyy" /></td>
	                                        <td><c:url
	                                                value="/sistema?c=Arquivo&acao=exibir&id=${file.id}"
	                                                var="linkAlterar" /> <c:url value="/sistema?c=Arquivo&acao=excluir&id=${file.id}"
	                                                var="linkExcluir" /> <a href="${linkAlterar}"><i class="fa fa-pencil-square-o fa-lg"></i></a> 
	                                                <a href="${linkExcluir}"><i class="fa fa-minus-square-o fa-lg "></i></a></td>
	                                    </tr>
								    </c:if>
								    
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

