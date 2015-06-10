<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <base href="/">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.3.0/css/roboto.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.3.0/css/material.min.css"
        rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.3.0/css/ripples.css"
        rel="stylesheet">
  <title>Simulateur de Piste</title>
</head>
<body>
<div class="header">
  <div class="navbar navbar-inverse">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Permis piste</a>
    </div>
    <div class="navbar-collapse collapse navbar-inverse-collapse">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Apprenants <b
                  class="caret"></b></a>
          <ul class="dropdown-menu">
            <li class="dropdown-header">Affichages</li>
            <li><a href="/apprenant">Affichage des apprenants</a></li>
            <li><a href="/apprenant/ajout">Ajouter un apprenant</a></li>
            <li class="divider"></li>
            <li class="dropdown-header">Statistiques</li>
            <li><a href="/apprenant/stats">Statistique globales</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Statistiques <b class="caret"></b></a>
          <ul class="dropdown-menu">
            <li class="dropdown-header">Affichages</li>
            <li><a href="/action">Affichage des actions</a></li>
            <li><a href="/jeu">Affichages des jeux</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <%@include file="headercomponents/breadcrumb.jsp" %>
</div>
