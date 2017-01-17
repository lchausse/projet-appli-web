<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleConnexion.css" />
<title>Youlist - Mon Compte</title>
</head>
<body>
<% Utilisateur user = (Utilisateur) request.getAttribute("utilisateur"); %>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left-active" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
<%
if (user != null) {
	out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Mes playlists\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Deconnexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Mon compte\" />");
} else {
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Connexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Inscription\" />");
}
%>
</form>

<div id = "principal">
  <form class="login-form" action = "ServletUtilisateur" method = "POST" >
	<p class="message">Modifier les paramètres du compte </p>  
	<input type="password" value="oldPassword" name = "mdp" placeholder="ancien mot de passe" required/>
	<span class="erreurMDP">${erreurs['mdp']}</span><br />
	<input type="password" value="newPassword" name = "mdp" placeholder="nouveau mot de passe" required/>
	<input type="password" value="newPassword2" name ="mdp" placeholder="confirmer mot de passe" required/>
	<span class="erreurMDP2">${erreurs['mdp']}</span><br />
	<input type = "submit" class="button" name = "op" value = "Modifier mot de passe"/><br />
	<input type="hidden" name = "utilisateur" value = "<%=user.getPseudo() %>" />
  </form>
</div>

</body>
</html>
