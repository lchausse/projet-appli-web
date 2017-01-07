package principal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class FacadePlaylist {
	
  	Set<Playlist> playlists; // liste des playlists disponibles sur le site
	@PersistenceContext
	EntityManager em;
	
	public FacadePlaylist() {
	}
	

	public void creerPlaylist(String nom) {
	  	Playlist playlist = new Playlist();
		playlist.setNom(nom);
		this.playlists.add(playlist);

	}

	public Set<Playlist> rechercherPlaylists(List<String> motClefs) {
		Set<Playlist> playlistsCorrespondantes = this.playlists;
		Set<Playlist> playlistsARetirer;
		for (String motClef : motClefs) {
			playlistsARetirer = new HashSet<Playlist>();
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

	public void ModifierMotClef(Playlist playlist, Set<String> nouveauxMotsClefs){
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
