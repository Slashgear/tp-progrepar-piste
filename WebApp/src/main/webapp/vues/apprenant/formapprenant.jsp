<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <form class="form-horizontal" method="post" action="${action}" accept-charset="UTF-8">
            <fieldset>
                <legend>${legend}</legend>
                <div class="form-group">
                    <label for="prenomApprenant" class="col-lg-2 control-label">Prénom</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" name="prenomApprenant" id="prenomApprenant"
                               placeholder="Prénom de l'apprenant"
                               value="${prenomApprenant}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="nomApprenant" class="col-lg-2 control-label">Nom</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" name="nomApprenant" id="nomApprenant" placeholder="Nom de l'apprenant"
                               value="${nomApprenant}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <a href="/apprenant" class="btn btn-default">Annuler</a>
                        <button type="submit" class="btn btn-material-indigo">${confirmButtonLabel}</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="../common/footer.jsp" %>