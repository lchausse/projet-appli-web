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

	public Set<Playlist> rechercherPlaylistsPubliques(String[] motClefs) {
		Set<Playlist> playlistsARetirer;
		Set<Playlist> playlistsCorrespondantes = this.getPlaylistsPubliques();
//		for (Playlist pl : this.playlists) {
//			playlistsCorrespondantes.add(pl);
//		}
		for (String motClef : motClefs) {
			playlistsARetirer = new HashSet<Playlist>();
			for (Playlist pl : playlistsCorrespondantes) {
				if (!match(pl.getMotsClefs(), motClef)) {
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
	
	private Set<Playlist> getPlaylistsPubliques() {
		List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p", Playlist.class)
				                     .getResultList();
		Set<Playlist> playlistsPubliques = new HashSet<Playlist>();
		for (Playlist p : playlists) {
			if (p.isPublique()) {
				playlistsPubliques.add(p);
			}
		}
		return playlistsPubliques;
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
