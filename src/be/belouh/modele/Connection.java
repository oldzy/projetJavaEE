package be.belouh.modele;

import java.util.HashMap;
import java.util.Map;

import be.belouh.DAO.DAO;
import be.belouh.DAO.UtilisateurDAO;
import be.belouh.bean.Utilisateur;

public final class Connection {
	private static final String CHAMP_EMAIL = "e_mail";
	private static final String CHAMP_PASS = "password";

	private boolean resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public boolean getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur(String email, String mdp) {
		email = getValeur(email);
		mdp = getValeur(mdp);
		Utilisateur u = null;
		DAO<Utilisateur> d = new UtilisateurDAO();

		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
			resultat = false;
		}

		try {
			validationMotsDePasse(mdp);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
			resultat = false;
		}

		if (erreurs.isEmpty()) {
			u = (Utilisateur) d.find(email);
			if (u == null) {
				setErreur(CHAMP_EMAIL, "Wrong combination.");
				resultat = false;
			} else {
				if (u.getMdp().equals(mdp)) {
					resultat = true;
				} else {
					setErreur(CHAMP_PASS, "Wrong combination.");
					resultat = false;
				}
			}
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

	private void validationMotsDePasse(String mdp) throws Exception {
		if (mdp == null || mdp.length() == 0) {
			throw new Exception("Enter a password.");
		}
	}

	private static String getValeur(String champ) {
		if (champ == null || champ.trim().length() == 0) {
			return null;
		} else {
			return champ.trim();
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}
}
