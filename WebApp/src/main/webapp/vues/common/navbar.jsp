<ul class="nav navbar-nav">
    <li class="dropdown">
        <a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Apprenants <span
                class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="apprenant">Affichage des apprenants</a></li>
            <li><a href="apprenant/ajout">Ajouter un apprenant</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Affichages<span
                class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="jeu">Jeux</a></li>
            <li><a href="mission">Missions</a></li>
            <li><a href="objectif">Objectifs</a></li>
            <li><a href="action">Actions</a></li>
        </ul>
    </li>
</ul>
<c:if test="${not empty searchURL}">
    <ul class="nav navbar-nav navbar-right">
        <li>
            <form class="navbar-form" action="${searchURL}" method="post">
                <input type="text" class="form-control col-lg-8" name="label" placeholder="${searchLabel}">
            </form>
        </li>
    </ul>
</c:if>