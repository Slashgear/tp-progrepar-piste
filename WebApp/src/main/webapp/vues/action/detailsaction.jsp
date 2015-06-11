<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${action.libaction}"/></h1>

        <div class="col-xs-12">
            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${action.numaction}"/></p>

                <p>Score minimum : <c:out value="${action.scoremin}"/>/20</p>
                <c:if test="${not empty averageScore}">
                    <c:choose>
                        <c:when test="${averageScore < action.scoremin}">
                            <p class="text-danger"><strong>Score moyen : <c:out value="${averageScore}"/>/20</strong>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-success"><strong>Score moyen : <c:out value="${averageScore}"/>/20</strong>
                            </p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>