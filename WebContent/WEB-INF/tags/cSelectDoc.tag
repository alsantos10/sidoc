<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>

<div class="form-group ${colSize}">
    <label class="control-label">${rotulo}: </label> <select id="${id}"
                                                             name="${id}" class="${classe}" ${outro}>
        <option value="">Selecione...</option>

        <jsp:useBean id="dao" class="br.com.sidoc.DAO.DocumentoDAO">
            <c:forEach var="opcao" items="${dao.lista}" varStatus="id">

                <c:if test="${usuario_usuariotipo == 'funcionario' && usuario_id == opcao.usuario.id}">
                    <c:choose>
                        <c:when test="${valor == opcao.id}">
                            <option value="${opcao.id}" selected="selected">${opcao.titulo}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${opcao.id}">${opcao.titulo}</option>
                        </c:otherwise>
                    </c:choose>
                </c:if>


                <c:if test="${usuario_usuariotipo == 'gerente' && usuario_departamento.id == opcao.departamento.id}">
                    <c:choose>
                        <c:when test="${valor == opcao.id}">
                            <option value="${opcao.id}" selected="selected">${opcao.titulo}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${opcao.id}">${opcao.titulo}</option>
                        </c:otherwise>
                    </c:choose>
                </c:if>


                <c:if test="${usuario_usuariotipo == 'administrador'}">
                    <c:choose>
                        <c:when test="${valor == opcao.id}">
                            <option value="${opcao.id}" selected="selected">${opcao.titulo}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${opcao.id}">${opcao.titulo}</option>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:forEach>



        </jsp:useBean>
    </select>
</div>
