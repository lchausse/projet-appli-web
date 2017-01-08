<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="accueil.css"/>
		<title>YouList</title>
	</head>

	<body>
		<div id="yl-masthead">
			<div id="yl-masthead-up"> <!-- logo && bare de recherche && 

connexion/inscription -->
				<span class="logo" title="Accueil YouList"><img 

src="images/youlist.png"/></span> <!-- logo -->
				
				<div id="yl-masthead-sign"> <!-- connexion/inscription-->
					<a href="connextion.html" class="signin">Connexion</a> 
					<a href="creerCompte.jsp" class="signup">Inscription</a>
				</div>
				
				<div id="yl-masthead-content"> <!-- barre de recherche -->
					<form action="http://www.youtube.com/results" method="get" 

target="_blank" >
						<input name="search_query" type="text" 

maxlength="128" />
						<select name="search_type">
							<option value="">Videos</option>
							<option 

value="search_users">Channels</option>
						</select>
						<input type="submit" value="Search" />
					</form> 
				</div>
			</div>

			<div id="masthead-appbar"> <!-- accueil && playlist tendance -->
				<div id="accueil-tendance">
					<a href="">Accueil</a> 
					<a href="tendance.html">Tendances</a>
				</div>
			</div>
		</div>


		<div id="page-container">
			<iframe width="560" height="315" 

src="https://www.youtube.com/embed/OU548L9B5mo" frameborder="0" allowfullscreen></iframe><br\>
			<iframe width="560" height="315" 

src="https://www.youtube.com/embed/zBs6al8-gCw" frameborder="0" allowfullscreen></iframe>
		</div>

		
	</body>
</html>
