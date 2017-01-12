package principal;

import model.Search;

import java.io.IOException;
import java.util.Set;

import com.google.api.services.youtube.model.SearchResult;

import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Projet
 */
@WebServlet("/ServletPlaylist")
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
		Set<Playlist> results;
		String[] motsClefs;
		String rechercheMusique;
		switch (op) {
		case "Créer Playlist":
			String titrePlaylist = request.getParameter("titre");
			String motsClefsPlaylist = request.getParameter("motsClefs");
			String estPublique = request.getParameter("estPublique");
			Utilisateur createur = (Utilisateur) request.getAttribute("utilisateur");
			facadePlaylist.creerPlaylist(new Playlist(titrePlaylist, motsClefsPlaylist, createur, (estPublique.equals("Publique") ? true : false)));
			request.getRequestDispatcher("mesPlaylists.jsp").forward(request, response);
			break;
			
		case "Rechercher Playlist":
			motsClefs = recherche.split(" ");
			results = facadePlaylist.rechercherPlaylistsPubliques(motsClefs);
			request.setAttribute("resultatsRecherchePlaylist", results);
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
			break;
			
		case "Rechercher":
			rechercheMusique = request.getParameter("rechercheMusique");
			List<SearchResult> resultatsRecherche = Search.youtubeSearch(rechercheMusique);
			request.setAttribute("resultatsRechercheMusique", resultatsRecherche);
			request.getRequestDispatcher("modifierPlaylist.jsp").forward(request, response);
		}
	}
}
