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
		<option value="M"
			<c:if test="${valor == 'M'}"> selected="selected"</c:if>>Masculino</option>
		<option value="F"
			<c:if test="${valor == 'F'}"> selected="selected"</c:if>>Feminino</option>

	</select>
</div>
