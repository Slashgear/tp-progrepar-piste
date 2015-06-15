<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${action.libaction}"/></h1>

        <div class="col-xs-12">
            <h2>Résumé</h2>

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
            </div>
        </div>
        <c:if test="${nbInscrits>0}">
            <div class="col-md-6">
                <h3>Proportion d'apprenants validant cette action</h3>

                <div class="well">
                    <div id="piechart_validation" style="width: 100%; height: 100%;"></div>
                </div>
            </div>
        </c:if>
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
        }
        $(window).resize(function () {
            drawChart();
        });
    </script>
</c:if>
<%@include file="../common/footer.jsp" %>