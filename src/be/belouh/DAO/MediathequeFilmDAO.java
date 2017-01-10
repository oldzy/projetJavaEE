package be.belouh.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import be.belouh.bean.Film;
import be.belouh.bean.Mediatheque;
import oracle.jdbc.internal.OracleTypes;

public class MediathequeFilmDAO extends DAO<Mediatheque> {

	@Override
	public boolean create(Mediatheque obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionMediathequeFilm.ajoutMediatheque(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getNom());
			stmt.setString(3, obj.getEmail());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Mediatheque obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionMediathequeFilm.suppressionMediatheque(?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getNom());
			stmt.setString(3, obj.getEmail());

			stmt.executeUpdate();

			res = (stmt.getInt(1) != 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(Mediatheque obj) {
		CallableStatement stmt = null;
		boolean res = false;
		try {
			stmt = c.prepareCall("{? = call gestionMediathequeFilm.miseAjourMediatheque(?, ?, ?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setString(2, obj.getNom());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, (obj.getNouveauNom().equals("") ? obj.getNom() : obj.getNouveauNom()));

			stmt.executeUpdate();

			if (stmt.getInt(1) != 0) {
				res = true;
				if (!obj.getNouveauNom().equals("")) {
					obj.setNom(obj.getNouveauNom());
					obj.setNouveauNom("");
				}

				Iterator<Film> it = obj.getListe().iterator();
				while (it.hasNext()) {
					stmt = c.prepareCall("{? = call gestionMediathequeFilm.ajout(?, ?, ?)}");
					stmt.registerOutParameter(1, Types.INTEGER);
					stmt.setString(2, it.next().getId());
					stmt.setString(3, obj.getNom());
					stmt.setString(4, obj.getEmail());

					stmt.executeUpdate();
				}
				it = obj.getListeSuppression().iterator();
				while (it.hasNext()) {
					stmt = c.prepareCall("{? = call gestionMediathequeFilm.suppression(?, ?, ?)}");
					stmt.registerOutParameter(1, Types.INTEGER);
					stmt.setString(2, it.next().getId());
					stmt.setString(3, obj.getNom());
					stmt.setString(4, obj.getEmail());

					stmt.executeUpdate();
				}

				obj.setListeSuppression(new ArrayList<Film>());
				obj.setNombre(obj.getListe().size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Mediatheque> find(Object id) {
		CallableStatement stmt = null;
		ArrayList<String> listeMed;
		ArrayList<String> listeFilm;
		ArrayList<Mediatheque> liste = new ArrayList<Mediatheque>();
		DAO<Film> d = new FilmDAO();
		try {
			stmt = c.prepareCall("{? = call gestionMediathequeFilm.trouverMediatheque2(?)}");
			stmt.registerOutParameter(1, OracleTypes.ARRAY, "LISTESTRING");
			stmt.setString(2, (String) id);

			stmt.executeUpdate();

			listeMed = new ArrayList<String>(Arrays.asList((String[]) stmt.getArray(1).getArray()));
			for (String nom : listeMed) {
				Mediatheque m = new Mediatheque();
				m.setEmail((String) id);
				m.setNom(nom);

				stmt = c.prepareCall("{? = call gestionMediathequeFilm.recupererFilm(?, ?)}");
				stmt.registerOutParameter(1, OracleTypes.ARRAY, "LISTESTRING");
				stmt.setString(2, m.getNom());
				stmt.setString(3, m.getEmail());

				stmt.executeUpdate();

				listeFilm = new ArrayList<String>(Arrays.asList((String[]) stmt.getArray(1).getArray()));

				for (String film : listeFilm) {
					m.getListe().add((Film) d.find(film));
				}

				m.setNombre(m.getListe().size());

				liste.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
