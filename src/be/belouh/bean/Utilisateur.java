package be.belouh.bean;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email = "";
	private String mdp = "";
	private String nom = "";
	private String prenom = "";
	private GregorianCalendar dateNaissance = new GregorianCalendar();

	public Utilisateur() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
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

	public GregorianCalendar getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(GregorianCalendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
}
