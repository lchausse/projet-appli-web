package principal;

import java.util.ArrayList;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class FacadePlayList {
	
  	List<PlayList> playlists; // liste des playlists disponibles sur le site
	@PersistenceContext
	EntityManager em;
	
	public FacadePlayList() {
	}
	

	public void creerPlayList(String nom) {
	  	Playlist playlist = new Playlist();
		playlist.setNom(nom);
		this.playlists.add(playlist);

	}

	public Playlist rechercherPlayList(String nom) {
	}

	public void ajouterMusique(Playlist playlist, Musique musique) {
	  	playlist.addMusique(musique);
	}

	public void supprimerMusique(Playlist playlist, Musique musique) {
		playlist.removeMusique(musique);
	}

	public void rendrePublique(Playlist playlist) {
	}

	public void ModifierMotClef(Playlist playlist, String... nouveauxMotsClefs){
	  	playlist.setMotsClefs(nouveauxMotsClefs);
	}

	public void modifierTitrePlayList(Playlist playlist, String nouveauTitre) {
	  	playlist.setNom(nouveauTitre);
	}

	public void modifierArtiste(Musique musique, String nouveauArtiste) {
	  	musique.setAuteur(nouveauArtiste);
	}

	public void modifierTitreMusique(Musique musique, String nouveauTitre) {
	  	musique.setTitre(nouveauTitre);

	}
}
