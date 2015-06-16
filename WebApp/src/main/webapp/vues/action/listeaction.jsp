<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1>Liste des actions</h1>
        <c:if test="${empty listeActions}">
            <div class="alert alert-info">
                Aucune action trouvée.
            </div>
        </c:if>
        <c:forEach items="${listeActions}" var="action">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <a href=<c:out value="action/${action.numaction}"/>>
                <div class="well">
                    <p>#<c:out value="${action.numaction}"/> : <c:out value="${action.libaction}"/><br>
                        Coefficient : <c:out value="${coefActions.get(action.numaction)}"/><br>
                        Score minimum : <c:out value="${action.scoremin}"/><br>
                        <c:if test="${not empty scoresActions.get(action.numaction)}">
                            <c:choose>
                                <c:when test="${scoresActions.get(action.numaction) < action.scoremin}">
                                <span class="text-danger"><strong>Score moyen : <c:out
                                        value="${scoresActions.get(action.numaction)}"/>/20</strong>
                                </span>
                                </c:when>
                                <c:otherwise>
                                <span class="text-success"><strong>Score moyen : <c:out
                                        value="${scoresActions.get(action.numaction)}"/>/20</strong>
                                </span>
                                </c:otherwise>
                            </c:choose>
                        </c:if></p>
                </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>