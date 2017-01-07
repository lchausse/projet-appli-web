package principal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Musique {
	
	@Id
	private String lien; // lien youtube de la musique
	private String titre; // titre de la musique
	private String auteur; // auteur de la musique

	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getAuteur() {
		return auteur;
	}
	
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public String getLien() {
		return lien;
	}
	
	public void setLien(String lien) {
		this.lien = lien;
	}
	
	
}
