package principal;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Musique {
	
	@Id
	private String lien; // lien youtube de la musique
	private String titre; // titre de la musique
	private String auteur; // auteur de la musique
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Playlist> playlists; // musique de la playlist
	
	public Musique() {
		this.playlists = new HashSet<Playlist>();
	}

	
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
	
	public void addPlaylist(Playlist pl) {
		playlists.add(pl);
	}
	
	
}
