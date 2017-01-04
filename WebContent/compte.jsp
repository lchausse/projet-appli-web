<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Compte</title>
</head>
<body>
	
<form action="http://www.youtube.com/results" method="get" target="_blank" >
<input name="search_query" type="text" maxlength="128" />
<select name="search_type">
<option value="">Videos</option>
<option value="search_users">Channels</option>
</select>
<input type="submit" value="Search" />
</form> 
	
<form action = "Annuaire" method = "POST">
	<%Utilisateur user = (Utilisateur)request.getAttribute("utilisateur"); %>
	<header> Utilisateur : <%=user.getPseudo() %></header>
	<iframe width="854" height="480" src="https://www.youtube.com/embed/KQOKWwhUi1k" frameborder="0" allowfullscreen></iframe>
</form>
</body>
</html>
