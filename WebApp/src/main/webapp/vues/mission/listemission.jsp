<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <c:forEach items="${missions}" var="mission">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <a href=<c:out value="mission/${mission.nummission}"/>>
                    <div class="well">
                        #<c:out value="${mission.nummission}"/> : <c:out value="${mission.libmission}"/>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>