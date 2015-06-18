<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<a href="/action/<c:out value="${estAssocie.numaction}"/>">
    <c:out value="${estAssocie.actionByNumaction.libaction}"/> (<c:out
        value="${coefActions.get(estAssocie.actionByNumaction.numaction)}"/>)</a>
<c:choose>
    <c:when test="${not empty apprenant}">
        <c:choose>
            <c:when test="${not empty scoresActions.get(estAssocie.actionByNumaction.numaction)}">
                <c:choose>
                    <c:when test="${scoresActions.get(estAssocie.actionByNumaction.numaction) < scoresMinimum.get(estAssocie.actionByNumaction.numaction)}">
                        <a href="/obtient/${apprenant.numapprenant}/action/${estAssocie.actionByNumaction.numaction}/jeu/${idJeu}/modifier"
                           class="label label-danger pull-right" data-toggle="tooltip"
                           data-placement="right" title="Modifier la note">
                            <fmt:formatNumber
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"
                                    value="${scoresActions.get(estAssocie.actionByNumaction.numaction)}"/>
                            /20 (minimum : <c:out
                                value="${scoresMinimum.get(estAssocie.actionByNumaction.numaction)}"/>/20)
                        </a>
                    </c:when>
                    <c:otherwise>
                        &nbsp;
                        <a href="/obtient/${apprenant.numapprenant}/action/${estAssocie.actionByNumaction.numaction}/jeu/${idJeu}/modifier"
                           class="label label-success pull-right" data-toggle="tooltip"
                           data-placement="right" title="Modifier la note">
                            <fmt:formatNumber
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"
                                    value="${scoresActions.get(estAssocie.actionByNumaction.numaction)}"/>/20</a>

                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <a href="/obtient/${apprenant.numapprenant}/action/${estAssocie.actionByNumaction.numaction}/jeu/${idJeu}/ajout"
                   class="label label-primary pull-right">
                    Saisir une note</a>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
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
    </c:otherwise>
</c:choose>

<br/>