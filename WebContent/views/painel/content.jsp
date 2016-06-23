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


<!-- Highlights - jumbotron -->
<div class="jumbotron top-space">
	<div class="container">

		<div class="row">
			<div class="col-sm-12">
				<h3>
					<i class="fa fa-cogs"></i> Painel de Controle
				</h3>
				<hr>
				
				<c:import url="../main/messages.jsp" />

				<!-- opção 1 -->
				<c:if test="${usuario_usuariotipo !=  'funcionario' }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-users fa-5"></i>
								</h4>
							</div>
							<p>Gerencie os usuários que têm acesso ao sistema.</p>
							<a href="${menuFunc}" title="Funcionários"
								class="btn btn-success btn-block">Funcionários</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				</c:if>

				<!-- opção 2 -->
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-paste fa-5"></i>
								</h4>
							</div>
							<p>Gerencie os Documentos do sistema.</p>
							<a href="${menuDoc}" title="Documentos"
								class="btn btn-success btn-block">Documentos</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>

				<!-- opção 3 -->
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-save fa-5"></i>
								</h4>
							</div>
							<p>Gerencie os Arquivos de documentos.</p>
							<a href="${menuArq}" title="Arquivos"
								class="btn btn-success btn-block">Arquivos</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>

				<!-- opção 4 -->
				<c:if test="${usuario_usuariotipo !=  'funcionario' }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-cogs fa-5"></i>
								</h4>
							</div>
							<p>Gerencie os cargos dos empregados cadastrados.</p>
							<a href="${menuCargo}" title="Cargos"
								class="btn btn-success btn-block">Cargos</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				</c:if>


				<!-- opção 5 -->
				<c:if test="${usuario_usuariotipo !=  'funcionario' }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-bars fa-5"></i>
								</h4>
							</div>
							<p>Gerencie as Categorias de documentos.</p>
							<a href="${menuCateg}" title="Categorias"
								class="btn btn-success btn-block">Categorias</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				</c:if>

				<!-- opção 6 -->
				<c:if test="${usuario_usuariotipo !=  'funcionario' }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-sitemap fa-5"></i>
								</h4>
							</div>
							<p>Gerencie os Departamentos dos empregados que têm acesso ao sistema.</p>
							<a href="${menuDep}" title="Departamentos"
								class="btn btn-success btn-block">Departamentos</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				</c:if>

				<!-- opção 7 -->
				<c:if test="${usuario_usuariotipo !=  'funcionario' }">
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="h-caption">
								<h4>
									<i class="fa fa-list-alt fa-5"></i>
								</h4>
							</div>
							<p>Visualize os Relatórios do sistema.</p>
							<a href="${menuRel}" title="Relatórios"
								class="btn btn-action btn-block">Relatórios</a>
						</div>
						<!--/panel-body-->
					</div>
					<!--/panel-->
				</div>
				</c:if>

			</div>
		</div>


	</div>
</div>
<!-- /Highlights -->