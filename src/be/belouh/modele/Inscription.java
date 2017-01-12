package be.belouh.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import be.belouh.DAO.DAO;
import be.belouh.DAO.UtilisateurDAO;
import be.belouh.bean.Utilisateur;

public class Inscription {
	private static final String CHAMP_LAST_NAME = "last_name";
	private static final String CHAMP_FIRST_NAME = "first_name";
	private static final String CHAMP_EMAIL = "e_mail";
	private static final String CHAMP_DATE = "date";
	private static final String CHAMP_PASS = "password";

	private boolean resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public boolean getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur inscrireUtilisateur(String nom, String prenom, String email, String dateS, String mdp, String conf) {
		DAO<Utilisateur> d = new UtilisateurDAO();
		Utilisateur u = new Utilisateur();

		nom = getValeur(nom);
		prenom = getValeur(prenom);
		email = getValeur(email);
		dateS = getValeur(dateS);
		GregorianCalendar date = new GregorianCalendar();
		mdp = getValeur(mdp);
		conf = getValeur(conf);

		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		u.setEmail(email);

		try {
			validationMotsDePasse(mdp, conf);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		u.setMdp(mdp);

		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_LAST_NAME, e.getMessage());
		}
		u.setNom(nom);

		try {
			validationPrenom(prenom);
		} catch (Exception e) {
			setErreur(CHAMP_FIRST_NAME, e.getMessage());
		}
		u.setPrenom(prenom);

		try {
			validationDate(dateS, date);
		} catch (Exception e) {
			setErreur(CHAMP_DATE, e.getMessage());
		}
		u.setDateNaissance(date);

		if (erreurs.isEmpty()) {
			if(d.create(u))
				resultat = true;
			else{
				setErreur("exist", "This email address is already used.");
				resultat = false;
			}
		} else {
			resultat = false;
		}

		return u;
	}

	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Enter a valid email address.");
			}
		} else {
			throw new Exception("Enter an email address.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("The passwords are differents");
			}
		} else {
			throw new Exception("Enter and confirm a password");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom == null || nom.length() == 0) {
			throw new Exception("Enter a last name");
		}
	}

	private void validationPrenom(String prenom) throws Exception {
		if (prenom == null || prenom.length() == 0) {
			throw new Exception("Enter a first name");
		}
	}

	private void validationDate(String dateS, GregorianCalendar dateG) throws Exception {
		if (dateS != null)
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateS);
				dateG.setTime(date);
			} catch (ParseException e) {
				throw new Exception("Enter a valid birthdate");
			}
		else
			throw new Exception("Enter a valid birthdate");
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeur(String champ) {
		if (champ == null || champ.trim().length() == 0) {
			return null;
		} else {
			return champ.trim();
		}
	}
}
