<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${mission.libmission}"/></h1>

        <div class="col-xs-12">

            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${mission.nummission}"/></p>

                <p>Il y a actuellement <strong><c:out value="${actionNb}"/>
                    actions</strong> à accomplir dans cette mission.</p>
            </div>
        </div>
        <div class="col-lg-6">

            <h2>Détails</h2>

            <div class="well">
                <c:forEach
                        items="${objectif.estAssociesByNumobjectif}"
                        var="estAssocie">
                    <a href="/action/<c:out value="${estAssocie.numaction}"/>">
                        <c:out value="${estAssocie.actionByNumaction.libaction}"/> (<c:out
                            value="${coefActions.get(estAssocie.actionByNumaction.numaction)}"/>)</a>
                    <c:choose>
                        <c:when test="${not empty scoresActions.get(estAssocie.actionByNumaction.numaction)}">
                            <c:choose>
                                <c:when test="${scoresActions.get(estAssocie.actionByNumaction.numaction) < scoresMinimum.get(estAssocie.actionByNumaction.numaction)}">
                                    &nbsp;<span class="label label-danger pull-right"><fmt:formatNumber
                                        type="number"
                                        minFractionDigits="2"
                                        maxFractionDigits="2"
                                        value="${scoresActions.get(estAssocie.actionByNumaction.numaction)}"/>
                                    /20 (minimum : <c:out
                                        value="${scoresMinimum.get(estAssocie.actionByNumaction.numaction)}"/>/20)</span>
                                </c:when>
                                <c:otherwise>
                                    &nbsp;<span class="label label-success pull-right"><fmt:formatNumber
                                        type="number"
                                        minFractionDigits="2"
                                        maxFractionDigits="2"
                                        value="${scoresActions.get(estAssocie.actionByNumaction.numaction)}"/>/20</span>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                    <br/>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
