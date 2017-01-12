<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleCreerCompte.css" />
<title>YouList - Inscription</title>
</head>
<body>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "right" type = "submit" name = "op" value = "Connexion" />
  <input class = "right-active" type = "submit" name = "op" value = "Inscription" />
</form>

<div id = principal>
  <form class="login-form" action = "ServletUtilisateur" method = "POST">
  	<p class="message">Inscrivez vous pour accéder à tous nos services</p>
	<input type = "text" name = "pseudo" placeholder = "Pseudo" autofocus value="<c:out value="${param.pseudo}"/>" required/>
	<span class="erreur">${erreurs['pseudo']}</span><br /><br />
	<input type = "password" name = "mdp" placeholder = "Mot de passe" required/>
	<span class="erreur">${erreurs['mdp']}</span><br /><br />
	<input type = "password" name = "mdp2" placeholder = "Confirmer mot de passe" required/><br /><br />
	<input type = "submit" class="button" name = "op" value = "S'inscrire"/><br />
  </form>
</div>

<a href="accueil.jsp">
<!-- <img id="logoYoulist" src="images/YouList.jpeg" alt="Logo" /> -->
</a>
</div>
</body>
</html>
