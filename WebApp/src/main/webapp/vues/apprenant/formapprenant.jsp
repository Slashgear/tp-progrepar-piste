<%@include file="../common/header.jsp" %>
<div class="container">
    <div class="row">
        <form class="form-horizontal" method="post" action="${action}">
            <fieldset>
                <legend>${legend}</legend>
                <div class="form-group">
                    <label for="nomApprenant" class="col-lg-2 control-label">Nom</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" name="nomApprenant" id="nomApprenant" placeholder="Nom de l'apprenant"
                               value="${nomApprenant}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="prenomApprenant" class="col-lg-2 control-label">Prénom</label>

                    <div class="col-lg-10">
                        <input type="text" class="form-control" name="prenomApprenant" id="prenomApprenant" placeholder="Prénom de l'apprenant"
                               value="${prenomApprenant}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button class="btn btn-default">Annuler</button>
                        <button type="submit" class="btn btn-primary">${confirmButtonLabel}</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="../common/footer.jsp" %>