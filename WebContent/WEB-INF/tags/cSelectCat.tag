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

		<jsp:useBean id="daoCt" class="br.com.sidoc.DAO.CategoriaDAO">
			<c:forEach var="opcao" items="${daoCt.lista}" varStatus="id">
				<c:choose>
					<c:when test="${valor == opcao.id}">
						<option value="${opcao.id}" selected="selected">${opcao.categoria}</option>
					</c:when>
					<c:otherwise>
						<option value="${opcao.id}">${opcao.categoria}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</jsp:useBean>
	</select>
</div>
