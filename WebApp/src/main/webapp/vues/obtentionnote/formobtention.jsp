<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <legend>Assigner les notes</legend>
    <div class="row">
        <c:if test="${not empty errMessage}">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>${errMessage}</strong>
            </div>
        </c:if>
        <div class="col-md-12">
            <div class="well">
                <legend>Résumé de l'action pour l'apprenant ${apprenant.prenomapprenant} ${apprenant.nomapprenant}</legend>
                Action : ${action.libaction}<br/>
                <c:if test="${not empty note}">
                    Note précédente : ${note}
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <form class="form-horizontal" method="post" action="/obtient/${apprenant.numapprenant}/action/${action.numaction}/jeu/${idJeu}/${formaction}" accept-charset="UTF-8">
                <fieldset>
                    <div class="form-group">
                        <label for="note" class="col-lg-4 control-label">Note attribuée</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="note" id="note"
                                   placeholder="Note sur 20"
                                   value="${note}">
                        </div>
                    </div>
                </fieldset>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <a href="/jeu/${idJeu}/apprenant/${apprenant.numapprenant}" class="btn btn-default">Annuler</a>
                        <button type="submit" class="btn btn-material-indigo">${confirmButtonLabel}</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
