package be.belouh.bean;

import java.io.Serializable;

public class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id = "";
	private String titre = "";

	public Film() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public boolean equals(Object obj) {
		Film f;
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		} else {
			f = (Film) obj;
			if (f.getId().equals(getId()) && f.getTitre().equals(getTitre())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getId().hashCode() + this.getTitre().hashCode();
	}
}
