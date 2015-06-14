<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${jeu.libellejeu}"/></h1>

        <div class="col-xs-12">

            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${jeu.numjeu}"/></p>
            </div>
        </div>
        <div class="col-xs-12">

            <h2>Détails</h2>

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <c:forEach items="${jeu.missionsByNumjeu}" var="mission">
                        <div class="panel-heading" role="tab" id="headingOne" data-toggle="collapse"
                             data-parent="#accordion"
                             href="#collapseMission<c:out value="${mission.nummission}"/>"
                             aria-expanded="false"
                             aria-controls="collapseMission<c:out value="${mission.nummission}"/>">
                            <h3 class="panel-title">
                                Mission : <c:out value="${mission.libmission}"/>
                                <a>
                                    <i class="icone-right mdi-navigation-unfold-more"></i></a>
                            </h3>
                        </div>
                        <div id="collapseMission<c:out value="${mission.nummission}"/>"
                             class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne">
                            <div class="panel-body">
                                <c:forEach items="${mission.fixesByNummission}" var="fixe">
                                    <h4><a href="action/objectif/<c:out value="${fixe.numobjectif}"/>">Objectif : <c:out
                                            value="${fixe.objectifByNumobjectif.libobectif}"/></a></h4>
                                    <c:forEach
                                            items="${fixe.objectifByNumobjectif.estAssociesByNumobjectif}"
                                            var="estAssocie">
                                        <a href="/action/<c:out value="${estAssocie.numaction}"/>">
                                            <c:out value="${estAssocie.actionByNumaction.libaction}"/>
                                        </a><br/>
                                    </c:forEach>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
