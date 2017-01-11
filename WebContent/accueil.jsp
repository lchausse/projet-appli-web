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
 %>

<ul>
  <li class = "left"><a class="active" href="">Accueil</a></li>
  <li class = "left"><a href="tendances.jsp">Tendances</a></li>
<%
if (user != null) {
	out.println("<li class = \"left\"><a href = \"ServletUtilisateur?op=mesPlaylists" + user.getPseudo() + "\">Mes playlists</a>");
	out.println("<li class = \"right\"><a href = \"\">Déconnexion</a>");
	out.println("<li class = \"right\"><a href = \"\">Mon compte</a>");
} else {
	out.println("<li class = \"right\"><a href = \"connexion.jsp\">Connexion</a>");
	out.println("<li class = \"right\"><a href = \"creerCompte.jsp\">Inscription</a>");
}
%>
</ul>

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
