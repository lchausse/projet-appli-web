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

<ul>
  <li class = "left"><a href="accueil.jsp">Accueil</a></li>
  <li class = "left"><a href="tendances.jsp">Tendances</a></li>
  <li class = "right"><a class="active" href="">Connexion</a></li>
  <li class = "right"><a href="creerCompte.jsp">Inscription</a></li>
</ul>

<div id = "principal">
	<span class="logo" title="Connection"><img src="images/YouList.jpeg" width="200"/></span>
	<div class="banner">
		<h1>Créer et partager vos playlists</h1>
		<h2 class="hidden-small">Connectez-vous pour accÃ©der Ã  tous les services.</h2>
	</div>
	<form  action = "ServletUtilisateur" method = "POST">
		<input type = "text" name = "pseudo" placeholder = "Pseudo" autofocus value="<c:out value="${param.pseudo}"/>" required/>
		<span class="erreur">${erreurs['pseudo']}</span><br />
		<input type = "password" name = "mdp" placeholder = "Mot de passe" required/>
		<span class="erreur">${erreurs['mdp']}</span><br />
		<input type = "submit" name = "op" value = "Connexion" /><br />
	</form>
	
</div>

</body>
</html>
