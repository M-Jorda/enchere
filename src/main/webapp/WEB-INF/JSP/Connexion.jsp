<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Connexion</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/style.css">
	</head>
	
	<body>
		<header>
			<jsp:include page="includeJSP/H1.jsp" />
		</header>
		
		<main class="main">
		
			<form method="post" class="co">
			
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
				
				<div class="conn">
					<label for="pseudo">Pseudo :</label>
					<input type="text" id="pseudo" name="pseudo">
				</div>
				
				<div class="conn">
					<label for="mdp">Mot de passe :</label>
					<input type="password" id="mdp" name="mdp">
				</div>
				
				<div class="remember">
					<label for=SeSouvenirDeMoi>Se souvenir de moi</label>
					<input type="checkbox" name="SeSouvenirDeMoi" id="SeSouvenirDeMoi">
				</div>
				
				
				<section class="conn">
					<input type="submit" value="Connexion" id="butt">
					<a href="${pageContext.request.contextPath}/Visitor/ForgotPassword" id="butt">forgot pass</a>
				</section>
				
			</form>
			
			
			
			<a href="${pageContext.request.contextPath}/Visitor/CreateAccount" id="butt">Créer un compte</a>
			
		</main>
			
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>
		
	</body>
</html>

