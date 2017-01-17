package principal;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	private Facade facadeUtilisateur;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUtilisateur() {
        super();
        this.facadeUtilisateur = new Facade();
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
		String mdp;
		String confirmationMdp;
		String[] motsClefs;
		
		if (op.equals("Se connecter")) {
			Map<String, String> erreurs = new HashMap<String, String>();
			boolean connexionPossible = false;
			String pseudo = request.getParameter("pseudo");
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
				request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudo));
				request.setAttribute("playlistsPubliques", facadeUtilisateur.getPlaylistsPubliques());
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			} else {
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("connexion.jsp").forward(request, response);
			}
		}
		else if (op.equals("Connexion")) {
			request.getRequestDispatcher("connexion.jsp").forward(request, response);
		}
		else if (op.equals("Inscription")) {
			request.getRequestDispatcher("creerCompte.jsp").forward(request, response);
		}
		else if (op.equals("S'inscrire")) {
			Map<String, String> erreurs = new HashMap<String, String>();
			String pseudo = request.getParameter("pseudo");
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
		else if (op.equals("Nouvelle Playlist")) {
			String pseudo = request.getParameter("utilisateur");
			request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudo));
			request.getRequestDispatcher("creerPlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Creer Playlist")) {
			Map<String, String> erreurs = new HashMap<String, String>();
			String pseudo = request.getParameter("utilisateur");
			Utilisateur user = facadeUtilisateur.getUtilisateur(pseudo);
			String titre = request.getParameter("titre");
			motsClefs = request.getParameter("motsClefs").split(" ");
			Set<String> motsClefsEns = (new HashSet<String>(Arrays.asList(motsClefs)));
			motsClefsEns.remove("");
			String estPublique = request.getParameter("estPublique");
			try {
			    validationTitrePlaylist(titre, user);
			} catch (Exception e) {
			    erreurs.put("titre", e.getMessage());
			}
			if (erreurs.isEmpty()) {
				this.facadeUtilisateur.creerPlaylist(titre, user, motsClefsEns, (estPublique.equals("Publique") ? true : false));
				request.setAttribute("utilisateur", user);
				request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
			} else {
				request.setAttribute("utilisateur", pseudo);
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("creerPlaylist.jsp").forward(request, response);
			}
		}
		else if (op.equals("Rechercher")) {
			String recherche = request.getParameter("recherche");
			String pseudo = request.getParameter("utilisateur");
			Utilisateur user = facadeUtilisateur.getUtilisateur(pseudo);
			motsClefs = recherche.split(" ");
			Set<Playlist> results = facadeUtilisateur.rechercherPlaylistsUtilisateur(motsClefs, user);
			request.setAttribute("resultats", results);
			request.setAttribute("utilisateur", user);
			request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
		}
		else if (op.equals("Rechercher musique")) {
			String rechercheMusique = request.getParameter("rechercheMusique");
		}
		else if (op.equals("Mes playlists")) {
			Utilisateur user = facadeUtilisateur.getUtilisateur(request.getParameter("pseudo"));
			request.setAttribute("utilisateur", user);
			request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
		}
		else if (op.equals("Mon compte")) {
			Utilisateur user = facadeUtilisateur.getUtilisateur(request.getParameter("pseudo"));
			request.setAttribute("utilisateur", user);
			request.getRequestDispatcher("parametres.jsp").forward(request, response);
		}
		else if (op.equals("Modifier mot de passe")) {
			Map<String, String> erreurs = new HashMap<String, String>();
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String newPassword2 = request.getParameter("newPassword2");
			String pseudo = request.getParameter("utilisateur");
			Utilisateur user = facadeUtilisateur.getUtilisateur(pseudo);
			if (!user.getMdp().equals(oldPassword)) {
				erreurs.put("oldMdp", "L'ancien mot de passe n'est pas correct.");
			} else {
				try {
				    validationMotsDePasse(newPassword, newPassword2);
				} catch (Exception e) {
				    erreurs.put("newMdp", e.getMessage());
				}
			}
			request.setAttribute("utilisateur", user);
			if (erreurs.isEmpty()) {
				facadeUtilisateur.modifierMdp(pseudo, newPassword);
				request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
			} else {
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("parametres.jsp").forward(request, response);
			}
		}
		else if (op.equals("Accueil")) {
			String pseudoUser = request.getParameter("pseudo");
			if (pseudoUser != null) {
				request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudoUser));
			}
			request.setAttribute("playlistsPubliques", facadeUtilisateur.getPlaylistsPubliques());
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		}
		else if (op.contains("ajouterPlaylist")) {
			String pseudoUser = op.split("ajouterPlaylist")[1];
			request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudoUser));
			request.getRequestDispatcher("creerPlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Deconnexion")) {
			request.setAttribute("utilisateur", null);
			request.setAttribute("playlistsPubliques", facadeUtilisateur.getPlaylistsPubliques());
			request.getRequestDispatcher("accueil.jsp").forward(request,  response);
		}
	}
	
	private void validationTitrePlaylist(String titre, Utilisateur u) throws Exception {
		boolean titreDisponible = true;
		Set<Playlist> playlists = u.getMesPlaylists();
		for (Playlist p: playlists) {
			if (titre.equals(p.getTitre())) {
				titreDisponible = false;
				break;
			}
		}
		if (!titreDisponible) {
			throw new Exception("Ce titre n'est pas disponible !");
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