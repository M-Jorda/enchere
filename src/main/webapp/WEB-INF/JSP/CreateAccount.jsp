<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	
		<meta charset="UTF-8">
		<title>Créer un compte</title>
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
		
		<c:if test="${!empty listeCodesErreur}">
			<section class="alert alert-danger" role="alert">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</section>
		</c:if>



		
			<section class="inputs">
			
				<section class="left">
				
					<div>
						<label for="pseudo">Pseudo:</label>
						<input type="text" id="pseudo" name="pseudo"/>
					</div>
					
					<div>
						<label for="nom">Nom:</label>
						<input type="text" id="nom" name="nom"/>
					</div>
					
					<div>
						<label for="prenom">Prénom:</label>
						<input type="text" id="prenom" name="prenom"/>
					</div>
					
					<div>
						<label for="email">Email:</label>
						<input type="text" id="email" name="email"/>
					</div>
					
					<div>
						<label for="tel">Tel:</label>
						<input type="tel" id="tel" name="tel"/>
					</div>
				</section>
			
				<section class="right">
					<div>
						<label for="rue">Rue:</label>
						<input type="text" id="rue" name="rue"/>
					</div>
					
					<div>
						<label for="codePostal">Code Postal:</label>
						<input type="text" id="codePostal" name="codePostal"/>
					</div>
					
					<div>
						<label for="ville">Ville:</label>
						<input type="text" id="ville" name="ville"/>
					</div>
					
					<div>
						<label for="mdp">Mot de passe:</label>
						<input type="password" id="mdp" name="mdp"/>
					</div>
			
					<div>
						<label for="conf">Confirmation:</label>
						<input type="text" id="conf" name="conf"/>
					</div>
					
				</section>
				
			</section>
				
				
				
			<section class="buttons">
			
				<input type="submit" value="Créer"/>
				
				<input type="button" value="Annuler"/>
				
			</section>
			
			</form>
			
		</main>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>

	</body>
</html>