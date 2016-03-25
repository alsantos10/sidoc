<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>

<c:if test="${usuarioLogado.usuarioTipo =='gerente'}">
	<input type="hidden" name="${id}" value="funcionario" />
</c:if>

<c:if test="${usuarioLogado.usuarioTipo =='administrador'}">
	<div class="form-group ${colSize}">
		<label class="control-label">${rotulo}: </label> <select id="${id}"
			name="${id}" class="${classe}" ${outro}>
			<option value="funcionario"
				${valor=='funcionario'? ' selected="selected"':''}>Funcionário</option>
			<option value="gerente"
				${valor=='gerente'? ' selected="selected"':''}>Gerente</option>
			<option value="administrador"
				${valor=='administrador'? ' selected="selected"':''}>Administrador</option>
		</select>
	</div>
</c:if>