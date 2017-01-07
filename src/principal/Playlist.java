package principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Playlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom; // nom de la playlist
	private List<Musique> musiques; // musique de la playlist
	@ManyToMany(fetch=FetchType.EAGER) 
	private List<Utilisateur> utilisateurs; // utilisateur pouvant modifier la playlist
	private List<String> motsClefs; // mots clefs de la playlist
	private int vues; // nombre de vue de la playlistre
	
	public Playlist() {
		this.musiques = new ArrayList<Musique>();
		this.utilisateurs = new ArrayList<Utilisateur>();
		this.motsClefs = new ArrayList<String>();
		this.vues = 0;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public List<Musique> getMusiques() {
		return this.musiques;
	}
	
	public void setMusiques(List<Musique> musiques) {
		this.musiques = musiques;
	}
	
	public void addMusique(Musique musique) {
		this.musiques.add(musique);	
	}
	
	public void deleteMusique(Musique musique) {
		this.musiques.remove(musique);
	}

	
	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}
	
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public void addUtilisateur(Utilisateur user) {
		this.utilisateurs.add(user);
	}

	
	public List<String> getMotsClefs() {
		return this.motsClefs;	
	}
	public void setMotsClefs(List<String> motsClefs) {
		this.motsClefs = motsClefs;	
	}


	public int getVues() {
		return this.vues;
	}
	
	public void setVues(int vues) {
		this.vues = vues;	
	}
	
	public void addVues() {
		this.vues++;
	}
}
