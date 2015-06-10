<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp" %>
<div class="container">
    <div class="row">
        <c:forEach items="${listeActions}" var="action">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="well">
                    <a href=<c:out value="action/${action.numaction}"/>>
                        #<c:out value="${action.numaction}"/> : <c:out value="${action.libaction}"/>
                    </a>

                    <p>Score minimum : <c:out value="${action.scoremin}"/></p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>