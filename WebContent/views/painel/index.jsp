<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="cF"%>
<c:url value="/" var="urlHome" />
<c:url value="assets/uploads/" var="dir_images" />

<!DOCTYPE html>
<html lang="pt-BR">
<head>
<c:import url="../main/head_meta.jsp" />
<c:url value="${link_acao}" var="linkNovo" />
<c:set var="titulo" value="Arquivo" />

<title>SIDOC | ${titulo}</title>
</head>

<body class="home">

	<c:import url="../main/menu.jsp" />

	<c:import url="../painel/content.jsp" />

	<c:import url="../main/rodape.jsp" />

</body>
</html>