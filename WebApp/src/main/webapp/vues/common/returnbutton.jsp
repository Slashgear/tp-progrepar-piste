<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty returnPage}">
    <div class="navbar-fixed-bottom">
        <a href="<c:out value="${returnPage}"/>" class="btn btn-inverse btn-fab btn-raised mdi-navigation-arrow-back"/>
        </a>
    </div>
</c:if>