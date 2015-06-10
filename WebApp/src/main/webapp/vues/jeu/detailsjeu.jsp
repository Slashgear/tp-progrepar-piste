<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${jeu.libellejeu}"/></h1>

        <h2>Résumé</h2>

        <div class="col-xs-12">
            <div class="well">
                <p>Identifiant #<c:out value="${jeu.numjeu}"/></p>
            </div>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp" %>