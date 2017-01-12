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
public class FacadeUtilisateur {
	
	@PersistenceContext
	EntityManager em;
	
	public FacadeUtilisateur() {
	}
	
	public void ajouterUtilisateur(String pseudo, String mdp) {
		Utilisateur user = new Utilisateur();
		user.setPseudo(pseudo);
		user.setMdp(mdp);
		em.persist(user);
	}

	public void modifierPseudo(String ancienPseudo, String nouveauPseudo) {
		this.getUtilisateur(ancienPseudo).setPseudo(nouveauPseudo);
	}

	public void modifierMdp(String pseudo, String mdp) {
		this.getUtilisateur(pseudo).setMdp(mdp);
	}

	public void partagerPlayList(Utilisateur user, Playlist playlist) {
		playlist.addUtilisateur(user);
	}

	public void ajouterPlayList(String pseudo, Playlist playlist) {
//		this.getUtilisateur(pseudo).addPlaylist(playlist);
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
		Set<Playlist> playlistsARetirer;
		Set<Playlist> playlistsCorrespondantes = new HashSet<Playlist>();
		playlistsCorrespondantes.addAll(u.getMesPlaylists());
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
	
	private boolean match(Set<String> motClefs, String motClef) {
		for (String mc : motClefs) {
			if (mc.equals(motClef)) {
				return true;
			}
			if (mc.toLowerCase().contains(motClef.toLowerCase())) {
				return true;
			}
		}
		return false;
	}	
	
	public Utilisateur getUtilisateur(String pseudo2) {
		TypedQuery<Utilisateur> req = em.createQuery(
				"SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo2", Utilisateur.class)
				.setParameter("pseudo2", pseudo2);
		
		if (req.getResultList().size() == 0) {
			return null;	
		}
		else {
			return req.getResultList().get(0);
		}
	}
}
