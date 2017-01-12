<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.Set" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleAccueil.css" />
<title>YouList</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
request.setAttribute("utilisateur", user);
 %>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left-active" type = "submit" name = "op" value = "Accueil" />
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
<%
if (user != null) {
	out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Mes playlists\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Déconnexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Mon compte\" />");
} else {
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Connexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Inscription\" />");
}
%>
</form>

<div id = "principal">
	<p>Rechercher une playlist publique</p>
	<form  action = "ServletPlaylist" method = "POST">
	  <input type="search" name="recherchePublique" placeholder="Mots clefs..."/>
	  <input type="submit" name="op" value = "Rechercher Playlist" />  
	</form>
	</p>
</div>
</body>
</html>
