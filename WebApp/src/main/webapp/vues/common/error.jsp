<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="alert alert-danger">
            <h3>
                <i class="mdi-alert-error"></i> <strong> Une erreur est survenue : </strong>
                <c:out value="${errorMessage}"/>
            </h3>
            <c:if test="${not empty errorReturnPage}">
                <a href=<c:out value="${errorReturnPage}"/>>
                    <button type="button" class="btn btn-danger">
                        <i class="mdi-navigation-arrow-back"></i>
                    </button>
                </a>
            </c:if>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>