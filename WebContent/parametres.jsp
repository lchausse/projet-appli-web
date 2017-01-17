<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleParametres.css" />
<title>Youlist - Mon Compte</title>
</head>
<body>
<% Utilisateur user = (Utilisateur) request.getAttribute("utilisateur"); %>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left" type = "submit\" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit\" name = "op" value = "Deconnexion" />
  <input class = "right-active" type = "submit" name = "op" value = "Mon compte" />
<%
	out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");
%>
</form>

<div id = "principal">
  <form class="login-form" action = "ServletUtilisateur" method = "POST" >
	<p class="message">Modifier les paramètres du compte </p>  
	<input type="password" name = "oldPassword" placeholder="ancien mot de passe" required/>
	<span class="erreurMDP">${erreurs['oldMdp']}</span><br />
	<input type="password" name = "newPassword" placeholder="nouveau mot de passe" required/>
	<input type="password" name ="newPassword2" placeholder="confirmer mot de passe" required/>
	<span class="erreurMDP2">${erreurs['newMdp']}</span><br />
	<input type = "submit" class="button" name = "op" value = "Modifier mot de passe"/><br />
	<input type="hidden" name = "utilisateur" value = "<%=user.getPseudo() %>" />
  </form>
</div>

</body>
</html>