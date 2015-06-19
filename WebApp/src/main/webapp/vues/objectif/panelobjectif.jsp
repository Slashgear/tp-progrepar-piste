<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<h4><a href="objectif/<c:out value="${objectif.numobjectif}"/>">
    Objectif : <c:out value="${objectif.objectifByNumobjectif.libobectif}"/></a>
    <c:if test="${not empty apprenant}">
        <c:choose>
            <c:when test="${
 not empty statsObjectifs.get(objectif.numobjectif)
 and not empty countScoreObjectif
 and countScoreObjectif.get(objectif.numobjectif) == objectif.objectifByNumobjectif.estAssociesByNumobjectif.size()}">&nbsp;
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
    </c:if>

</h4>
<c:forEach
        items="${objectif.objectifByNumobjectif.estAssociesByNumobjectif}"
        var="estAssocie">
    <%@include file="../action/panelaction.jsp" %>
</c:forEach>
 