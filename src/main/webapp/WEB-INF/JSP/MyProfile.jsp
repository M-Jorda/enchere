<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon Profil</title>
</head>
<body>
	<header>
		<nav>
			<jsp:include page="includeJSP/H1.jsp" />
		</nav>
	</header>

	<main>
		
		<h2>Mon Profil</h2>
		
		<section>
			
			<jsp:include page="includeJSP/ProfilInformation.jsp" />
			
		</section>
		<a href="${pageContext.request.contextPath}/Connected/AlterAccount">Modifer mon compte</a>
		
	</main>

	<footer>
		<jsp:include page="includeJSP/Footer.jsp" />
	</footer>
</body>
</html>