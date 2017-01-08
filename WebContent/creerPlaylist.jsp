<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="principal.*" %><html>

<head>
<meta charset="UTF-8">
<title>Youlist - Créer playlist</title>
</head>
<body>
<section id = "informationsPlaylist">
	<form action = "ServletUtilisateur" method = "POST">
		Saisissez le titre et les mots clefs de la playlist <br />
		<input type = "text" name = "titre" placeholder = "Titre"> <br />
		<textarea name = "motsClefs" placeholder = "Mots clefs" rows="5"></textarea> <br />
		<input type = "submit" name = "op" value = "Appliquer">
	</form>
</section>
</body>
</html>
