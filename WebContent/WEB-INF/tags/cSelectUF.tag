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
		<option value="">UF</option>
		<option value="AC"
			<c:if test="${valor=='AC'}"> selected="selected"</c:if>>AC</option>
		<option value="AL"
			<c:if test="${valor=='AL'}"> selected="selected"</c:if>>AL</option>
		<option value="AP"
			<c:if test="${valor=='AP'}"> selected="selected"</c:if>>AP</option>
		<option value="AM"
			<c:if test="${valor=='AM'}"> selected="selected"</c:if>>AM</option>
		<option value="BA"
			<c:if test="${valor=='BA'}"> selected="selected"</c:if>>BA</option>
		<option value="CE"
			<c:if test="${valor=='CE'}"> selected="selected"</c:if>>CE</option>
		<option value="DF"
			<c:if test="${valor=='DF'}"> selected="selected"</c:if>>DF</option>
		<option value="ES"
			<c:if test="${valor=='ES'}"> selected="selected"</c:if>>ES</option>
		<option value="GO"
			<c:if test="${valor=='GO'}"> selected="selected"</c:if>>GO</option>
		<option value="MA"
			<c:if test="${valor=='MA'}"> selected="selected"</c:if>>MA</option>
		<option value="MG"
			<c:if test="${valor=='MG'}"> selected="selected"</c:if>>MG</option>
		<option value="MS"
			<c:if test="${valor=='MS'}"> selected="selected"</c:if>>MS</option>
		<option value="MT"
			<c:if test="${valor=='MT'}"> selected="selected"</c:if>>MT</option>
		<option value="PA"
			<c:if test="${valor=='PA'}"> selected="selected"</c:if>>PA</option>
		<option value="PB"
			<c:if test="${valor=='PB'}"> selected="selected"</c:if>>PB</option>
		<option value="PE"
			<c:if test="${valor=='PE'}"> selected="selected"</c:if>>PE</option>
		<option value="PI"
			<c:if test="${valor=='PI'}"> selected="selected"</c:if>>PI</option>
		<option value="PR"
			<c:if test="${valor=='PR'}"> selected="selected"</c:if>>PR</option>
		<option value="RJ"
			<c:if test="${valor=='RJ'}"> selected="selected"</c:if>>RJ</option>
		<option value="RN"
			<c:if test="${valor=='RN'}"> selected="selected"</c:if>>RN</option>
		<option value="RO"
			<c:if test="${valor=='RO'}"> selected="selected"</c:if>>RO</option>
		<option value="RR"
			<c:if test="${valor=='RR'}"> selected="selected"</c:if>>RR</option>
		<option value="RS"
			<c:if test="${valor=='RS'}"> selected="selected"</c:if>>RS</option>
		<option value="SC"
			<c:if test="${valor=='SC'}"> selected="selected"</c:if>>SC</option>
		<option value="SE"
			<c:if test="${valor=='SE'}"> selected="selected"</c:if>>SE</option>
		<option value="SP"
			<c:if test="${valor=='SP'}"> selected="selected"</c:if>>SP</option>
		<option value="TO"
			<c:if test="${valor=='TO'}"> selected="selected"</c:if>>TO</option>
	</select>
</div>
