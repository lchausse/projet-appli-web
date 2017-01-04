package principal;

import java.util.ArrayList;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
public class FacadeUtilisateur {
	
	private ArrayList<Utilisateur> utilisateurs;
	@PersistenceContext
	EntityManager em;
	
	public FacadeUtilisateur() {
		this.utilisateurs = new ArrayList<Utilisateur>();
	}
	
	public boolean ajouterUtilisateur(String pseudo, String mdp, String confirmationMdp) {
		boolean compteCree = false;

		if (mdp.equals(confirmationMdp) && getUtilisateur(pseudo) == null ) {
			Utilisateur user = new Utilisateur();
			user.setPseudo(pseudo);
			user.setMdp(mdp);
			em.persist(user);
			compteCree = true;
		}
		return compteCree;
	}

	public void modifierPseudo(String ancienPseudo, String nouveauPseudo) {
		this.getUtilisateur(ancienPseudo).setPseudo(nouveauPseudo);
	}

	public void modifierMdp(String pseudo, String mdp) {
		this.getUtilisateur(pseudo).setMdp(mdp);
	}

	public void partagerPlayList() {
	}

	public void ajouterPlayList(String pseudo, Playlist playlist) {
//		this.getUtilisateur(pseudo).addPlaylist(playlist);
	}

	public boolean seConnecter(String pseudo, String mdp) {
		boolean res;
		TypedQuery<Utilisateur> req = em.createQuery(
				"SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo2", Utilisateur.class)
				.setParameter("pseudo2", pseudo);
		if (req.getResultList().size() == 0) {
			res = false;
		} else {
			Utilisateur user = req.getResultList().get(0);
			if (mdp.equals(user.getMdp())) {
				res = true;
			} else {
				res = false;
			}
		}
		return res;
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
