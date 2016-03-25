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

		<option value="n"
			<c:if test="${valor == 'n'}"> selected="selected"</c:if>>Inativo</option>
		<option value="s"
			<c:if test="${valor == 's'}"> selected="selected"</c:if>>Ativo</option>

	</select>
</div>
