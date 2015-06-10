<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/header.jsp" %>
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
<%@include file="../common/footer.jsp" %>