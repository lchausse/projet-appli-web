<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="principal.*, java.util.Set" %><html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/styleCreerPlaylist.css" />
<title>YouList - Créer Playlist</title>
</head>
<body>


<%
Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
%>
 

<form action = "ServletUtilisateur" method = "POST" class = "barre-navigation">
  <input class = "left" type = "submit" name = "op" value = "Accueil" />
<%out.println("<input type = \"hidden\" name = \"pseudo\" value = \"" + user.getPseudo() + "\" />");%>
  <input class = "left" type = "submit" name = "op" value = "Tendances" />
  <input class = "left-active" type = "submit" name = "op" value = "Mes playlists" />
  <input class = "right" type = "submit" name = "op" value = "Deconnexion" />
  <input class = "right" type = "submit" name = "op" value = "Mon compte" />
</form>

<div id = "principal">
 <form class="login-form" action = "ServletUtilisateur" method = "POST" >
	<p class="message">Saisissez le titre et les mots clefs de la playlist </p>  
   <input type="text" name = "titre" placeholder = "titre" required value="<c:out value="${param.titre}"/>"> <br />
	<span class="erreur">${erreurs['titre']}</span><br /><br />
   <input type="text" name = "motsClefs" placeholder = "mots clefs" required /> <br />
	<select class="custom-dropdown__select custom-dropdown__select--red" name="estPublique" size="1">
		<option> Publique
		<option> Privée
	</select>
	<input type="submit" name="op" class="button" value="Creer Playlist">
	<input type="hidden" name="utilisateur" value="<%=user.getPseudo()%>"/>
 </form>
</div>
</body>
</html>