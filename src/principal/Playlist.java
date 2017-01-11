package principal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
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
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Musique> musiques; // musique de la playlist
	@ManyToMany(fetch=FetchType.EAGER) 
	private Set<Utilisateur> utilisateurs; // utilisateur pouvant modifier la playlist
    @ElementCollection(targetClass=String.class)
	private Set<String> motsClefs; // mots clefs de la playlist
    private boolean publique;
	private int vues; // nombre de vue de la playlistre
	
	public Playlist() {
		this.musiques = new HashSet<Musique>();
		this.utilisateurs = new HashSet<Utilisateur>();
		this.motsClefs = new HashSet<String>();
		this.vues = 0;
		this.publique = false;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Set<Musique> getMusiques() {
		return this.musiques;
	}
	
	public void setMusiques(Set<Musique> musiques) {
		this.musiques = musiques;
	}
	
	public void addMusique(Musique musique) {
		this.musiques.add(musique);	
	}
	
	public void deleteMusique(Musique musique) {
		this.musiques.remove(musique);
	}

	
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}
	
	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
	public void addUtilisateur(Utilisateur user) {
		this.utilisateurs.add(user);
	}

	
	public Set<String> getMotsClefs() {
		return this.motsClefs;	
	}
	public void setMotsClefs(Set<String> motsClefs) {
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
	
	public boolean isPublique() {
		return publique;
	}

	public void setPublique(boolean publique) {
		this.publique = publique;
	}
}
