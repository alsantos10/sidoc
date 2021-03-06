<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/" var="urlHome" />
<html lang="pt-BR">
<head>
<c:import url="views/main/head_meta.jsp" />
</head>

<body class="home">
	<c:import url="views/main/menu.jsp" />
	<c:import url="views/main/cabecalho.jsp" />
	<!-- Intro -->
	<div class="container text-center top-space">
		<p class="text-muted">
			Comece agora mesmo a otimizar o processo corporativo de <strong>cadastro
				e armazenamento</strong> de documentos para <strong>melhorar e
				agilizar</strong> o processo de guarda de documentos. <strong>Otimize
				os custos</strong> melhorando o desempenho da organiza��o e diminuindo o <strong>investimento
				de recursos</strong> em manuten��o de espa�o f�sico para arm�rios e pastas
			que armazenam um <strong>volume consider�val e crescente</strong> de
			documentos produzidos pela empresa diariamente.
		</p>
	</div>
	<!-- /Intro-->

	<!-- container -->
	<div class="container">
		<h2 class="text-center ">Vantagens e Benef�cios</h2>

		<div class="row">
			<div class="col-sm-6">
				<h3>Organize seus documentos de forma otimizada</h3>
				<p>O sistema permite controle de acesso a cada documento
					armazenado separado por categoria e departamento, preservando sua
					integridade e confidencialidade.</p>
			</div>
			<div class="col-sm-6">
				<h3>Mantenha um registro dos documentos descartados</h3>
				<p>Diminua drasticamento os custos com espa�o f�sico para a
					guarda de documentos corporativos registrando uma c�pia digital no
					sistema.</p>
			</div>
		</div>
		<!-- /row -->

		<div class="row">
			<div class="col-sm-6">
				<h3>Cadastre seus documentos com agilidade e seguran�a</h3>
				<p>Documentos s�o rapidamente cadastrados pelo usu�rio no
					sistema permite entitular, descrever e colocar uma data de descarte
					que � avisada automaticamente.</p>
			</div>
			<div class="col-sm-6">
				<h3>Preserve a localiza��o de documentos f�sicos</h3>
				<p>Mantenha em seu registro a localiza��o dos documentos
					f�sicos, caso necessite mant�-los, com prazo para descarte.</p>
			</div>
		</div>
		<!-- /row -->

		<div class="jumbotron top-space">
			<h4>Aumente a produtividade de sua empresa utilizando um sistema
				de informatiza��o de documentos.</h4>
			<p class="text-right">
				<a href="${urlHome}sistema?c=Contato" class="btn btn-primary btn-large">Entre em contato</a>
			</p>
		</div>

	</div>
	<!-- /container -->

	<c:import url="views/main/rodape.jsp" />

</body>
</html>