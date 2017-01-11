<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styleAccueil.css" />
<title>YouList</title>
</head>
<body>
<div id="masthead-positioner">
	<div id="yl-masthead-container" class="clearfix yl-base-gutter"> <!-- logo && barre de recherche && connexion -->
		<div id="yl-masthead"> <!-- logo -->
			<span class="logo" title="Accueil YouList"></span>²
		</div>
				
		<div id="yl-masthead-signin"> <!-- connexion -->
			<button class="yl-signin-button" type="button" onclick=";window.location.href=this.getAttribute('href');return false;" href="creerCompte.jsp?op=creerCompte" role="link">
				<span class="yl-button-content">Connexion</span>
			</button>
		</div>
				
		<div id="yl-masthead-content"> <!-- barre de recherche -->
		</div>
	</div>
	<div id="masthead-appbar-container" class="clearfix"> <!-- playlist tendance -->
	</div>
</div>
<div id="masthead-positioner-height-offset"></div> <!-- positionnement de masthead-positioner -->

<p>Déjà  inscrit ? </p>
<form  action = "ServletUtilisateur" method = "POST">
	<input type = "text" name = "pseudo" placeholder = "Pseudo" autofocus value="<c:out value="${param.pseudo}"/>" required/>
	<span class="erreur">${erreurs['pseudo']}</span><br />
	<input type = "password" name = "mdp" placeholder = "Mot de passe" required/>
	<span class="erreur">${erreurs['mdp']}</span><br />
	<input type = "submit" name = "op" value = "Connexion" /><br />
</form>
	
<p>
<em><a href="creerCompte.jsp">Créer un compte</a></em>
</p>

<p>Rechercher une playlist publique</p>
<form  action = "ServletPlaylist" method = "POST">
  <input type="search" name="recherchePublique" placeholder="Mots clefs..."/>
  <input type="submit" name="op" value = "Rechercher Playlist" />  
</form>
</p>
</body>
</html>
