<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.Set" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleLirePlaylist.css" />
<title>Youlist - Lire playlist</title>
</head>
<body>

<%
String pseudo = (String)request.getAttribute("utilisateur");
Playlist playlist  = (Playlist)request.getAttribute("playlist");
%>
 
<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
<%
if (pseudo != null) {
	out.println("<input class = \"left-active\" type = \"submit\" name = \"op\" value = \"Accueil\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Tendances\" />");
	out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + pseudo + "\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Mes playlists\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Deconnexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Mon compte\" />");
} else {
	out.println("<input class = \"left-active\" type = \"submit\" name = \"op\" value = \"Accueil\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Tendances\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Connexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Inscription\" />");
}
%>
</form>

<div id = "principal">

<form action = "ServletUtilisateur" method = "POST" class = "rechercher">
<input type = "search" name = "recherche" placeholder = "Rechercher playlist" />
<input type="submit" name="op" value = "Rechercher"/>
</form>

<iframe width="854" height="480" src="https://www.youtube.com/embed/<%=playlist.getMusiques(). %>" frameborder="0" allowfullscreen></iframe>

<%

%>

</div>

<!-- <script language="javascript"> -->
<!--  function IframeRefresh(IframeId) { -->
<!--  	var iframe=document.getElementById(IframeId); -->
<!--  	iframe.src="https://www.youtube.com/embed/oY6J1Lmj3ZE"; -->
<!--  } -->
<!-- </script> -->
</body>
</html>
