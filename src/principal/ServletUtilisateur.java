package principal;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Projet
 */
@WebServlet("/ServletUtilisateur")
public class ServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeUtilisateur facadeUtilisateur;
	private Utilisateur utilisateurCourant;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUtilisateur() {
        super();
        this.facadeUtilisateur = new FacadeUtilisateur();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String pseudo;
		String mdp;
		String confirmationMdp;
		String[] motsClefs;
		Map<String, String> erreurs = new HashMap<String, String>();
		
		if (op.equals("Connexion")) {
			boolean connexionPossible = false;
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			try {
				connexionPossible = facadeUtilisateur.seConnecter(pseudo, mdp);
			} catch (Exception e) {
				String errMes = e.getMessage();
				if (errMes.equals("Ce pseudo n'existe pas.")) {
					erreurs.put("pseudo", errMes);
				} else if (errMes.equals("Mauvais mot de passe.")) {
					erreurs.put("mdp", errMes);
				}
			}
			if (connexionPossible) {
				utilisateurCourant = facadeUtilisateur.getUtilisateur(pseudo);
				request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudo));
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			} else {
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("connexion.jsp").forward(request, response);
			}
		}
		else if (op.equals("Inscription")) {
			pseudo = request.getParameter("pseudo");
			mdp = request.getParameter("mdp");
			confirmationMdp = request.getParameter("mdp2");
			
			/* Validation des champs mot de passe et confirmation. */
	        try {
	            validationMotsDePasse(mdp, confirmationMdp);
	        } catch (Exception e) {
	            erreurs.put("mdp", e.getMessage());
	        }

	        /* Validation du champ pseudo */
	        try {
	            validationPseudo(pseudo);
	        } catch ( Exception e ) {
	            erreurs.put("pseudo", e.getMessage());
	        }
	        
	        if (erreurs.isEmpty()) {
	        	facadeUtilisateur.ajouterUtilisateur(pseudo, mdp);
	        	request.getRequestDispatcher("accueil.jsp").forward(request, response);
	        } else {
	        	request.setAttribute("erreurs", erreurs);
		        request.getRequestDispatcher("creerCompte.jsp").forward(request, response);
	        }
		}
		else if (op.equals("Appliquer")) {
			String titre = request.getParameter("titre");
			motsClefs = request.getParameter("motsClefs").split(" ");
			Set<String> motsClefsEns = (new HashSet<String>(Arrays.asList(motsClefs)));
			motsClefsEns.remove("");
			this.facadeUtilisateur.creerPlaylist(titre, utilisateurCourant, motsClefsEns);
			request.setAttribute("utilisateur", utilisateurCourant);
			request.setAttribute("resultats", new HashSet<Playlist>());
			request.getRequestDispatcher("compte.jsp").forward(request, response);
		}
		else if (op.equals("Rechercher")) {
			String recherche = request.getParameter("recherche");
			motsClefs = recherche.split(" ");
			Set<Playlist> results = facadeUtilisateur.rechercherPlaylistsUtilisateur(motsClefs, utilisateurCourant);
			request.setAttribute("resultats", results);
			request.setAttribute("utilisateur", utilisateurCourant);
			request.getRequestDispatcher("compte.jsp").forward(request, response);
		}
		else if (op.equals("Rechercher musique")) {
			String rechercheMusique = request.getParameter("rechercheMusique");
		}
		else if (op.contains("mesPlaylists")) {
			String pseudoUser = op.split("mesPlaylists")[1];
			request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudoUser));
			request.setAttribute("resultats", new HashSet<Playlist>());
			request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
		}
		else if (op.contains("accueil")) {
			String pseudoUser = op.split("accueil")[1];
			request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudoUser));
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		}
		else if (op.contains("ajouterPlaylist")) {
			String pseudoUser = op.split("ajouterPlaylist")[1];
			request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudoUser));
			request.getRequestDispatcher("creerPlaylist.jsp").forward(request, response);
		}
	}
	
	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception{
	    if (!motDePasse.equals(confirmation)) {
	        throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	    } else if (motDePasse.trim().length() < 3) {
	        throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	    }
	}
	
	private void validationPseudo(String pseudo) throws Exception {
	    if (pseudo != null && pseudo.trim().length() < 3) {
	        throw new Exception("Le pseudo doit contenir au moins 3 caractères.");
	    } else if (facadeUtilisateur.getUtilisateur(pseudo) != null) {
	    	throw new Exception("Ce pseudo n'est pas disponible.");
	    }
	}
}
