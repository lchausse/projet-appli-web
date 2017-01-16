<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="principal.*, java.util.Set" %><html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleCreerPlaylist.css" />
<title>Créer une playlist</title>
</head>
<body>

<%
String pseudo = (String) request.getAttribute("utilisateur");
 %>

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
<%out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + pseudo + "\" />");%>
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left-active" type = "submit" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit" name = "op" value = "Déconnexion" />
  <input class = "right" type = "submit" name = "op" value = "Mon compte" />
</form>


<div id = principal >
	<form action = "ServletUtilisateur" method = "POST">
		Saisissez le titre et les mots clefs de la playlist <br />
		<input type = "text" name = "titre" placeholder = "Titre" required value="<c:out value="${param.titre}"/>"> <br />
		<span class="erreur">${erreurs['titre']}</span><br /><br />
		<textarea name = "motsClefs" placeholder = "Mots clefs" rows="5"></textarea> <br />
		<select name="estPublique" size="1">
			<option> Publique
			<option> Privée
		</select>
		<input type="submit" name="op" value="Creer Playlist">
		<input type="hidden" name="utilisateur" value="<%=pseudo%>" />
	</form>
</div>
</body>
</html>
