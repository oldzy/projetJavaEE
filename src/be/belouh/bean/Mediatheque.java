package be.belouh.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Mediatheque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom = "";
	private String email = "";
	private int nombre = 0;
	private String nouveauNom = "";
	private ArrayList<Film> liste = new ArrayList<Film>();
	private ArrayList<Film> listeSuppression = new ArrayList<Film>();

	public Mediatheque() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public String getNouveauNom() {
		return nouveauNom;
	}

	public void setNouveauNom(String nouveauNom) {
		this.nouveauNom = nouveauNom;
	}

	public ArrayList<Film> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Film> liste) {
		this.liste = liste;
	}

	public ArrayList<Film> getListeSuppression() {
		return listeSuppression;
	}

	public void setListeSuppression(ArrayList<Film> listeSuppression) {
		this.listeSuppression = listeSuppression;
	}
}
