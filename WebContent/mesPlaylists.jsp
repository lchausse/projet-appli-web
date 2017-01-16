<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.*" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleMesPlaylists.css" />
<title>Youlist - Mes playlists</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
Set<Playlist> playlistsUtilisateur = user.getMesPlaylists();
Set<Playlist> resultatRecherche = (Set<Playlist>)request.getAttribute("resultats");
%>
 

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
<%out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");%>
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left-active" type = "submit" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit" name = "op" value = "Deconnexion" />
  <input class = "right" type = "submit" name = "op" value = "Mon compte" />
</form>

<div id = "principal">

<%out.println(playlistsUtilisateur); %>

<header> Utilisateur : <%=user.getPseudo() %></header>


<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type = "search" name = "recherche" placeholder = "Rechercher playlist" />
<input type = "hidden" name = "utilisateur" value = "<%=user.getPseudo() %>" />
<input type="submit" name="op" value = "Rechercher"/>
</form>

<%
if (resultatRecherche == null) {
	for (Playlist p : playlistsUtilisateur) { %>
		<div class="playlist">
		  <div class="titre"><%= p.getTitre()%> </div>
			<form action = "ServletPlaylist" method = "POST">
				<input type="submit" name="op" value = "Ecouter"/>
				<input type="submit" name="op" value = "Modifier"/>
				<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>" />
				<input type="hidden" name="titrePlaylist" value="<%=p.getTitre()%>" />
				<input type="hidden" name="idPlaylist" value="<%=p.getId()%>" />
				<input type = "hidden" name = "typePlaylist" value = "privee" />
			</form>
		</div>
	<%}
} else {
	for (Playlist p : resultatRecherche) {%>
		<div class="playlist">
		  <div class="titre"><%= p.getTitre()%></div>
			<form action = "ServletPlaylist" method = "POST">
				<input type="submit" name="op" value = "Ecouter"/>
				<input type="submit" name="op" value = "Modifier"/>
				<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>" />
				<input type="hidden" name="titrePlaylist" value="<%=p.getTitre()%>" />
				<input type="hidden" name="idPlaylist" value="<%=p.getId()%>" />
				<input type = "hidden" name = "typePlaylist" value = "privee" />
			</form>
		</div>
	<%}
}
%>


<%
out.println("<a href=\"ServletUtilisateur?op=ajouterPlaylist" + user.getPseudo() + "\" >");
%>
<img id="ajouterPlaylist" src="images/ajouterPlaylist.png" alt="Logo" title = "Créer une playlist"/>
</a>
</div>

<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type="submit" name="op" value = "Nouvelle Playlist"/>
<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>" />
</form>
<!-- <script language="javascript"> -->
<!--  function IframeRefresh(IframeId) { -->
<!--  	var iframe=document.getElementById(IframeId); -->
<!--  	iframe.src="https://www.youtube.com/embed/oY6J1Lmj3ZE"; -->
<!--  } -->
<!-- </script> -->
</body>
</html>
