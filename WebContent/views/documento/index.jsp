<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:url value="/" var="urlHome" />
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
						<table class="table table-striped">
							<tr>
								<th>Titulo</th>
								<c:if test="${usuario_usuariotipo != 'funcionario'}"><th>Usuario</th></c:if>
								<th>Categoria</th>
								<th>Cadastro</th>
								<th>Validade</th>
								<th>Ativo</th>
								<th>Ação</th>
							</tr>

							<jsp:useBean id="dao" class="br.com.sidoc.DAO.DocumentoDAO">
								<c:forEach var="doc" items="${dao.lista}">
									<c:if test="${usuario_usuariotipo ==  'funcionario' && usuario_id == doc.usuario.id}">
										<tr class="text text-${doc.ativo=='s'?'default':'danger'}">
											<td>${doc.titulo}</td>
											<td>${doc.categoria.categoria}</td>
											<td><fmt:formatDate value="${doc.dtCadastro.time}" pattern="dd/MM/yyyy" /></td>
											<td><fmt:formatDate value="${doc.dtValidade.time}" pattern="dd/MM/yyyy" /></td>
											<td>${doc.ativo=='s'?'Ativo':'Inativo'}</td>
											<td><c:url value="/sistema?c=Documento&acao=editar&id=${doc.id}" var="linkAlterar" /> 
											<c:url value="/sistema?c=Documento&acao=excluir&id=${doc.id}" var="linkExcluir" />
											 <a href="${linkAlterar}"><i class="fa fa-pencil-square-o fa-lg"></i></a> 
											 <a href="${linkExcluir}" class="btnExcluir text text-danger"><i class="fa fa-minus-square-o fa-lg "></i></a></td>
										</tr>
									</c:if>
									
									<c:if test="${usuario_usuariotipo == 'gerente' && departamento.id == doc.departamento.id}">
                                        <tr class="text text-${doc.ativo=='s'?'default':'danger'}">
                                            <td>${doc.titulo}</td>
                                            <td>${doc.usuario.login}</td>
                                            <td>${doc.categoria.categoria}</td>
                                            <td><fmt:formatDate value="${doc.dtCadastro.time}" pattern="dd/MM/yyyy" /></td>
                                            <td><fmt:formatDate value="${doc.dtValidade.time}" pattern="dd/MM/yyyy" /></td>
                                            <td>${doc.ativo=='s'?'Ativo':'Inativo'}</td>
                                            <td><c:url value="/sistema?c=Documento&acao=editar&id=${doc.id}" var="linkAlterar" /> 
                                            <c:url value="/sistema?c=Documento&acao=excluir&id=${doc.id}" var="linkExcluir" />
                                             <a href="${linkAlterar}"><i class="fa fa-pencil-square-o fa-lg"></i></a> 
                                             <a href="${linkExcluir}" class="btnExcluir text text-danger"><i class="fa fa-minus-square-o fa-lg "></i></a></td>
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

