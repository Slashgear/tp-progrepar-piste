<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${jeu.libellejeu}"/></h1>

        <div class="col-xs-12">

            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${jeu.numjeu}"/></p>

                <p>Il y a actuellement <strong><c:out value="${missionNb}"/> missions</strong>, <strong><c:out
                        value="${objectifNb}"/> objectifs</strong>
                    , et <strong><c:out value="${actionNb}"/> actions</strong> à accomplir dans ce jeu.</p>

                <p><strong><c:out value="${inscritNb}"/></strong> Apprenants sont inscrit à ce jeu.</p>
            </div>
        </div>
        <div class="col-lg-6">

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
                                <c:if test="${not empty statsMissions.get(mission.nummission)}">
                                    &nbsp;<span class="badge"><fmt:formatNumber type="number" minFractionDigits="2"
                                                                                maxFractionDigits="2"
                                                                                value="${statsMissions.get(mission.nummission)}"/>/20</span>
                                </c:if>
                                <a>
                                    <i class="icone-right mdi-navigation-unfold-more"></i></a>
                            </h3>
                        </div>
                        <div id="collapseMission<c:out value="${mission.nummission}"/>"
                             class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne">
                            <div class="panel-body">
                                <c:forEach items="${mission.fixesByNummission}" var="fixe">
                                    <h4><a href="action/objectif/<c:out value="${fixe.numobjectif}"/>">
                                        Objectif : <c:out value="${fixe.objectifByNumobjectif.libobectif}"/> </a>
                                        <c:if test="${not empty statsObjectifs.get(mission.nummission).get(fixe.numobjectif)}">
                                            &nbsp;<span class="badge"><fmt:formatNumber type="number"
                                                                                        minFractionDigits="2"
                                                                                        maxFractionDigits="2"
                                                                                        value="${statsObjectifs.get(mission.nummission).get(fixe.numobjectif)}"/>/20</span>
                                        </c:if>
                                    </h4>
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
        <div class="col-lg-6">
            <h3>Popularité du Jeu</h3>

            <div class="well">
                <div id="piechart_popularity" style="width: 100%; height: 100%;"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Inscrits', ${inscritNb}],
            ['Non inscrits', ${apprenantNb - inscritNb}]
        ]);

        var options = {
            colors: ['#f44336', '#3f51b5', '#9c27b0', '#4caf50', '#ffc624', '#ff5722']
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart_popularity'));
        chart.draw(data, options);
    }
    $(window).resize(function () {
        drawChart();
    });
</script>
<%@include file="../common/footer.jsp" %>
