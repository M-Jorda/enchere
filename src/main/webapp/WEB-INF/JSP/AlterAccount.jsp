<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	
		<meta charset="UTF-8">
		<title>Modifier mon compte</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/style.css">
		
	</head>
	
	<body>
	
	<header>
		<nav>
			<jsp:include page="includeJSP/H1.jsp" />
		</nav>
	</header>
	
	<main>
		<h2>Mon profil</h2>
			
		<form method="post">
		
			<section class="inputs">
			
				<section class="left">
				
					<div>
						<label for="pseudo">Pseudo:</label>
						<input type="text" id="pseudo" name="pseudo" maxlength="30"/>
					</div>
					
					<div>
						<label for="nom">Nom:</label>
						<input type="text" id="nom" name="nom" maxlength="30"/>
					</div>
					
					<div>
						<label for="prenom">Prénom:</label>
						<input type="text" id="prenom" name="prenom" maxlength="30"/>
					</div>
					
					<div>
						<label for="email">Email:</label>
						<input type="email" id="email" name="email" maxlength="50"/>
					</div>
					
					<div>
						<label for="tel">Tel:</label>
						<input type="tel" id="tel" name="tel" maxlength="15"/>
					</div>
				</section>
			
				<section class="right">
					<div>
						<label for="rue">Rue:</label>
						<input type="text" id="rue" name="rue" maxlength="30"/>
					</div>
					
					<div>
						<label for="codePostal">Code Postal:</label>
						<input type="text" id="codePostal" name="codePostal" maxlength="10"/>
					</div>
					
					<div>
						<label for="ville">Ville:</label>
						<input type="text" id="ville" name="ville" maxlength="50"/>
					</div>
					
					<div>
						<label for="mdp">Mot de passe actuel:</label>
						<input type="password" id="mdp" name="mdp" minlength="5" maxlength="30"/>
					</div>
					
					<div>
						<label for="newMdp">Nouveau mot de passe:</label>
						<input type="password" id="newMdp" name="newMdp" minlength="5" maxlength="30"/>
					</div>
			
					<div>
						<label for="conf">Confirmation:</label>
						<input type="text" id="conf" name="conf" minlength="5" maxlength="30"/>
					</div>
					
				</section>
				
				<div>Crédit :</div>
				
			</section>
				
				
				
			<section class="buttons">
			
				<input type="submit" value="Enregistrer"/>
				
			</section>
			
		</form>
		<form action="${pageContext.request.contextPath}/Connected/DeleteAccount" method="post">
			<input type="submit" value="Supprimer mon compte"/>
		</form>
			
		</main>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>

	</body>
</html>