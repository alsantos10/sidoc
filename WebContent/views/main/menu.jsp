<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/sistema?c=Categoria" var="menuCateg" />
<c:url value="/sistema?c=Departamento" var="menuDep" />
<c:url value="/sistema?c=Cargo" var="menuCargo" />
<c:url value="/sistema?c=Funcionario" var="menuFunc" />
<c:url value="/sistema?c=Documento" var="menuDoc" />
<c:url value="/sistema?c=Arquivo" var="menuArq" />
<c:url value="/sistema?c=Relatorio" var="menuRel" />
<c:url value="/sistema?c=Painel&acao=logout" var="menuSair" />
<c:url value="/" var="urlHome" />
<!-- MENU Painel -->
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top headroom">
	<div class="container">
		<div class="navbar-header">
			<!-- Button for smallest screens -->
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${urlHome}"> <img
				src="assets/images/logo_sidoc.png" alt="SIDOC"></a>
		</div>

		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav pull-right">
				<c:if test="${empty usuarioLogado}">
					<li class="active"><a href="${urlHome}">Home</a></li>
				</c:if>
				<c:if test="${not empty usuarioLogado}">
					<li class="active"><a href="${urlHome}sistema?c=Painel">Painel
							de Controle</a></li>
				</c:if>
				<li><a href="${urlHome}sistema?c=Contato">Contato</a></li>
				
				<c:if test="${not empty usuarioLogado}">
					<c:url
						value="/sistema?c=Funcionario&acao=editar&id=${usuarioLogado.id}"
						var="menuDados" />
					<c:url
						value="/sistema?c=Funcionario&acao=senha&id=${usuarioLogado.id}"
						var="menuSenha" />
					<li class="dropdown"><a href="#" class="btn dropdown-toggle"
						data-toggle="dropdown">Ferramentas <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${menuDep}">Departamentos</a></li>
							<li class="active"><a href="${menuCateg}">Categorias</a></li>
							<li><a href="${menuCargo}">Cargos</a></li>
							<li><a href="${menuFunc}">Funcionários</a></li>
							<li><a href="${menuDoc}">Documentos</a></li>
							<li><a href="${menuArq}">Arquivos</a></li>
							<li><a href="${menuRel}">Relatórios</a></li>
							<li><a href="${menuDados}">Meus Dados</a></li>
							<li><a href="${menuSenha}">Alterar Senha</a></li>
							<li><a href="${menuSair}">Sair</a></li>
						</ul></li>
				</c:if>
				<c:if test="${empty usuarioLogado}">
					<li><a class="btn" href="${urlHome}sistema?c=Home&acao=login">LOGIN</a></li>
				</c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
<!-- /.navbar -->