<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.api.services.youtube.model.SearchResult" %>
<%@ page import="com.google.api.services.youtube.model.ResourceId" %>
<%@ page import="com.google.api.services.youtube.model.Thumbnail" %>
<%@ page import="com.google.api.services.youtube.YouTube" %>
<%@ page import="com.google.api.services.youtube.model.Video" %>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleModifierPlaylist.css" />
<title>Youlist - Mes playlists</title>
</head>

<body>

<%
String pseudo = (String) request.getAttribute("utilisateur");
Playlist pl = (Playlist) request.getAttribute("playlist");
%>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
<%out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + pseudo + "\" />");%>
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left-active" type = "submit" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit" name = "op" value = "Deconnexion" />
  <input class = "right" type = "submit" name = "op" value = "Mon compte" />
</form>

<div id = principal>
	<form action = "ServletPlaylist" method = "POST">
		<input type ="search" name ="rechercheMusique" placeholder ="Rechercher musique">
		<input type="hidden" name ="titrePlaylist" value=<%=pl.getTitre()%>>
		<input type="hidden" name ="utilisateur" value=<%=pseudo %>>
		<input type ="submit" name ="op" value ="Rechercher Musique">
	</form>
</div>

<% List<SearchResult> searchResultList = (List<SearchResult>) request.getAttribute("resultatsRechercheMusique"); %>
<% if (searchResultList != null) { %>
<% for (SearchResult sr : searchResultList) { %>
	<% ResourceId rId = sr.getId();  %>
	<% if (rId.getKind().equals("youtube#video")) { %>
		<% Thumbnail thumbnail = sr.getSnippet().getThumbnails().getDefault();%>
		<% String id = rId.getVideoId(); %>
		<% String title = sr.getSnippet().getTitle(); %>
		<% title = (Character.toUpperCase(title.charAt(0))) + title.toLowerCase().substring(1,Math.min(45, title.length())) + (Math.min(45, title.length()) == 45? "..." : "");%>
		<div class="img" href=<%= "https://www.youtube.com/watch?v=" + id %>>
		  <a target="_blank" >
		    <img src=<%= "http://i.ytimg.com/vi/" + id + "/mqdefault.jpg" %> alt=<%= title %> width="300" height="200">
		  </a>
		  <div class="desc"><%= title %></div>
		  <form action = "ServletPlaylist" method = "POST">
		  		<input type="text" name="titre" placeholder="Titre">
		  		<input type="text" name="auteur" placeholder="Auteur">
				<input type="hidden" name="titrePlaylist" value=<%=pl.getTitre()%>>
				<input type="hidden" name="utilisateur" value=<%=pseudo%>>
				<input type="hidden" name="lien" value=<%="https://www.youtube.com/watch?v=" + id%>>
		 		<input type="submit" name="op" value="Ajouter Musique">
		  </form>
		</div>
		
	<% } %>
<% }
}%>

<div id = principal>
<% if (pl != null) {
	Set<Musique> musiques = pl.getMusiques();
	  for (Musique m : musiques) {%>
		  <div class="desc"> <%=m.getTitre() + " - " + m.getAuteur() %></div>
<%}}%>
</div>

</body>
</html>
