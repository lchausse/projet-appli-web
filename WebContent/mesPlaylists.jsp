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

<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type="text" class="search" name = "recherche"  placeholder="Rechercher playlist..." required>
<input type="hidden" name = "utilisateur" value = "<%=user.getPseudo() %>" />
<input type="submit" name="op" class="button" value="Rechercher" class = "button"/>
</form>

</div>

<br/>
<br/>
<div class="min">
<%
if (resultatRecherche == null) {
	for (Playlist p : playlistsUtilisateur) { %>
		<div class = "aff">
		  <div class="titre"><%= p.getTitre()%> </div>
			<form action = "ServletPlaylist" method = "POST" class = "opt">
					<input type="submit" name="op" class="button" value="Ecouter" class = "button">
					<input type="submit" name="op" class="button" value="Modifier" class = "button">
					<input type = "text" name = "pseudoPartage" placeholder = "Pseudo utilisateur" />
					<input type = "submit" name = "op" value = "Partager" class = "button"/>
					<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>" />
					<input type="hidden" name="titrePlaylist" value="<%=p.getTitre()%>" />
					<input type="hidden" name="idPlaylist" value="<%=p.getId()%>" />
					<input type = "hidden" name = "typePlaylist" value = "privee" />		
			</form>
		</div>
	<%}
} else {
	for (Playlist p : resultatRecherche) {%>
		<div class = "aff">
		  <div class="titre"><%= p.getTitre()%></div>
			<form action = "ServletPlaylist" method = "POST" class = "opt">
				<input type="submit" name="op" class="button" value="Ecouter">
				<input type="submit" name="op" class="button" value="Modifier">
				<input type = "text" name = "pseudoPartage" placeholder = "Pseudo utilisateur" />
				<input type = "submit" name = "op" value = "Partager" class = "button"/>
				<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>" />
				<input type="hidden" name="titrePlaylist" value="<%=p.getTitre()%>" />
				<input type="hidden" name="idPlaylist" value="<%=p.getId()%>" />
				<input type = "hidden" name = "typePlaylist" value = "privee" />
			</form>
		<div class = "aff">
	<%}
}
%>

</div>
</div>

<form action = "ServletUtilisateur" method = "POST" >
<input type="image" name="op" value = "Nouvelle Playlist" src="images/ajouterPlaylist.png" id = "newpl"/>
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
