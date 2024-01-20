package fr.eni.encheres.bo;

public class User {

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private String rue;
	private String codePostale;
	private String ville;
	private String mdp;
	private int credit;
	private String admin;
	
	public User() {
		super();
	}
	
	public User(String _pseudo, String _nom, String _prenom, String _email, String _tel, String _rue, String _codePostale,
			String _ville, String _mdp) {
		super();
		setPseudo(_pseudo);
		setNom(_nom);
		setPrenom(_prenom);
		setEmail(_email);
		setTel(_tel);
		setRue(_rue);
		setCodePostale(_codePostale);
		setVille(_ville);
		setMdp(_mdp);
		setCredit(0);
		setAdmin("oui");
	}
	public User(String _pseudo, String _nom, String _prenom, String _email, String _tel, String _rue, String _codePostale,
			String _ville, String _mdp, int noUser) {
		super();
		setPseudo(_pseudo);
		setNom(_nom);
		setPrenom(_prenom);
		setEmail(_email);
		setTel(_tel);
		setRue(_rue);
		setCodePostale(_codePostale);
		setVille(_ville);
		setMdp(_mdp);
		setNoUtilisateur(noUser);
	}

	public User(int noUtilisateur, String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostale, String ville, String mdp, int credit, String admin) {
		super();
		setNoUtilisateur(noUtilisateur);
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTel(tel);
		setRue(rue);
		setCodePostale(codePostale);
		setVille(ville);
		setMdp(mdp);
		setCredit(0);
		setAdmin("oui");
	}
	
	public User(String rue, String codePostale, String ville) {
		super();
		setRue(rue);
		setCodePostale(codePostale);
		setVille(ville);
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
	    this.tel = tel;
	}
	
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getCodePostale() {
		return codePostale;
	}
	
	public void setCodePostale(String codePostal) {
	    this.codePostale = codePostal;
	}
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public String getAdmin() {
		return admin;
	}
	
	public void setAdmin(String string) {
		this.admin = string;
	}

	@Override
	public String toString() {
		return "User [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", tel=" + tel + ", rue=" + rue + ", codePostale=" + codePostale + ", ville="
				+ ville + ", mdp=" + mdp + ", credit=" + credit + ", admin=" + admin + "]";
	}
	
}

