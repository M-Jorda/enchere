<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Liste des enchères</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style/home.css">
	</head>
	
	<body>
		<header>
			<nav>
				<jsp:include page="includeJSP/H1.jsp" />
				<jsp:include page="includeJSP/Menu.jsp" />
			</nav>
		</header>
	
		<main>
			<h2>Liste des enchères</h2>
			
			<section>
				<jsp:include page="includeJSP/Research.jsp" />
			</section>
			<section>
				<div>
					<label for="achats">Achats</label>
	    			<input type="radio" id="achats" name="ventes" value="achat" checked />
	    			
	    			<label for="opauction">enchères ouvertes</label>
	    			<input type="checkbox" id="opauction" name="opauction" checked />
	   	 			
	   	 			<label for="myauction">mes enchères</label>
	    			<input type="checkbox" id="myauction" name="myauction"/>
	    			
	    			<label for="remp">mes enchères remportées</label>
	    			<input type="checkbox" id="remp" name="remp"/>
	  			</div>
	
				 <div>
				    <input type="radio" id="ventes" name="ventes" value="dewey" />
				    <label for="ventes">Mes ventes</label>
				    
				    <label for="encours">mes ventes en cours</label>
	    			<input type="checkbox" id="encours" name="encours"/>
	    			
	    			<label for="soon">ventes non débutées</label>
	    			<input type="checkbox" id="soon" name="soon"/>
	    			
	    			<label for="closed">ventes terminées</label>
	    			<input type="checkbox" id="closed" name="closed"/>
				 </div>
			</section>
		
	
			
		</main>
		
		<footer>
			<jsp:include page="includeJSP/Footer.jsp" />
		</footer>
		
	</body>
</html>