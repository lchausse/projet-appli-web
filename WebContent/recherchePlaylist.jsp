<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta charset="UTF-8">
<title>Resultats Recherche</title>
</head>
<body>
	

<% Set<Playlist> resultatsRecherchePlaylist = (HashSet<Playlist>)request.getAttribute("resultatsRecherchePlaylist"); %>
<% for (Playlist pl : resultatsRecherchePlaylist) { %>
<%= pl.getNom() %> 
<% } %>

</body>
</html>
