package be.belouh.DAO;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import be.belouh.bean.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {

	@Override
	public boolean create(Utilisateur obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionUtilisateur.ajoutUtilisateur(?, ?, ?, ?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getMdp());
			stmt.setString(4, obj.getNom());
			stmt.setString(5, obj.getPrenom());
			stmt.setDate(6, new Date(obj.getDateNaissance().getTimeInMillis()));

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Utilisateur obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionUtilisateur.suppressionUtilisateur(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getEmail());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(Utilisateur obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionUtilisateur.miseAjourUtilisateur(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getEmail());
			stmt.setString(3, obj.getMdp());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Utilisateur find(Object id) {
		CallableStatement stmt = null;
		Utilisateur res = new Utilisateur();
		try {
			stmt = c.prepareCall("{? = call gestionUtilisateur.trouverUtilisateur2(?, ?, ?, ?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, (String) id);
			stmt.registerOutParameter(3, Types.VARCHAR);
			stmt.setString(3, res.getMdp());
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.setString(4, res.getNom());
			stmt.registerOutParameter(5, Types.VARCHAR);
			stmt.setString(5, res.getPrenom());
			stmt.registerOutParameter(6, Types.DATE);
			stmt.setDate(6, new Date(res.getDateNaissance().getTimeInMillis()));

			stmt.executeUpdate();
			if ((stmt.getInt(1) != 0)) {
				res.setEmail((String) id);
				res.setMdp(stmt.getString(3));
				res.setNom(stmt.getString(4));
				res.setPrenom(stmt.getString(5));
				res.getDateNaissance().setTime(stmt.getDate(6));
			} else {
				res = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
