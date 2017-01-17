package principal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class Facade {
	
	@PersistenceContext
	EntityManager em;
	
	public Facade() {
	}
	
	public void ajouterUtilisateur(String pseudo, String mdp) {
		Utilisateur user = new Utilisateur();
		user.setPseudo(pseudo);
		user.setMdp(mdp);
		em.persist(user);
	}

	public void modifierMdp(String pseudo, String mdp) {
		this.getUtilisateur(pseudo).setMdp(mdp);
	}

	public boolean seConnecter(String pseudo, String mdp) throws Exception {
		boolean res = false;
		TypedQuery<Utilisateur> req = em.createQuery(
				"SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo2", Utilisateur.class)
				.setParameter("pseudo2", pseudo);
		if (req.getResultList().size() == 0) {
			throw new Exception("Ce pseudo n'existe pas.");
		} else {
			Utilisateur user = req.getResultList().get(0);
			if (mdp.equals(user.getMdp())) {
				res = true;
			} else {
				throw new Exception("Mauvais mot de passe.");
			}
		}
		return res;
	}
	
	public void creerPlaylist(String nom, Utilisateur u, Set<String> motsClefs, boolean estPublique) {
	  	Playlist playlist = new Playlist();
	  	playlist.addUtilisateur(u);
		playlist.setTitre(nom);
		playlist.setMotsClefs(motsClefs);
		playlist.setPublique(estPublique);
		u.addPlaylist(playlist);
		em.persist(playlist);
	}
	
	public Set<Playlist> rechercherPlaylistsUtilisateur(String[] motClefs, Utilisateur u) {
		Set<Playlist> playlistsARetirer = new HashSet<Playlist>();
		Set<Playlist> playlistsCorrespondantes = new HashSet<Playlist>();
		List<String> motsClefs = Arrays.asList(motClefs);
		List<String> motsClefsTitre;
		Set<String> motsClefsTitreSet;
		playlistsCorrespondantes.addAll(u.getMesPlaylists());
		for (String motClef : motsClefs) {
			playlistsARetirer = new HashSet<Playlist>();
			for (Playlist pl : playlistsCorrespondantes) {
				motsClefsTitre = Arrays.asList(pl.getTitre().split(" "));
				motsClefsTitreSet = new HashSet<String>();
				motsClefsTitreSet.addAll(motsClefsTitre);
				motsClefsTitreSet.remove(new String(" "));
				if (!match(pl.getMotsClefs(), motClef) && !match(motsClefsTitreSet, motClef)) {
					playlistsARetirer.add(pl);
				}
			}
			for (Playlist plARetirer : playlistsARetirer) {
				playlistsCorrespondantes.remove(plARetirer);
			}
		}
		return playlistsCorrespondantes;
	}
	
	public Set<Playlist> rechercherPlaylistsPubliques(String[] motClefs) {
		Set<Playlist> playlistsARetirer = new HashSet<Playlist>();
		Set<Playlist> playlistsCorrespondantes = new HashSet<Playlist>();
		List<String> motsClefs = Arrays.asList(motClefs);
		List<String> motsClefsTitre;
		Set<String> motsClefsTitreSet;
		playlistsCorrespondantes.addAll(this.getPlaylistsPubliques());
		for (String motClef : motsClefs) {
			playlistsARetirer = new HashSet<Playlist>();
			for (Playlist pl : playlistsCorrespondantes) {
				motsClefsTitre = Arrays.asList(pl.getTitre().split(" "));
				motsClefsTitreSet = new HashSet<String>();
				motsClefsTitreSet.addAll(motsClefsTitre);
				motsClefsTitreSet.remove(new String(" "));
				if (!match(pl.getMotsClefs(), motClef) && !match(motsClefsTitreSet, motClef)) {
					playlistsARetirer.add(pl);
				}
			}
			for (Playlist plARetirer : playlistsARetirer) {
				playlistsCorrespondantes.remove(plARetirer);
			}
		}
		return playlistsCorrespondantes;
	}
	
	private boolean match(Set<String> motClefs, String motClef) {
		for (String mc : motClefs) {
			if (mc.toLowerCase().equals(motClef.toLowerCase())) return true;
			if (mc.toLowerCase().contains(motClef.toLowerCase())) return true;
			if ((mc.toLowerCase() + 's').equals(motClef.toLowerCase())) return true;
		}
		return false;
	}
	
	public Utilisateur getUtilisateur(String pseudo) {
		return em.find(Utilisateur.class, pseudo);
	}
	
	public Set<Playlist> getPlaylistsPubliques() {
		TypedQuery<Playlist> req = em.createQuery(
				"SELECT p FROM Playlist p WHERE p.publique = TRUE", Playlist.class);
		Set<Playlist> res = new HashSet<Playlist>();
		res.addAll(req.getResultList());
		return res;
	}
	
	public void ajouterMusique(Playlist playlist, Musique musique) {
	  	playlist.addMusique(musique);
	  	em.persist(musique);
	}

	public void supprimerMusique(Playlist playlist, Musique musique) {
		playlist.deleteMusique(musique);
	}

	public void ModifierMotClef(Playlist playlist, Set<String> nouveauxMotsClefs){
	  	playlist.setMotsClefs(nouveauxMotsClefs);
	}

	public void modifierTitrePlayList(Playlist playlist, String nouveauTitre) {
	  	playlist.setTitre(nouveauTitre);
	}

	public void modifierArtiste(Musique musique, String nouveauArtiste) {
	  	musique.setAuteur(nouveauArtiste);
	}

	public void modifierTitreMusique(Musique musique, String nouveauTitre) {
	  	musique.setTitre(nouveauTitre);

	}
	
	public Playlist getPlaylist(String titrePlaylist) {
		List<Playlist> playlists = em.createQuery("SELECT p FROM Playlist p", Playlist.class)
                .getResultList();
		for (Playlist pl : playlists) {
			if (pl.getTitre().equals(titrePlaylist)) {
				return pl;
			}
		}
		return null;
	}
	
	public void supprimerMusique(Playlist playlist, String lien) {
		Musique musiqueASupprimer = null;
		for (Musique m : playlist.getMusiques()) {
			if (m.getLien().equals(lien)) {
				musiqueASupprimer = m;
			}
		}
		playlist.deleteMusique(musiqueASupprimer);
		musiqueASupprimer.deletePlaylist(playlist);
		em.remove(em.find(Musique.class, musiqueASupprimer.getLien()));
	}

	public Playlist getPlaylist(int playlistId) {
		return em.find(Playlist.class, playlistId);
	}
	
	public void partager(int idPlaylist, String pseudo) throws Exception {
		Utilisateur u = em.find(Utilisateur.class, pseudo);
		Playlist p = em.find(Playlist.class, idPlaylist);
		if (u != null) {
			p.addUtilisateur(u);
		} else {
			throw new Exception("Cet utilisateur n'existe pas.");
		}
	}
}