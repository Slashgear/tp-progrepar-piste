<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
        <h1><c:out value="${action.libaction}"/></h1>

        <h2>Résumé</h2>

        <div class="col-xs-12">
            <div class="well">
                <p>Identifiant #<c:out value="${action.numaction}"/></p>

                <p>Score minimum : <c:out value="${action.scoremin}"/></p>
            </div>
        </div>
        <h2>Graphes</h2>

        <div class="col-md-6">
            <div id="donutchart" class="well well-lg">
            </div>
        </div>
        <div class="col-md-6">
            <div id="donutchart2" class="well well-lg">
            </div>
        </div>
    </div>
</div>
<c:if test="${not empty returnPage}">
    <div class="navbar-fixed-bottom">
        <a href=
               <c:out value="${returnPage}"/> class="btn btn-inverse btn-fab btn-raised mdi-navigation-arrow-back"></a>
    </div>
</c:if>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work', 25],
            ['Eat', 2],
            ['Commute', 2],
            ['Watch TV', 2],
            ['Sleep', 7]
        ]);

        var options = {
            title: 'My Daily Activities',
            is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
        chart = new google.visualization.PieChart(document.getElementById('donutchart2'));
        chart.draw(data, options);
    }
</script>
<jsp:include page="../common/footer.jsp"/>