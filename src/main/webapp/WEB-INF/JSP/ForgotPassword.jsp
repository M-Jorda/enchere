<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Forgot password</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/style.css">
	</head>
	
	<body>
	
		<jsp:include page="includeJSP/H1.jsp" />
		
		<form method="post">
		
			<label for="email">Entrer votre Email</label>
			<input type="text" id="email" name="email">
			
		</form>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>
	
	</body>
</html>