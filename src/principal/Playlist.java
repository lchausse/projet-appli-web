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
	private String nom;
//	private List<Musique> musiques;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Utilisateur> utilisateurs;
//	private List<String> motsClefs;
	private int vues;
	
	public Playlist() {
//		this.musiques = new ArrayList<Musique>();
		this.utilisateurs = new ArrayList<Utilisateur>();
//		this.motsClefs = new ArrayList<String>();
		this.vues = 0;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVues() {
		return this.vues;
	}
	
	public void setVues(int vues) {
		this.vues = vues;	
	}
	
//	public List<Musique> getMusiques() {
//		return musiques;
//	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

//	public List<String> getMotsClefs() {
//		return this.motsClefs;	
//	}
	
//	public void setMusiques(List<Musique> musiques) {
//		this.musiques = musiques;
//	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
//	public void setMotsClefs(List<String> motsClefs) {
//		this.motsClefs = motsClefs;	
//	}
}
