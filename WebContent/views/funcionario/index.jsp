<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <c:import url="../main/head_meta.jsp" />
        <c:url value="${link_acao}" var="linkNovo" />
        <c:url value="/" var="urlHome" />
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
                <li class="active">Funcionários</li>
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
                                    <th>Nome</th>
                                    <th>Login</th>
                                    <th>email</th>
                                    <th>Departamento</th>
                                    <th>Ativo</th>
                                    <th>Ação</th>
                                </tr>

                                <jsp:useBean id="dao" class="br.com.sidoc.DAO.UsuarioDAO">
                                    <c:forEach var="funci" items="${dao.lista}">
                                        <c:if test="${usuarioLogado.usuarioTipo != 'administrador' && (usuarioLogado.id != funci.id && (funci.gerente.id>0 && usuarioLogado.id==funci.gerente.id))}">
                                            <tr class="text text-${funci.ativo=='s'?'default':'danger'}">
                                                <td>${funci.nome} ${funci.sobrenome}</td>
                                                <td>${funci.login}</td>
                                                <td>${funci.contato.email}</td>
                                                <td>${funci.departamento.departamento}</td>
                                                <td>${funci.ativo=='s'?'Ativo':'Inativo'}</td>
                                                <td><c:url
                                                        value="/sistema?c=Funcionario&acao=editar&id=${funci.id}"
                                                        var="linkAlterar" /> <c:url
                                                        value="/sistema?c=Funcionario&acao=resetar&id=${funci.id}"
                                                        var="linkSenha" /> <c:url
                                                        value="/sistema?c=Funcionario&acao=excluir&id=${funci.id}"
                                                        var="linkExcluir" /> <a href="${linkSenha}"><i
                                                            class="fa fa-unlock-alt fa-2x text-success"></i></a> <a
                                                        href="${linkAlterar}"><i
                                                            class="fa fa-pencil fa-2x icon-fixed-width icon-pencil"></i></a> <a href="${linkExcluir}" class="btnExcluir"><i
                                                            class="fa fa-times fa-2x text-danger"></i></a></td>
                                            </tr>
                                        </c:if>
                                            
                                        <c:if test="${usuarioLogado.usuarioTipo == 'administrador' && usuarioLogado.id != funci.id}">
                                            <tr class="text text-${funci.ativo=='s'?'default':'danger'}">
                                                <td>${funci.nome} ${funci.sobrenome}</td>
                                                <td>${funci.login}</td>
                                                <td>${funci.contato.email}</td>
                                                <td>${funci.departamento.departamento}</td>
                                                <td>${funci.ativo=='s'?'Ativo':'Inativo'}</td>
                                                <td><c:url
                                                        value="/sistema?c=Funcionario&acao=editar&id=${funci.id}"
                                                        var="linkAlterar" /> <c:url
                                                        value="/sistema?c=Funcionario&acao=resetar&id=${funci.id}"
                                                        var="linkSenha" /> <c:url
                                                        value="/sistema?c=Funcionario&acao=excluir&id=${funci.id}"
                                                        var="linkExcluir" /> <a href="${linkSenha}"><i
                                                            class="fa fa-unlock-alt fa-2x text-warning"></i></a> <a
                                                        href="${linkAlterar}"><i
                                                            class="fa fa-pencil fa-2x icon-fixed-width icon-pencil"></i></a> <a href="${linkExcluir}" class="btnExcluir"><i
                                                            class="fa fa-times fa-2x text-danger"></i></a></td>
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

