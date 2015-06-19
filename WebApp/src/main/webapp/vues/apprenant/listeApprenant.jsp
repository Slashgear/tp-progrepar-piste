<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/floatingactionbutton.css"/>" rel="stylesheet"/>

<div class="container">
    <div class="row">
        <h1>Liste des apprenants<c:if test="${empty listeApprenants}"> (vide)</c:if></h1>
        <c:if test="${not empty isDeleted}">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>${isDeleted}</strong>
            </div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>${success}</strong>
            </div>
        </c:if>
        <c:forEach items="${listeApprenants}" var="apprenant">
            <div class="col-md-4 col-sm-6">
                <div class="well infobox">
                    <h2><c:out value="${apprenant.prenomapprenant}"/> <c:out value="${apprenant.nomapprenant}"/></h2>

                    <div class="btn-group btn-group-justified">
                        <a href="/apprenant/${apprenant.numapprenant}"
                           class="btn btn-sm btn-flat btn-info btn-material-indigo"
                           title="Détails"><i
                                class="mdi-content-add-circle-outline"></i></a>
                        <a href="/apprenant/modifier/${apprenant.numapprenant}" class="btn btn-sm btn-flat btn-primary"
                           title="Modifier"><i
                                class="mdi-content-create"></i></a>
                        <a href="/apprenant/suppr/${apprenant.numapprenant}" class="btn btn-sm  btn-flat btn-danger"
                           title="Supprimer"><i
                                class="mdi-content-clear"></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<footer class="footer">
    <div class="container">
        <div class="pull-right">
            <%@include file="../common/floatingactionbutton.jsp" %>
        </div>
    </div>
</footer>
<%@include file="../common/footer.jsp" %>
