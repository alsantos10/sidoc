<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<c:url value="/" var="urlHome" />

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <c:import url="../main/head_meta.jsp" />
        <c:url value="${link_acao}" var="linkNovo" />
        <c:url value="/" var="urlHome" />
        <c:set var="titulo" value="Relatório" />
        <title>SIDOC | ${titulo}</title>
    </head>

    <body class="home">

        <c:import url="../main/menu.jsp" />
        <header id="head" class="secondary"></header>

        <!-- container -->
        <div class="container">

            <ol class="breadcrumb">
                <li><a href="${urlHome}sistema?c=Painel">Painel de Controle</a></li>
                <li class="active">Relatório</li>
            </ol>


            <div class="row">
                <!-- Article main content -->
                <article class="col-xs-12 maincontent">
                    <header class="page-header">
                        <h1 class="page-title">Relatório de documentos <small class="pull-right"> 
                                <button type="button" onclick="window.history.back();" value="Voltar" class="btn btn-primary">Voltar</button>
                            </small></h1>
                    </header>

                    <c:import url="../main/messages.jsp" />

                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${usuario_usuariotipo ==  'funcionario'}">
                                <p class="text-danger"><i class="fa fa-exclamation-triangle"></i> Usuário sem permissão de acesso!</p>
                            </c:if>

                            <c:if test="${usuario_usuariotipo ==  'administrador' || usuario_usuariotipo ==  'gerente'}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Documento</th>
                                            <th class="hidden-xs">Categoria</th>
                                            <th>Cadastro</th>
                                            <th>Validade</th>
                                            <th>Departamento</th>
                                            <th class="hidden-xs">Responsável</th>
                                            <th class="hidden-xs">Ativo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="doc" items="${relatorio}">
                                            <tr class="text text-${doc.ativo=='s'?'default':'danger'}">
                                                <td>${doc.titulo}</td>
                                                <td class="hidden-xs">${doc.categoria.categoria}</td>
                                                <td class="hidden-xs"><fmt:formatDate value="${doc.dtCadastro.time}" pattern="dd/MM/yyyy" /></td>
                                                <td><fmt:formatDate value="${doc.dtValidade.time}" pattern="dd/MM/yyyy" /></td>
                                                <td>${doc.departamento.departamento}</td>
                                                <td class="hidden-xs">${doc.usuario.nome} ${doc.usuario.sobrenome}</td>
                                                <td class="hidden-xs">${doc.ativo=='s'?'Ativo':'Inativo'}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot class="row">
                                            <h4 class="col-xs-10">Período entre ${dt_inicio} e ${dt_final}</h4>
                                            <div class="col-xs-2 text-right">
                                                <span id="btn-download" class="text-primary"><i class="fa fa-download fa-2x" title="Baixar Arquivo" style="cursor: pointer"></i></span>                              
                                                <span id="btn-print" class="text-primary"><i class="fa fa-print fa-2x" title="Imprimir" style="cursor: pointer"></i></span>
                                            </div>                                   
                                    </tfoot>
                                </table>
                            </c:if>
                        </div>
                    </div>

                </article>
                <!-- /Article -->

            </div>
        </div>
        <!-- ./container -->

        <c:import url="../main/rodape.jsp" />

    </body>
    
    <script type="text/javascript">
        $("#btn-download").on("click", function(){
            var url = "${urlHome}sistema?c=Relatorio&acao=pdf&dt_inicial=${dt_inicio}&dt_final=${dt_final}";
            window.location.href = url;
        });
        $("#btn-print").on("click", function(){
            window.print();
        });
    </script>
    
</html>