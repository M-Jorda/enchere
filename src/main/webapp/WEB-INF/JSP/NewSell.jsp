<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.bo.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style/newVente.css">
</head>
<body>
	
	<header>
		<nav>
			<jsp:include page="includeJSP/H1.jsp" />
		</nav>
	</header>

	<main>
		<jsp:include page="includeJSP/NouvelleVenteInclude.jsp" />
	</main>

	<footer>
		<jsp:include page="includeJSP/Footer.jsp" />
	</footer>

</body>
</html>