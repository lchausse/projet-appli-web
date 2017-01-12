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
<link rel="stylesheet" href="styleModifierPlaylist.css" />
<title>Youlist - Créer playlist</title>
</head>
<body>
<section id = "rechercheMusique">
	<form action = "ServletPlaylist" method = "POST">
		<input type = "search" name = "rechercheMusique" placeholder = "Rechercher musique">
		<input type = "submit" name = "op" value = "Rechercher musique">
	</form>
</section>
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
		</div>		
	<% } %>
<% }
} %>


<section id = "informationsPlaylist">
	<form action = "ServletUtilisateur" method = "POST">
		Saisissez le titre et les mots clefs de la playlist <br />
		<input type = "text" name = "titre" placeholder = "Titre"> <br />
		<textarea name = "motsClefs" placeholder = "Mots clefs" rows="5"></textarea> <br />
		<input type = "submit" name = "op" value = "Appliquer">
	</form>
</section>
</body>
</html>
