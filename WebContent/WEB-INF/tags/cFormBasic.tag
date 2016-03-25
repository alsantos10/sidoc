<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="type"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>

<div class="form-group ${colSize}">
	<label class="control-label">${rotulo}: </label> <input id="${id}"
		name="${id}" type="${type}" class="${classe}" value="${valor}"
		${outro} /> <span class="text-danger"></span>
</div>
