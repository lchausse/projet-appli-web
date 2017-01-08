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
		this.playlists = new HashSet<Playlist>();
	}
	

	public void creerPlaylist(String nom) {
	  	Playlist playlist = new Playlist();
		playlist.setNom(nom);
		this.playlists.add(playlist);

	}

	public Set<Playlist> rechercherPlaylists(String[] motClefs) {
		Set<Playlist> playlistsARetirer;
		Set<Playlist> playlistsCorrespondantes = new HashSet<Playlist>();
		for (Playlist pl : this.playlists) {
			if (pl.isPublique) playlistsCorrespondantes.add(pl);
		}
		for (String motClef : motClefs) {
			playlistsARetirer = new HashSet<Playlist>();
			for (Playlist pl : playlistsCorrespondantes) {
				if (!match(pl.getMotsClefs(), motClef)) playlistsARetirer.add(pl);
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
	
	private boolean match(Set<String> motClefs, String motClef) {
		for (String mc : motClefs) {
			if (mc.toLowerCase().equals(motClef.toLowerCase())) return true;
			if (mc.toLowerCase().contains(motClef.toLowerCase())) return true;
			if ((mc.toLowerCase() + 's').equals(motClef.toLowerCase())) return true;
		}
		return false;
	}
}
