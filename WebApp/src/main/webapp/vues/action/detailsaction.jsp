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
                <h3>Apprenants validant cette action</h3>

                <div class="well">
                    <div id="piechart_validation" style="width: 100%; height: 100%;"></div>
                </div>
            </div>
            <div class="col-md-6">
                <h3>Apprenants inscrit ayant obtenu une note</h3>

                <div class="well">
                    <div id="piechart_obtention" style="width: 100%; height: 100%;"></div>
                </div>
            </div>
            <div class="col-md-6">
                <h3>Répartition des notes</h3>

                <div class="well">
                    <div id="barchart_repartition" style="width: 100%; height: 100%;"></div>
                </div>
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
    <script type="text/javascript">
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['% d\'apprenants validant l\'action', ${nbValidators}],
                ['% d\'apprenants ne validant pas l\'action', ${nbInscrits - nbValidators}]
            ]);

            var options = {
                colors: ['#f44336', '#3f51b5', '#9c27b0', '#4caf50', '#ffc624', '#ff5722']
            };
            var chart = new google.visualization.PieChart(document.getElementById('piechart_validation'));
            chart.draw(data, options);

            var data2 = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['% d\'apprenants qui ont obtenu une note', ${nbObtient}],
                ['% d\'apprenants sans note', ${nbInscrits - nbObtient}]
            ]);

            var chart2 = new google.visualization.PieChart(document.getElementById('piechart_obtention'));
            chart2.draw(data2, options);


            var data3 = new google.visualization.arrayToDataTable([
                ['Move', 'Nombre d\'apprenants'],
                ["0 à 10", ${obt0_10}],
                ["10 à 14", ${obt10_14}],
                ["14 à 18", ${obt14_18}],
                ["18 à 20", ${obt18_20}]
            ]);

            var chart3 = new google.visualization.ColumnChart(document.getElementById('barchart_repartition'));
            chart3.draw(data3, options);
        }
        $(window).resize(function () {
            drawChart();
        });
    </script>
</c:if>

<%@include file="../common/footer.jsp" %>