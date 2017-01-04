package principal;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	private String nom;
	private List<Musique> musiques;
	private List<Utilisateur> utilisateurs;
	private List<String> motsClefs;
	
	public Playlist() {
		this.musiques = new ArrayList<Musique>();
		this.utilisateurs = new ArrayList<Utilisateur>();
		this.motsClefs = new ArrayList<String>();
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

	public List<String> getMotsClefs() {
		return this.motsClefs;	
	}
	
	public void setMusiques(List<Musique> musiques) {
		this.musiques = musiques;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public void setMotsClefs(List<String> motsClefs) {
		this.motsClefs = motsClefs;	
	}
}
