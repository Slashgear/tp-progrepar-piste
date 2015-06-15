<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty returnPage}">
    <a href="${returnPage}" class="btn btn-default">Retour</a>
</c:if>