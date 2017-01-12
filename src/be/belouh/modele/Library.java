package be.belouh.modele;

import java.util.ArrayList;

import be.belouh.DAO.DAO;
import be.belouh.DAO.FilmDAO;
import be.belouh.DAO.MediathequeFilmDAO;
import be.belouh.bean.Film;
import be.belouh.bean.Mediatheque;

public class Library {
	private DAO<Mediatheque> d;
	private DAO<Film> f;

	public Library() {
		d = new MediathequeFilmDAO();
		f = new FilmDAO();
	}

	public ArrayList<Mediatheque> getLibraries(String email) {
		@SuppressWarnings("unchecked")
		ArrayList<Mediatheque> res = (ArrayList<Mediatheque>) d.find(email);
		
		return res;
	}
	
	public void addFilm(Mediatheque m, String id, String titre){
		Film film = new Film();
		film.setId(id);
		film.setTitre(titre);
		
		f.create(film);
		
		if(!m.getListe().contains(film)){
			m.getListe().add(film);
			m.setNombre(m.getNombre() + 1);
			
			updateLibrary(m);
		}
	}
	
	public void removeFilm(Mediatheque m, String id, String titre){
		Film film = new Film();
		film.setId(id);
		film.setTitre(titre);
		
		if(m.getListe().contains(film)){
			m.getListeSuppression().add(film);
			m.getListe().remove(film);
			m.setNombre(m.getNombre() - 1);
			
			updateLibrary(m);
		}
	}
	
	public boolean addLibrary(String nom, String email){
		Mediatheque m = new Mediatheque();
		m.setNom(nom);
		m.setEmail(email);
		return d.create(m);
	}
	
	public boolean removeLibrary(String nom, String email){
		Mediatheque m = new Mediatheque();
		m.setNom(nom);
		m.setEmail(email);
		return d.delete(m);
	}
	
	public boolean updateLibrary(Mediatheque m){
		return d.update(m);
	}
}
