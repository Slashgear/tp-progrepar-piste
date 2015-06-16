<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<div class="panel-heading" role="tab" id="headingOne" data-toggle="collapse"
     data-parent="#accordion"
     href="#collapseMission<c:out value="${mission.nummission}"/>"
     aria-expanded="false"
     aria-controls="collapseMission<c:out value="${mission.nummission}"/>">
    <h3 class="panel-title">
        Mission : <c:out value="${mission.libmission}"/>
        <c:if test="${not empty statsMissions.get(mission.nummission)}">
            &nbsp;<span class="badge"><fmt:formatNumber type="number" minFractionDigits="2"
                                                        maxFractionDigits="2"
                                                        value="${statsMissions.get(mission.nummission)}"></fmt:formatNumber>/20</span>
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