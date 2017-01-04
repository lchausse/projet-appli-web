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
		if (mdp == confirmationMdp) {
			Utilisateur user = new Utilisateur();
			user.setPseudo(pseudo);
			user.setMdp(mdp);
			em.persist(user);
			compteCree = true;
		}
	}
	
	public void modifierPseudo(String ancienPseudo, String nouveauPseudo) {
		this.getUtilisateur(ancienPseudo).setPseudo(nouveauPseudo);
	}
	
	public void modifierMdp(String pseudo, String mdp) {
		this.getUtilisateur(pseudo).setMdp(mdp);
	}
	
	public void partagerPlayList() {
		
	}
	
	public void ajouterPlayList() {
	}
	
	public void seConnecter() {
	}
	
	public Utilisateur getUtilisateur(String pseudo2) {
		TypedQuery<Utilisateur> req = em.createQuery(
				"SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo2", Utilisateur.class)
				.setParameter("pseudo2", pseudo2);
		
		return req.getResultList().get(0);
	}
	
	
}
