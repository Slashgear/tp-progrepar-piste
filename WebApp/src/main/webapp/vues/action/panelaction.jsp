<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
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