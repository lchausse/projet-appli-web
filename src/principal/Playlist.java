package principal;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	private String nom;
	private List<Musique> musiques;
	private List<Utilisateur> utilisateurs;
	
	public Playlist() {
		this.musiques = new ArrayList<Musique>();
		this.utilisateurs = new ArrayList<Utilisateur>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Musique> getMusiques() {
		return musiques;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setMusiques(List<Musique> musiques) {
		this.musiques = musiques;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
}
