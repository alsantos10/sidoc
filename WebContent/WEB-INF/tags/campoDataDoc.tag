<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id"%>
<%@ attribute name="rotulo"%>
<%@ attribute name="type"%>
<%@ attribute name="classe"%>
<%@ attribute name="valor"%>
<%@ attribute name="outro"%>
<%@ attribute name="colSize"%>


<c:set var="data_value">
	<fmt:formatDate
		value="${valor=='cad'?doc.dtCadastro.time:doc.dtValidade.time}"
		pattern="dd/MM/yyyy" />
</c:set>


<div class="form-group ${colSize}">
	<label class="control-label">${rotulo}: </label> <input id="${id}"
		name="${id}" type="${type}" class="${classe}" ${outro}
		value="${data_value}" />
</div>
<script>

	$("#${id}").datepicker({dateFormat: 'dd/mm/yy' });
</script>