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
public class Projet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private Facade facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Projet() {
        super();
        this.facade = new Facade();
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
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		switch (op) {
		case "con":
			request.setAttribute("utilisateur", facade.getUtilisateur(pseudo));
			request.getRequestDispatcher("compte.jsp").forward(request, response);
			break;
		case "Inscription":
			facade.ajouterUtilisateur(nom, prenom, mail, pseudo, mdp);
			request.getRequestDispatcher("accueil.html").forward(request, response);
			break;
		}
	}

}
