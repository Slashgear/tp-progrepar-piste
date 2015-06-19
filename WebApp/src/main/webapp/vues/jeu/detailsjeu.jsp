<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <c:choose>
            <c:when test="${not empty apprenant}">
                <c:choose>
                    <c:when test="${not empty aValide and aValide == true}">
                        <h1 class="text-success"><c:out value="${jeu.libellejeu}"/> : Validé</h1>

                        <div class="well">
                            <iframe src="http://lepetitbonhommeenmouss.eu/" style="width:100%;height: 200px;"></iframe>
                        </div>
                    </c:when>
                    <c:when test="${not empty aNonValide and aNonValide == true}">
                        <h1 class="text-danger"><c:out value="${jeu.libellejeu}"/> : Non validé</h1>
                    </c:when>
                    <c:otherwise>
                        <h1><c:out value="${jeu.libellejeu}"/> : non terminé</h1>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h1><c:out value="${jeu.libellejeu}"/></h1>
            </c:otherwise>
        </c:choose>


        <div class="col-xs-12">

            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${jeu.numjeu}"/></p>

                <p>Il y a actuellement <strong><c:out value="${missionNb}"/> missions</strong>, <strong><c:out
                        value="${objectifNb}"/> objectifs</strong>, et <strong><c:out value="${actionNb}"/>
                    actions</strong> à accomplir dans ce jeu.</p>

                <p><strong><c:out value="${inscritNb}"/></strong> Apprenants sont inscrit à ce jeu.</p>
            </div>
        </div>
        <div class="col-lg-6">

            <h2>Détails<c:if test="${not empty apprenant}"> pour l'apprenant : <a
                    href="/apprenant/${apprenant.numapprenant}"><c:out
                    value="${apprenant.prenomapprenant} ${apprenant.nomapprenant}"/></a></c:if></h2>

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <c:forEach items="${jeu.missionsByNumjeu}" var="mission">
                        <%@include file="../mission/panelmission.jsp" %>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:if test="${pieChart.displayable()}">
            <div class="col-lg-6">
                    ${pieChart.getDiv()}
            </div>
        </c:if>
        <c:if test="${pieChart2.displayable()}">
            <div class="col-lg-6">
                    ${pieChart2.getDiv()}
            </div>
        </c:if>
        <c:if test="${pieChart3.displayable()}">
            <div class="col-lg-6">
                    ${pieChart3.getDiv()}
            </div>
        </c:if>
    </div>
</div>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
${pieChart.getScript()}
${pieChart2.getScript()}
${pieChart3.getScript()}
<%@include file="../common/footer.jsp" %>
