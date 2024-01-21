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
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/profile.css">
		
	</head>
	
	<body>
	
	<header>
		<nav>
			<jsp:include page="includeJSP/H1.jsp" />
		</nav>
	</header>
	
	<main>
		<h2>Mon profil</h2>
			
		<form method="post" class="form">
		
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
			
				<section class="left informations">
				
					<div class="pseudo">
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
			
				<section class="right informations">
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
						<label for="mdp">Mot de passe:</label>
						<input type="password" id="mdp" name="mdp" minlength="5" maxlength="30"/>
					</div>
			
					<div>
						<label for="conf">Confirmation:</label>
						<input type="text" id="conf" name="conf" minlength="5" maxlength="30"/>
					</div>
					
				</section>
				
			</section>
				
				
				
			<section class="buttons">
			
				<input type="submit" value="Créer" id="butt" class="reg"/>
				<a href="${pageContext.request.contextPath}/Visitor/Home" id="butt" class="back">Retour</a>
				
			</section>
			
			</form>
			
		</main>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>

	</body>
</html>