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
	
	public void ajouterUtilisateur(String pseudo, String mdp) {
		Utilisateur user = new Utilisateur();
		user.setPseudo(pseudo);
		user.setMdp(mdp);
		em.persist(user);
	}
	
	public Utilisateur getUtilisateur(String pseudo2) {
		TypedQuery<Utilisateur> req = em.createQuery(
				"SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo2", Utilisateur.class)
				.setParameter("pseudo2", pseudo2);
		
		return req.getResultList().get(0);
	}
	
	
}
