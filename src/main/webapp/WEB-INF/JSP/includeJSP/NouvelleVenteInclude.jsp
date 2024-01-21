<h2>Nouvelle vente</h2>

<form action="${pageContext.request.contextPath}/Connected/newSell" method="post">
	
	<img/>
	
	<section class="main-container">
	
		<section class="article-container">
		
			<div class="bigger">
				<label for="article">Article</label>
				<input type="text" id="article" name="article">
			</div>
			
			<div class="bigger">
				<label for="description">Description</label>
				<textarea id="description" name="description" rows="4" cols="50"></textarea>
			</div>
			
			<div>
				<label for="categorie">Catégorie</label>
    			<select id="categorie" name="categorie">
					<option value="1" >Maison</option>
					<option value="2" >Jardin</option>
					<option value="3" >Puériculture</option>
					<option value="3" >Auto/moto</option>
					<option value="4" >Informatique</option>
					<option value="5" >Cuisine</option>
					<option value="6" >Livres</option>
					<option value="7" >Musique</option>
					<option value="8" >Jeux/Jeux-vidéos</option>
					<option value="9" >Sports</option>
					<option value="10" >Vêtements</option>
					<option value="11" selected>Autres</option>
				</select>
			</div>
			
			<div>
				<label for="photo">Photo de l'article</label>
				<a href="#">UPLOADER</a>
			</div>
			
			<div>
				<label for="prix">Mise à prix</label>
				<input type="text" id="prix" name="prix">
			</div>
			
			<div>
				<label for="debutEnchere">Début de l'enchère</label>
				<input type="datetime-local" id="debutEnchere" name="debutEnchere">
			</div>
			
			<div> 
				<label for="finEnchere">Fin de l'enchère</label>
				<input type="datetime-local" id="finEnchere" name="finEnchere">
			</div>
			
		</section>
			
		<section class="adresse-container">
		
			<span class="adresse-title">Retrait</span>
		
			<div>
				<label for="rue">Rue :</label>
				<input type="text" id="rue" name="rue" value="${sessionScope.user.rue}">
			</div>
		
			<div>
				<label for="codePostal">Code postal :</label>
				<input type="text" id="codePostal" name="codePostal" value="${sessionScope.user.codePostale}">
			</div>
			
			<div>
				<label for="ville">Ville :</label>
				<input type="text" id="ville" name="ville" value="${sessionScope.user.ville}">
			</div>
			
		</section>
		
		<div class="button-container">
		
			<input type="submit" id="butt">
			<a href="${pageContext.request.contextPath}/Connected/Home" id="butt">Annuler</a>
		
		</div>
		
	
	</section>
	
</form>