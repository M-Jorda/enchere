<form action="${pageContext.request.contextPath}/ServletResearch" method="post">
	<section>
	
		<div class="filtre form-paire">
			<label for="filtre">Filtres</label>
			<input type="text" id="filtre" name="filtre" placeholder="Le nom de l'article contient">
		</div>
		
		<div class="categorie form-paire">
			<label for="categorie">Categorie :</label>
			<select>
				<option id="categorie" value="0" selected>Toutes</option>
			</select>
		</div>
		
	</section>
	
	<input id="butt" type="submit" value="Rechercher">
</form>