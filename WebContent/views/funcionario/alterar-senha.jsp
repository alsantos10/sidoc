<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/" var="urlHome" />
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <c:import url="../main/head_meta.jsp" />
        <c:url value="${link_acao}" var="linkNovo" />
        <c:set var="titulo" value="Funcionario" />
        <title>SIDOC | ${titulo}</title>
    </head>

    <body>
        <c:import url="../main/menu.jsp" />
        <header id="head" class="secondary"></header>
        <!-- container -->
        <div class="container">

            <ol class="breadcrumb">
                <li><a href="${urlHome}sistema?c=Painel">Painel de Controle</a></li>
                <c:if test="${usuario_usuariotipo != 'funcionario'}">
                <li><a href="${urlHome}sistema?c=${titulo}">Funcionários</a></li>
                </c:if>
                <li class="active">Alterar Senha</li>
            </ol>

            <div class="row">
                <!-- Article main content -->
                <article class="col-md-12 maincontent">
                    <header class="page-header">

                        <h1 class="page-title">
                            Cadastro de ${titulo}
                            <c:url var="linkListaFunc" value="sistema?c=Funcionario" />

                            <c:if test="${usuario_usuariotipo != 'funcionario'}">
                                <small class="pull-right"> 
                                    <a class="btn btn-primary" href="${linkListaFunc}">Listar Funcionarios</a>
                                </small>
                            </c:if>
                        </h1>

                    </header>

                    <c:import url="../main/messages.jsp" />

                    <div class="row">
                        <div class="col-md-12">
                            <form action="${linkNovo}" method="post" id="form-AlterarSenha">
                                <fieldset class="form-group">
                                    <legend>Dados de Acesso</legend>

                                    <div class="row">
                                        <cF:cFormBasic id="senhaAtual" rotulo="Senha Atual" type="text"
                                                       classe="form-control" outro="required" />
                                        <cF:cFormBasic id="senhaNova" rotulo="Nova Senha" type="text"
                                                       classe="form-control" outro="required" />
                                        <cF:cFormBasic id="senhaConf" rotulo="Confirme a Nova Senha"
                                                       type="text" classe="form-control" outro="required" />
                                    </div>
                                </fieldset>

                                <div class="form-group">
                                    <input class="btn btn-primary" type="button"
                                           value="Alterar Senha" onclick="javascript: alterarSenha();" />
                                    <a href="${urlHome}sistema?c=Funcionario"
                                       class="btn btn-default">Voltar</a>
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


