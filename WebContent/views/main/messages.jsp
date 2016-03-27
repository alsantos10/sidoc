<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty mensagem}">
    <div class="alert alert-${mensagem[0]}">
        <button type="button" class="close" data-dismiss="alert">×</button>
        ${mensagem[1]}
    </div>
</c:if>