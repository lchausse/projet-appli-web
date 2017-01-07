package principal;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, String> erreurs = new HashMap<String, String>();
		
		switch (op) {
		case "Connexion":
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
				request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudo));
				request.getRequestDispatcher("compte.jsp").forward(request, response);
			} else {
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			}
			break;
			
		case "Inscription":
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
			break;
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
