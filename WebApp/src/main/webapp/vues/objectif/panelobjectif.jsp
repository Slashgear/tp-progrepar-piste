<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<h4><a href="action/objectif/<c:out value="${objectif.numobjectif}"/>">
    Objectif : <c:out value="${objectif.objectifByNumobjectif.libobectif}"/></a>
    <c:choose>
        <c:when test="${
 not empty statsObjectifs.get(objectif.numobjectif)
 and not empty countScore
 and countScore.get(objectif.numobjectif) == objectif.objectifByNumobjectif.estAssociesByNumobjectif.size()}">&nbsp;
            <c:choose>
                <c:when test="${statsObjectifsFailure.get(objectif.numobjectif) != 0 || statsObjectifs.get(objectif.numobjectif) < 10}">
<span class="label label-danger">
    <fmt:formatNumber type="number"
                      minFractionDigits="2"
                      maxFractionDigits="2"
                      value="${statsObjectifs.get(objectif.numobjectif)}"/>/20
    : Objectif non validé
</span>
                </c:when>
                <c:otherwise>
<span class="label label-success">
    <fmt:formatNumber
            type="number"
            minFractionDigits="2"
            maxFractionDigits="2"
            value="${statsObjectifs.get(objectif.numobjectif)}"/>/20
    : Objectif validé
</span>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <span class="label label-info">Note(s) manquante(s)</span>
        </c:otherwise>
    </c:choose>
</h4>
<c:forEach
        items="${objectif.objectifByNumobjectif.estAssociesByNumobjectif}"
        var="estAssocie">
    <%@include file="../action/panelaction.jsp" %>
</c:forEach>
 