<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<div class="panel-heading" role="tab" id="headingOne" data-toggle="collapse"
     data-parent="#accordion"
     href="#collapseMission<c:out value="${mission.nummission}"/>"
     aria-expanded="false"
     aria-controls="collapseMission<c:out value="${mission.nummission}"/>">
    <h3 class="panel-title">
        Mission : <c:out value="${mission.libmission}"/>
        <c:if test="${not empty apprenant}">
            <c:choose>
                <c:when test="${

 not empty statsMissions.get(mission.nummission)
 and not empty countScoreMission
 and countScoreMission.get(mission.nummission) == mission.getFixesByNummission().size()}">&nbsp;
                    <c:choose>
                        <c:when test="${statsMissionsFailure.get(mission.nummission) != 0 || statsMissions.get(mission.nummission) < 10}">
<span class="label label-danger">
    <fmt:formatNumber type="number"
                      minFractionDigits="2"
                      maxFractionDigits="2"
                      value="${statsMissions.get(mission.nummission)}"/>/20
    : Mission non validée
</span>
                        </c:when>
                        <c:otherwise>
<span class="label label-success">
    <fmt:formatNumber
            type="number"
            minFractionDigits="2"
            maxFractionDigits="2"
            value="${statsMissions.get(mission.nummission)}"/>/20
    : Mission validée
</span>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <span class="label label-info">Note(s) manquante(s)</span>
                </c:otherwise>
            </c:choose>
        </c:if>
        <a>
            <i class="icone-right mdi-navigation-unfold-more"></i></a>
    </h3>
</div>
<div id="collapseMission<c:out value="${mission.nummission}"/>"
     class="panel-collapse collapse" role="tabpanel"
     aria-labelledby="headingOne">
    <div class="panel-body">
        <a href="<c:out value="mission/${mission.nummission}"/>">Plus d'informations sur la
            mission</a>
        <c:forEach items="${mission.fixesByNummission}" var="objectif">
            <%@ include file="../objectif/panelobjectif.jsp" %>
        </c:forEach>
    </div>
</div>