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
									<i class="fa fa-building-o fa-5"></i>
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
							<p>Visualise os Relatórios do sistema.</p>
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




		<c:if test="${usuario_usuariotipo !=  'funcionario' }">
		<div class="row">
			<div class="col-sm-12">
				<!-- column 2 -->
				<h3>
					<i class="fa fa-list"></i> Relatórios do Sistema
				</h3>
				<hr>
				<!--  tabs -->
				<div class="container">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active"><a href="#profile" data-toggle="tab">Funcionários</a></li>
						<li class=""><a href="#messages" data-toggle="tab">Documentos</a></li>
						<li class=""><a href="#settings" data-toggle="tab">Arquivos</a></li>
					</ul>

					<div class="tab-content">
						<div class="tab-pane active" id="profile">
							<h4>
								<i class="fa fa-user"></i>
							</h4>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Visits</th>
										<th>ROI</th>
										<th>Source</th>
										<th>Description and Notes</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>45</td>
										<td>2.45%</td>
										<td>Direct</td>
										<td>Sam sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Aliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>289</td>
										<td>56.2%</td>
										<td>Referral</td>
										<td>After RWD massa, aliquam in cursus ut, ullamcorper in
											tortor. Aliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>98</td>
										<td>25%</td>
										<td>Type</td>
										<td>Wil sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Liquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>109</td>
										<td>8%</td>
										<td>..</td>
										<td>Forfoot aliquam in cursus ut, ullamcorper in tortor.
											Okma mauris arcu, tristique a lobortis vitae, condimentum
											feugiat justo.</td>
									</tr>
									<tr>
										<td>34</td>
										<td>14%</td>
										<td>..</td>
										<td>Mikel sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Maliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="messages">
							<h4>
								<i class="fa fa-comment"></i>
							</h4>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Visits</th>
										<th>ROI</th>
										<th>Source</th>
										<th>Description and Notes</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>45</td>
										<td>2.45%</td>
										<td>Direct</td>
										<td>Sam sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Aliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>289</td>
										<td>56.2%</td>
										<td>Referral</td>
										<td>After RWD massa, aliquam in cursus ut, ullamcorper in
											tortor. Aliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>98</td>
										<td>25%</td>
										<td>Type</td>
										<td>Wil sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Liquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
									<tr>
										<td>109</td>
										<td>8%</td>
										<td>..</td>
										<td>Forfoot aliquam in cursus ut, ullamcorper in tortor.
											Okma mauris arcu, tristique a lobortis vitae, condimentum
											feugiat justo.</td>
									</tr>
									<tr>
										<td>34</td>
										<td>14%</td>
										<td>..</td>
										<td>Mikel sapien massa, aliquam in cursus ut, ullamcorper
											in tortor. Maliquam mauris arcu, tristique a lobortis vitae,
											condimentum feugiat justo.</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="settings">
							<h4>
								<i class="fa fa-cog"></i>
							</h4>
							Lorem settings dolor sit amet, consectetur adipiscing elit. Duis
							pharetra varius quam sit amet vulputate.
							<p>Quisque mauris augue, molestie tincidunt condimentum
								vitae, gravida a libero. Aenean sit amet felis dolor, in
								sagittis nisi. Sed ac orci quis tortor imperdiet venenatis. Duis
								elementum auctor accumsan. Aliquam in felis sit amet augue.</p>
						</div>
					</div>
				</div>
				<!--  ./tabs -->
			</div>
		</div>
		</c:if>
	</div>
</div>
<!-- /Highlights -->