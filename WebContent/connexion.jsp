<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleConnexion.css" />
<title>YouList - Connexion</title>
</head>
<body>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "right-active" type = "submit" name = "op" value = "Connexion" 

/>
  <input class = "right" type = "submit" name = "op" value = "Inscription" />
</form>


<div id = "principal">
  <form class="login-form" action = "ServletUtilisateur" method = "POST">
	<p class="message">Créer un compte pour accéder à tous nos services </p> 

 
    <input type="text" name = "pseudo" placeholder="identifiant" autofocus 

value="<c:out value="${param.pseudo}"/>" required/>
    <span class="erreur">${erreurs['pseudo']}</span><br />
    <input type="password" name = "mdp" placeholder="mot de passe" required/>
    <span class="erreur">${erreurs['mdp']}</span><br />
    <input type = "submit" class="button" name = "op" value = "Se connecter"/><br />

  </form>
</div>

</body>
</html>
