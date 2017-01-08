<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styleCompte.css" />
<title>Compte</title>
</head>
<body>
	

<!-- <form action="http://www.youtube.com/results" method="get" target="_blank" > -->
<!-- <input name="search_query" type="text" maxlength="128" /> -->
<!-- <select name="search_type"> -->
<!-- <option value="">Videos</option> -->
<!-- <option value="search_users">Channels</option> -->
<!-- </select> -->
<!-- <input type="submit" value="Search" /> -->
<!-- </form>  -->
<%Utilisateur user = (Utilisateur)request.getAttribute("utilisateur");%>

<!-- <form action = "ServletUtilisateur" method = "POST"> -->
<header> Utilisateur : <%=user.getPseudo() %></header>
<!-- 	<iframe id="ibra" width="854" height="480" src="https://www.youtube.com/embed/W_qih8UTeAQ?autoplay=1" frameborder="0" allowfullscreen></iframe> -->
<!-- 	<input value="Next" type = "button" onclick="IframeRefresh('ibra');" /> -->
<!-- </form> -->

<form action = "ServletPlaylist" method = "POST" class = "rechercher">
<input type = "search" name = "barreRecherche" placeholder = "Rechercher playlist" />
<input type="submit" name="op" value = "Rechercher"/>
</form>

<a href="creerPlaylist.jsp" >
<img id="ajouterPlaylist" src="images/ajouterPlaylist.png" alt="Logo" />
</a>

<!-- <script language="javascript"> -->
<!--  function IframeRefresh(IframeId) { -->
<!--  	var iframe=document.getElementById(IframeId); -->
<!--  	iframe.src="https://www.youtube.com/embed/oY6J1Lmj3ZE"; -->
<!--  } -->
<!-- </script> -->
</body>
</html>
