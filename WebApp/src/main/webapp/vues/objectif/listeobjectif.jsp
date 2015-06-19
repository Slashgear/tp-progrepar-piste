<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1>Liste des objectifs</h1>
        <c:if test="${empty objectifs}">
            <div class="alert alert-warning">
                <h4><strong>Aucun objectif trouvé.</strong></h4>
            </div>
        </c:if>
        <c:forEach items="${objectifs}" var="objectif">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <a href=<c:out value="objectif/${objectif.numobjectif}"/>>
                    <div class="well">
                        #<c:out value="${objectif.numobjectif}"/> : <c:out value="${objectif.libobectif}"/>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>