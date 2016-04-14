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
                <li class="active">Cadastro de ${titulo}</li>
            </ol>

            <div class="row">
                <!-- Article main content -->
                <article class="col-md-12 maincontent">
                    <header class="page-header">

                        <h1 class="page-title">
                            Cadastro de ${titulo}
                            <c:url var="linkListaFunc" value="sistema?c=Funcionario" />
                            <c:if test="${usuario_usuariotipo != 'funcionario'}">
                                <small class="pull-right"> <a class="btn btn-primary" href="${linkListaFunc}">Listar Funcionarios</a></small>
                            </c:if>
                        </h1>

                    </header>

                    <c:import url="../main/messages.jsp" />

                    <div class="row">
                        <div class="col-md-12">
                            <form action="${linkNovo}" method="post">
                                <cF:cFormHidden id="id_contato" valor="${func.contato.id}"
                                                type="hidden" />
                                <cF:cFormHidden id="id_endereco" valor="${func.endereco.id}"
                                                type="hidden" />
                                <fieldset class="form-group">
                                    <legend>Dados Pessoais</legend>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <cF:cFormBasic id="nome" valor="${func.nome}" rotulo="Nome"
                                                           type="text" classe="form-control" outro="required" />
                                        </div>
                                        <div class="col-sm-6">
                                            <cF:cFormBasic id="sobrenome" valor="${func.sobrenome}"
                                                           rotulo="Sobrenome" type="text" classe="form-control"
                                                           outro="required" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <cF:cFormBasic id="cpf" valor="${func.cpf}" rotulo="CPF"
                                                           type="text" classe="form-control" outro="required" />
                                        </div>
                                        <div class="col-sm-4">
                                            <cF:cFormBasic id="rg" valor="${func.rg}" rotulo="RG"
                                                           type="text" classe="form-control" />
                                        </div>
                                        <div class="col-sm-4">
                                            <cF:cSelectSexo id="sexo" valor="${func.sexo}" rotulo="Sexo"
                                                            classe="form-control" />
                                        </div>
                                    </div>
                                </fieldset>

                                <fieldset class="form-group">
                                    <legend>Dados Profissionais</legend>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <cF:cSelectCargo id="id_cargo" valor="${func.cargo.id}"
                                                             rotulo="Cargo" classe="form-control" outro="required" />
                                        </div>
                                        <div class="col-sm-4">
                                            <cF:cSelectDepto id="id_departamento"
                                                             valor="${func.departamento.id}" rotulo="Departamento"
                                                             classe="form-control" outro="required" />
                                        </div>
                                        <div class="col-sm-4">
                                            <cF:cSelectGerente id="id_gerente" valor="${func.gerente.id}"
                                                               rotulo="Gerente Responsável" classe="form-control"
                                                               outro="required" />
                                        </div>
                                    </div>
                                </fieldset>

                                <fieldset class="form-group">
                                    <legend>Dados de Acesso</legend>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <c:if test="${usuario_usuariotipo != 'funcionario'}">
                                            <cF:cFormBasic id="login" valor="${func.login}" rotulo="Usuário" type="text" classe="form-control" outro="required" />
                                            </c:if>
                                            <c:if test="${usuario_usuariotipo == 'funcionario'}">
                                            <cF:cFormBasic id="login" valor="${func.login}" rotulo="Usuário" type="text" classe="form-control" outro="disabled='disabled'" />
                                            </c:if>
                                        </div>
                                            <c:if test="${empty func.id}">
                                                <div class="col-sm-6">
                                                    <cF:cFormBasic id="senha" valor="${func.senha}"
                                                                   rotulo="Senha" type="text" classe="form-control"
                                                                   outro="required" />
                                                </div>
                                            </c:if>
                                        </div>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <legend>Dados de Contato</legend>

                                        <div class="row">
                                            <div class="col-sm-6">
                                                <cF:cFormBasic id="email" valor="${func.contato.email}"
                                                               rotulo="E-mail" type="email" classe="form-control"
                                                               outro="required" />
                                            </div>
                                            <div class="col-sm-6">
                                                <cF:cFormBasic id="celular" valor="${func.contato.celular}"
                                                               rotulo="Celular" type="text" classe="form-control" />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-6">
                                                <cF:cFormBasic id="tel_com"
                                                               valor="${func.contato.telefoneCom}"
                                                               rotulo="Telefone Comercial" type="text" classe="form-control" />
                                            </div>
                                            <div class="col-sm-6">
                                                <cF:cFormBasic id="tel_res"
                                                               valor="${func.contato.telefoneRes}"
                                                               rotulo="Telefone Residencial" type="text"
                                                               classe="form-control" />
                                            </div>
                                        </div>
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <legend>Dados de Localização</legend>

                                        <div class="row">
                                            <div class="col-sm-9">
                                                <cF:cFormBasic id="logradouro"
                                                               valor="${func.endereco.logradouro}" rotulo="Endereço"
                                                               type="text" classe="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <cF:cFormBasic id="numero" valor="${func.endereco.numero}"
                                                               rotulo="Número" type="text" classe="form-control" />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-9">
                                                <cF:cFormBasic id="bairro" valor="${func.endereco.bairro}"
                                                               rotulo="Bairro" type="text" classe="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <cF:cFormBasic id="cep" valor="${func.endereco.cep}"
                                                               rotulo="CEP" type="text" classe="form-control" />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-7">
                                                <cF:cFormBasic id="cidade" valor="${func.endereco.cidade}"
                                                               rotulo="Cidade" type="text" classe="form-control" />
                                            </div>
                                            <div class="col-sm-2">
                                                <cF:cSelectUF id="estado" valor="${func.endereco.estado}"
                                                              rotulo="Estado" classe="form-control" />
                                            </div>
                                            <div class="col-sm-3">
                                                <cF:cFormBasic id="pais"
                                                               valor="${func.endereco.pais?func.endereco.pais:'Brasil'}"
                                                               rotulo="País" type="text" classe="form-control" />
                                            </div>
                                        </div>

                                        <div class="row">
                                            <c:if test="${usuario_usuariotipo != 'funcionario'}">
                                                <div class="col-sm-4">
                                                <cF:cSelectSouN id="ativo" valor="${func.ativo=='s'?'s':'n'}" rotulo="Status do Usuário" classe="form-control" />
                                                </div>
                                            </c:if>
                                            <c:if test="${usuario_usuariotipo == 'funcionario'}">
                                                <cF:cFormHidden id="ativo" valor="${func.ativo=='s'?'s':'n'}" type="hidden" />
                                            </c:if>
                                            <div class="col-sm-4">
                                                <cF:cSelectUsuarioTipo id="usuario_tipo"
                                                                       valor="${func.usuarioTipo}" rotulo="Tipo de Usuário"
                                                                       classe="form-control" />
                                            </div>
                                        </div>
                                    </fieldset>

                                    <div class="form-group">
                                        <input class="btn btn-primary" type="submit" value="Gravar" />
                                        <c:if test="${func.id!=null && (usuario_id != func.id && usuario_usuariotipo == 'gerente')}">
                                            <a href="${urlHome}sistema?c=Funcionario&acao=resetar&id=${func.id}" class="btn btn-default">Resetar Senha</a>
                                        </c:if>
                                        <c:if test="${usuario_id == func.id}">
                                            <a href="${urlHome}sistema?c=Funcionario&acao=senha&id=${func.id}" class="btn btn-default">Alterar Senha</a>
                                        </c:if>
                                        <a href="${urlHome}sistema?c=Funcionario" class="btn btn-default">Voltar</a>

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


