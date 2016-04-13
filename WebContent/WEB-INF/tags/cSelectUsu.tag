<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>

<c:if test="${usuario_usuariotipo == 'funcionario'}">
    <input type="hidden" name="${id}" value="${usuarioLogado.id}" />
</c:if>
    
<c:if test="${usuario_usuariotipo == 'gerente' || usuario_usuariotipo == 'administrador'}">
    <div class="form-group ${colSize}">
        <label class="control-label">${rotulo}: </label>

        <select id="${id}" name="${id}" class="${classe}" ${outro}>
            <option value="">Selecione...</option>

            <jsp:useBean id="dao2" class="br.com.sidoc.DAO.UsuarioDAO">
                <c:forEach var="user" items="${dao2.lista}">
                    ${usuario_departamento.id} == ${user.departamento.id}
                    
                    <c:if test="${usuario_usuariotipo == 'gerente' && usuario_departamento.id == user.departamento.id}">
                        <c:choose>
                            <c:when test="${valor == user.id}">
                                <option value="${user.id}" selected="selected">${user.nome}
                                    ${user.sobrenome}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${user.id}">${user.nome} ${user.sobrenome}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </jsp:useBean>
        </select>	
    </div>
</c:if>
