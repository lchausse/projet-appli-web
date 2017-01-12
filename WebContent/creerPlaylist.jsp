<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer une playlist</title>
</head>
<body>

<%
Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");
Set<Playlist> playlistsUtilisateur = user.getMesPlaylists();
Set<Playlist> resultatRecherche = (Set<Playlist>)request.getAttribute("resultats");
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

	<form action = "ServletUtilisateur" method = "POST">
		Saisissez le titre et les mots clefs de la playlist <br />
		<input type = "text" name = "titre" placeholder = "Titre"> <br />
		<textarea name = "motsClefs" placeholder = "Mots clefs" rows="5"></textarea> <br />
		<select name="estPublique" size="1">
			<option> Publique
			<option> Privée
		</select>

		<input type = "submit" name = "op" value = "Créer Playlist">
		<input type="hidden" name="createur" id="createur" value="<%=user;%>" />
	</form>
</body>
</html>
