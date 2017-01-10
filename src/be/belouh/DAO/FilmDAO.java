package be.belouh.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import be.belouh.bean.Film;

public class FilmDAO extends DAO<Film> {

	@Override
	public boolean create(Film obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionFilm.ajoutFilm(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getId());
			stmt.setString(3, obj.getTitre());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Film obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionFilm.suppressionFilm(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getId());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(Film obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionFilm.miseAjourFilm(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getId());
			stmt.setString(3, obj.getTitre());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Film find(Object id) {
		CallableStatement stmt = null;
		Film res = new Film();
		try {
			stmt = c.prepareCall("{? = call gestionFilm.trouverFilm2(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, (String) id);
			stmt.registerOutParameter(3, Types.VARCHAR);
			stmt.setString(3, res.getTitre());

			stmt.executeUpdate();
			if ((stmt.getInt(1) != 0)) {
				res.setId((String) id);
				res.setTitre(stmt.getString(3));
			} else
				res = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
