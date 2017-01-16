<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleConnexion.css" />
<title>Créer un compte</title>
</head>
<body>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "right-active" type = "submit" name = "op" value = "Connexion" />
  <input class = "right" type = "submit" name = "op" value = "Inscription" />
</form>




<div id = "principal">
  <form class="login-form" action = "ServletUtilisateur" method = "POST" >
	<p class="message">Modifier les paramètres du compte </p>  
	<input type="oldPassword" name = "mdp" placeholder="ancien mot de passe" required/>
	<span class="erreurMDP">${erreurs['mdp']}</span><br />
	<input type="newPassword" name = "mdp" placeholder="nouveau mot de passe" required/>
	<input type="newPassword2" name = "mdp" placeholder="confirmer mot de passe" required/>
	<span class="erreurMDP2">${erreurs['mdp']}</span><br />
	<input type = "submit" class="button" name = "op" value = "Modifier mot de passe"/><br />

  </form>
</div>

</body>
</html>