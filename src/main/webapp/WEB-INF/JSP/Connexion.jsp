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
		<jsp:include page="includeJSP/H1.jsp" />
	
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
			
			<section>
				<label for="pseudo">Pseudo :</label>
				<input type="text" id="pseudo" name="pseudo">
			</section>
			<section>
				<label for="mdp">Mot de passe :</label>
				<input type="password" id="mdp" name="mdp">
			</section>
			<input type="submit" value="Connexion">
		</form>
		
		<a href="${pageContext.request.contextPath}/Visitor/CreateAccount">
			Créer un compte
		</a>
		<section>
			<label for=SeSouvenirDeMoi>Se souvenir de moi</label>
			<input type="checkbox" name="SeSouvenirDeMoi" id="SeSouvenirDeMoi">
		</section>
		
		<a href="${pageContext.request.contextPath}/Visitor/ForgotPassword">Mot de passe oublié</a>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>
		
	</body>
</html>

