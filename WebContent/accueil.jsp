<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<title>YouList</title>
</head>
<body>

<p>Déjà inscrit ?</p>
<form  action = "Projet" method = "POST">
	<input type = "text" name = "pseudo" placeholder = "Pseudo" autofocus required/> <br />
	<input type = "password" name = "mdp" placeholder = "Mot de passe" required/> <br />
	<input type = "submit" name = "op" value = "Connexion" /> <br />
</form>
	
<p>
<em><a href="creerCompte.html">Créer un compte</a></em>
</p>

<p>Rechercher une playlist publique</p>
<form  action = "Projet" method = "POST">
  <input type="text" name="recherche" placeholder="Mots clefs..."/>
  <input type="submit" name="op" value = "Rechercher Playlist" />  
</form>

</body>
</html>
