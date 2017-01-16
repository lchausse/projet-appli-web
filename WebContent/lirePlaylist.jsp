<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*, java.util.*" %>
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
int musiqueCourante = (int)request.getAttribute("musiqueCourante");
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
<form action = "ServletPlaylist" method = "POST">
<iframe width="854" height="480" src="https://www.youtube.com/embed/<%=playlist.getMusiques().get(musiqueCourante).getLien()%>" events: {'onStateChange': onPlayerStateChange} frameborder="0" allowfullscreen></iframe>
<input type = "hidden" name = "musiqueCourante" value = "<%= musiqueCourante %>" />
<input type = "hidden" name = "idPlaylist" value = "<%=playlist.getId() %>" />
<input type = "hidden" name = "utilisateur" value = "<%=pseudo %>" />
<%
if (musiqueCourante != playlist.getMusiques().size() - 1) {
%>
<input type = "submit" name = "op" value = "Musique suivante" />
<%
}
if (musiqueCourante != 0) {
%>
<input type = "submit" name = "op" value = "Musique precedente" />
<%
}
%>
</form>

<%
List<Musique> musiques = playlist.getMusiques();
for (int i = 0; i < musiques.size(); i++) {
	if (i != musiqueCourante) {
		out.println(musiques.get(i).getTitre() + " - " + musiques.get(i).getAuteur());
	}
}
%>

</div>

</body>
</html>
