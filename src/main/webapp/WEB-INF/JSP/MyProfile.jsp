<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon Profil</title>
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
			
			<h2>Mon Profil</h2>
			
			<section class="informations profile">
				
				<jsp:include page="includeJSP/ProfilInformation.jsp" />
				
			</section>
			
			<section class="buttons">
			
				<a href="${pageContext.request.contextPath}/Connected/AlterAccount" id="butt">Modifer</a>
				<a href="${pageContext.request.contextPath}/Connected/Home" id="butt">Retour</a>
				
			</section>
						
		</main>
	
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>
	</body>
</html>