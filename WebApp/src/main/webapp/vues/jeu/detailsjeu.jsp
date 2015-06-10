<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${jeu.libellejeu}"/></h1>

        <h2>Résumé</h2>

        <div class="col-xs-12">
            <div class="well">
                <p>Identifiant #<c:out value="${jeu.numjeu}"/></p>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp" %>