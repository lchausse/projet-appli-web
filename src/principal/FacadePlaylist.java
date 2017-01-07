package principal;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class FacadePlaylist {
	
  	List<Playlist> playlists; // liste des playlists disponibles sur le site
	@PersistenceContext
	EntityManager em;
	
	public FacadePlaylist() {
	}
	

	public void creerPlaylist(String nom) {
	  	Playlist playlist = new Playlist();
		playlist.setNom(nom);
		this.playlists.add(playlist);

	}

	public List<Playlist> rechercherPlaylists(List<String> motClefs) {
		List<Playlist> playlistsCorrespondantes = this.playlists;
		List<Playlist> playlistsARetirer;
		for (String motClef : motClefs) {
			playlistsARetirer = new ArrayList<Playlist>();
			for (Playlist pl : playlistsCorrespondantes) {
				if (!pl.getMotsClefs().contains(motClef)) {
					playlistsARetirer.add(pl);
				}
			}
			for (Playlist plARetirer : playlistsARetirer) {
				playlistsCorrespondantes.remove(plARetirer);
			}
		}
		return playlistsCorrespondantes;
	}

	public void ajouterMusique(Playlist playlist, Musique musique) {
	  	playlist.addMusique(musique);
	}

	public void supprimerMusique(Playlist playlist, Musique musique) {
		playlist.deleteMusique(musique);
	}

	public void rendrePublique(Playlist playlist) {
	}

	public void ModifierMotClef(Playlist playlist, List<String> nouveauxMotsClefs){
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
