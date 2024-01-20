<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des enchères</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/style.css">
</head>
<body>
	<header>
		<nav>
			<jsp:include page="includeJSP/H1.jsp" />
			<jsp:include page="includeJSP/Reg.jsp" />
		</nav>
	</header>

	<main>
		<h2>Liste des enchères</h2>
		
		<section>
			<jsp:include page="includeJSP/Research.jsp" />
		</section>

		
	</main>
	
	<footer>
		<jsp:include page="includeJSP/Footer.jsp" />
	</footer>
</body>
</html>