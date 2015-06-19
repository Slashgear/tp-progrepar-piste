<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${not empty breadcrumbList}">
    <div class="container">
        <div class="row">
            <ul class="breadcrumb" style="margin-bottom: 5px;">
                <c:forEach items="${breadcrumbList}" var="item">
                    <c:choose>
                        <c:when test="${item.isActive()}">
                            <li class="active">${item.label}</li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${item.url}">${item.label}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>
