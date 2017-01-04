package principal;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Projet
 */
@WebServlet("/Projet")
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
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		switch (op) {
		case "Connexion":
			boolean connexionPossible = facadeUtilisateur.seConnecter(pseudo, mdp);
			if (connexionPossible) {
				request.setAttribute("utilisateur", facadeUtilisateur.getUtilisateur(pseudo));
				request.getRequestDispatcher("compte.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("accueil.html").forward(request, response);
			}
			break;
		case "Inscription":
			facadeUtilisateur.ajouterUtilisateur(pseudo, mdp);
			request.getRequestDispatcher("accueil.html").forward(request, response);
			break;
		}
	}

}
