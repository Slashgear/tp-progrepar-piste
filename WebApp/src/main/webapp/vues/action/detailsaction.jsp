<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${action.libaction}"/></h1>

        <div class="col-md-6">
            <h3>Résumé</h3>

            <div class="well">
                <p>Identifiant #<c:out value="${action.numaction}"/></p>

                <p>Coefficient : <c:out value="${coef}"/></p>
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
                <c:if test="${not empty action.actNumaction}">
                    <p>Action précédente : <a href="/action/${action.actNumaction}" title="détail action parente">
                        #<c:out value="${action.actNumaction}"/></a></p>
                </c:if>
            </div>
        </div>
        <c:if test="${nbInscrits>0}">
            <div class="col-md-6">
                    ${pieChartValidation.getDiv()}
            </div>
            <div class="col-md-6">
                    ${pieChartObtention.getDiv()}
            </div>
            <div class="col-md-6">
                    ${columnChartRepartition.getDiv()}
            </div>
        </c:if>
        <c:if test="${not empty childActions}">
            <div class="col-md-6">
                <h3>Action<c:if test="${fn:length(childActions)>1}">s</c:if> suivante<c:if
                        test="${fn:length(childActions)>1}">s</c:if></h3>

                <div class="well">
                    <div class="list-group">
                        <c:forEach items="${childActions}" var="action" varStatus="loop">
                            <div class="list-group-item">
                                <div class="row-content">
                                    <h4 class="list-group-item-heading"><a
                                            href="/action/<c:out value="${action.numaction}"/>">#<c:out
                                            value="${action.numaction}"/></a></h4>

                                    <p class="list-group-item-text"><c:out value="${action.libaction}"/></p>
                                </div>
                            </div>
                            <c:if test="${!loop.last}">
                                <div class="list-group-separator"></div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <%@include file="../common/returnbutton.jsp" %>
        </div>
    </div>
</div>
<c:if test="${nbInscrits>0}">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    ${pieChartValidation.getScript()}
    ${pieChartObtention.getScript()}
    ${columnChartRepartition.getScript()}
</c:if>

<%@include file="../common/footer.jsp" %>