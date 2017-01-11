<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %><html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleCreerPlaylist.css" />
<title>Youlist - Créer playlist</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
 %>

<ul>
<%
out.println("<li class = \"left\"><a href=\"ServletUtilisateur?op=accueil" + user.getPseudo() + "\">Accueil</a></li>");
%>
  <li class = "left"><a href="tendances.jsp">Tendances</a></li>
  <li class = "left"><a class = "active" href = "">Mes playlists</a>
  <li class = "right"><a href="">Déconnexion</a></li>
  <li class = "right"><a href="">Mon compte</a></li>
</ul>

<div id = "principal">
	<section id = "rechercheMusique">
		<form action = "ServletPlaylist" method = "POST">
			<input type = "search" name = "rechercheMusique" placeholder = "Rechercher musique">
			<input type = "submit" name = "op" value = "Rechercher musique">
		</form>
	</section>
	<section id = "informationsPlaylist">
		<form action = "ServletUtilisateur" method = "POST">
			Saisissez le titre et les mots clefs de la playlist <br />
			<input type = "text" name = "titre" placeholder = "Titre"> <br />
			<textarea name = "motsClefs" placeholder = "Mots clefs" rows="5"></textarea> <br />
			<input type = "submit" name = "op" value = "Appliquer">
		</form>
	</section>
</div>
</body>
</html>
