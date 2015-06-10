<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp" %>
<div class="container">
    <div class="row">
        <c:forEach items="${jeux}" var="jeu">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="well">
                    <a href=<c:out value="jeu/${jeu.numjeu}"/>>
                        #<c:out value="${jeu.numjeu}"/> : <c:out value="${jeu.libellejeu}"/>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>