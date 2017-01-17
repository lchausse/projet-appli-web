<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleAccueil.css" />
<title>YouList</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
request.setAttribute("utilisateur", user);
Set<Playlist> playlistsPubliques = (Set<Playlist>) request.getAttribute("playlistsPubliques");
%>

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
<section id = "recherche-playlist">
<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type="text" class="search" name = "recherche"  placeholder="Rechercher playlist..." required> </br>
<input type="submit" name="op" class="button" value="Rechercher" class = "button"/>
<% if (user != null) { %>
  	<input type = "hidden" name = "utilisateur" value = "<%=user.getPseudo() %>" />
  <% } %>
</form>
</section>

<section id = "affichagePlaylistsPubliques">
<form action = "ServletPlaylist" method = "POST">
<%
if (playlistsPubliques != null) {
	if (user != null) {
		for (Playlist p : playlistsPubliques) {
			out.println("<label>" + p.getTitre() + " " + p.getVues() + " vues</label>");
			out.println("<input type = \"hidden\" name = \"nomPlaylist\" value = \"" + p.getTitre() + "\" />");
			out.println("<input type = \"hidden\" name = \"typePlaylist\" value = \"publique\" />");
			out.println("<input type = \"hidden\" name = \"utilisateur\" value = \"" + user.getPseudo() + "\" />");
			out.println("<input type = \"hidden\" name = \"idPlaylist\" value = \"" + Integer.toString(p.getId()) + "\" />");
			out.println("<input type = \"submit\" name = \"op\" value = \"Ecouter\" class = \"bouton-ecouter\" /> <br />");
		}
	} else {
		for (Playlist p : playlistsPubliques) {
			out.println("<label>" + p.getTitre() + " " + p.getVues() + " vues</label>");
			out.println("<input type = \"hidden\" name = \"nomPlaylist\" value = \"" + p.getTitre() + "\" />");
			out.println("<input type = \"hidden\" name = \"typePlaylist\" value = \"publique\" />");
			out.println("<input type = \"hidden\" name = \"idPlaylist\" value = \"" + p.getId() + "\" />");
			out.println("<input type = \"submit\" name = \"op\" value = \"Ecouter\" class = \"bouton-ecouter\" /> <br />");
		}
	}
}
%>
</ul>

</section>

</div>

</body>
</html>
