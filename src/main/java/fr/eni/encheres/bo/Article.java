package fr.eni.encheres.bo;

import java.util.Date;

public class Article {

	private int noArt;
	private String nomArt;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUser;
	private int noCat;
	
	public Article() {
		super();
	}
	
	public Article(int noArt, String nomArt, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente, int noUser, int noCat) {
		super();
		setNoArt(noArt);
		setNomArt(nomArt);
		setDescription(description);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setPrixInitial(prixInitial);
		setPrixVente(prixVente);
		setNoUser(noUser);
		setNoCat(noCat);
	}

	
	
	public Article(String nomArt, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial,
			int noUser, int noCat) {
		super();
		setNomArt(nomArt);
		setDescription(description);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setPrixInitial(prixInitial);
		setNoUser(noUser);
		setNoCat(noCat);
	}

	public int getNoArt() {
		return noArt;
	}

	public void setNoArt(int _noArt) {
		this.noArt = _noArt;
	}

	public String getNomArt() {
		return nomArt;
	}

	public void setNomArt(String _nomArt) {
		this.nomArt = _nomArt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date _dateDebutEncheres) {
		this.dateDebutEncheres = _dateDebutEncheres;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date _dateFinEncheres) {
		this.dateFinEncheres = _dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(Integer _prixInitial) {
		if (_prixInitial == null) {
			this.prixInitial = 0;
		} else {
			this.prixInitial = _prixInitial;
		}
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int _prixVente) {
		this.prixVente = _prixVente;
	}

	public int getNoUser() {
		return noUser;
	}

	public void setNoUser(int _noUser) {
		this.noUser = _noUser;
	}

	public int getNoCat() {
		return noCat;
	}

	public void setNoCat(int _noCat) {
		this.noCat = _noCat;
	}

	@Override
	public String toString() {
		return "Article [noArt=" + noArt + ", nomArt=" + nomArt + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", noUser=" + noUser + ", noCat=" + noCat + "]";
	}
	
	
}
