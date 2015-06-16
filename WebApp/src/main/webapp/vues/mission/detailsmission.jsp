<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${mission.libmission}"/></h1>

        <div class="col-xs-12">

            <h2>Résumé</h2>

            <div class="well">
                <p>Identifiant #<c:out value="${mission.nummission}"/></p>

                <p>Il y a actuellement <strong><c:out
                        value="${objectifNb}"/> objectifs</strong>, et <strong><c:out value="${actionNb}"/>
                    actions</strong> à accomplir dans cette mission.</p>
            </div>
        </div>
        <div class="col-lg-6">

            <h2>Détails</h2>
            <div class="well">
                <c:forEach items="${mission.fixesByNummission}" var="objectif">
                    <%@include file="../objectif/panelobjectif.jsp" %>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
