<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
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
        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th>#</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Action</th>
            </thead>
            <tbody>

            <c:forEach items="${listeApprenants}" var="apprenant">
                <tr>
                    <td>${apprenant.numapprenant}</td>
                    <td>${apprenant.nomapprenant}</td>
                    <td>${apprenant.prenomapprenant}</td>
                    <td>
                        <a href="/apprenant/modifier/${apprenant.numapprenant}"><i class="mdi-content-create"></i></a>
                        <a href="/apprenant/suppr/${apprenant.numapprenant}"><i class="mdi-content-clear"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
