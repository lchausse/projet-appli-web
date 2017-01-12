<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.Set" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleMesPlaylists.css" />
<title>Youlist - Mes playlists</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
Set<Playlist> playlistsUtilisateur = user.getMesPlaylists();
Set<Playlist> resultatRecherche = (Set<Playlist>)request.getAttribute("resultats");
 %>
 

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
<%out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");%>
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left-active" type = "submit" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit" name = "op" value = "Déconnexion" />
  <input class = "right" type = "submit" name = "op" value = "Mon compte" />
</form>

<div id = "principal">
<!-- <form action="http://www.youtube.com/results" method="get" target="_blank" > -->
<!-- <input name="search_query" type="text" maxlength="128" /> -->
<!-- <select name="search_type"> -->
<!-- <option value="">Videos</option> -->
<!-- <option value="search_users">Channels</option> -->
<!-- </select> -->
<!-- <input type="submit" value="Search" /> -->
<!-- </form>  -->


<!-- <form action = "ServletUtilisateur" method = "POST"> -->
<header> Utilisateur : <%=user.getPseudo() %></header>

<!-- 	<iframe id="ibra" width="854" height="480" src="https://www.youtube.com/embed/W_qih8UTeAQ?autoplay=1" frameborder="0" allowfullscreen></iframe> -->
<!-- 	<input value="Next" type = "button" onclick="IframeRefresh('ibra');" /> -->
<!-- </form> -->

<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type = "search" name = "recherche" placeholder = "Rechercher playlist" />
<input type="submit" name="op" value = "Rechercher"/>
</form>

<%
for (Playlist p : playlistsUtilisateur) {
	out.println(p.getNom() + "<br />");
}
for (Playlist p : resultatRecherche) {
	out.println(p.getNom() + "<br />");
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
