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
String typePlaylist = (String)request.getAttribute("typePlaylist");
Playlist playlist  = (Playlist)request.getAttribute("playlist");
int musiqueCourante = (int)request.getAttribute("musiqueCourante");
%>
 
<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
<%
if (pseudo != null) {
	if (typePlaylist.equals("publique")) {
		out.println("<input class = \"left-active\" type = \"submit\" name = \"op\" value = \"Accueil\" />");
		out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Tendances\" />");
		out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + pseudo + "\" />");
		out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Mes playlists\" />");
		out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Deconnexion\" />");
		out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Mon compte\" />");
	} else {
		out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Accueil\" />");
		out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Tendances\" />");
		out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + pseudo + "\" />");
		out.println("<input class = \"left-active\" type = \"submit\" name = \"op\" value = \"Mes playlists\" />");
		out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Deconnexion\" />");
		out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Mon compte\" />");
	}
} else {
	out.println("<input class = \"left-active\" type = \"submit\" name = \"op\" value = \"Accueil\" />");
	out.println("<input class = \"left\" type = \"submit\" name = \"op\" value = \"Tendances\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Connexion\" />");
	out.println("<input class = \"right\" type = \"submit\" name = \"op\" value = \"Inscription\" />");
}
%>
</form>

<div id = "principal">
<%if (playlist.getMusiques().size() != 0) {%>
<%Set<Musique> musiquesSet = new HashSet<Musique>();
musiquesSet.addAll(playlist.getMusiques());
List<Musique> musiques = new ArrayList<Musique>();
musiques.addAll(musiquesSet);%>
<form action = "ServletPlaylist" method = "POST">
<div class="aff">
<div class="vid">
<iframe width="854" height="480" src="https://www.youtube.com/embed/<%=musiques.get(musiqueCourante).getLien()%>" frameborder="0" allowfullscreen></iframe></div>
<input type = "hidden" name = "musiqueCourante" value = "<%= musiqueCourante %>" />
<input type = "hidden" name = "idPlaylist" value = "<%=playlist.getId() %>" />
<input type = "hidden" name = "utilisateur" value = "<%=pseudo %>" />
<input type = "hidden" name = "typePlaylist" value = "<%=typePlaylist %>" />
<%
if (musiqueCourante < musiques.size() - 1) {
%>
<input type = "submit" name = "op" value = "Musique suivante" class="suiv"/>
<%
}
if (musiqueCourante != 0) {
%>
<input type = "submit" name = "op" value = "Musique precedente" class="prec"/>
<%
}
%>
</form>



<div class="info">
<%
for (int i = 0; i < musiques.size(); i++) {
	if (i != musiqueCourante) {
		out.println(musiques.get(i).getTitre() + " - " + musiques.get(i).getAuteur());
	}
}
%>
<%
} else {
	out.println("PAS DE MUSIQUE DANS LA PLAYLIST");
}%>
</div>
</div>
</div>

</body>
</html>
