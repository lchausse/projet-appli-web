package principal;

import java.io.IOException;
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
@WebServlet("/Projet")
public class ServletPlaylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadePlaylist facadePlaylist;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPlaylist() {
        super();
        this.facadePlaylist = new FacadePlaylist();
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
		String recherche = request.getParameter("recherche");
		switch (op) {
		case "Rechercher Playlist":
			String[] motsClefs = recherche.split(" ");
			Set<Playlist> results = facadePlaylist.rechercherPlaylists(motsClefs);
			request.setAttribute("resultatsRecherchePlaylist", results);
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
			break;
		}
	}

}
