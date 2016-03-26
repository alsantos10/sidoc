<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>

<div class="form-group ${colSize}">
	<label class="control-label">${rotulo}: </label>
		<select id="${id}" <c:if test="${usuario_usuariotipo == 'funcionario'}"> disabled="disabledd </c:if>
		name="${id}" class="${classe}" ${outro}>
		<option value="">Selecione...</option>

		<c:when test="${usuario_usuariotipo == 'funcionario'}">
			<option value="${usuario_departamento.id}" selected="selected">${usuario_departamento.departamento}</option>
		</c:when>
		
		<c:otherwise>
			<jsp:useBean id="dao" class="br.com.sidoc.DAO.DepartamentoDAO">
				<c:forEach var="opcao" items="${dao.lista}" varStatus="id">
					<c:choose>
						<c:when test="${valor == opcao.id}">
							<option value="${opcao.id}" selected="selected">${opcao.departamento}</option>
						</c:when>
						<c:otherwise>
							<option value="${opcao.id}">${opcao.departamento}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</jsp:useBean>
		</c:otherwise>
	</select>
</div>
