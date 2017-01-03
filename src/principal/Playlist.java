package principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Playlist {
	
	private String nom;
	private List<Musique> musiques;
	private HashMap<Utilisateur, Droit> utilisateurs;
	
	public Playlist() {
		this.musiques = new ArrayList<Musique>();
		this.utilisateurs = new HashMap<Utilisateur, Droit>();
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

	public void ajouterMusique(String lien) {
		Musique m = new Musique();
		m.setLien(lien);
		this.musiques.add(m);
	}

	public HashMap<Utilisateur, Droit> getUtilisateurs() {
		return utilisateurs;
	}

	public void ajouterUtilisateur(Utilisateur user, Droit droit) {
		this.utilisateurs.put(user, droit);
	}
}
