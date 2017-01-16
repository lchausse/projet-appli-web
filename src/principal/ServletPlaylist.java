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
		Playlist pl;
		String[] motsClefs;
		String rechercheMusique;
		if (op.equals("Rechercher Playlist")) { 
			motsClefs = recherche.split(" ");
			results = facadePlaylist.rechercherPlaylistsPubliques(motsClefs);
			request.setAttribute("resultatsRecherchePlaylist", results);
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		}
		else if (op.equals("Modifier")) { 
			pl = facadePlaylist.getPlaylist( request.getParameter("titrePlaylist"));
			request.setAttribute("playlist", pl);
			request.setAttribute("utilisateur", request.getParameter("utilisateur"));
			request.getRequestDispatcher("modifierPlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Ajouter Musique")) {
			pl = facadePlaylist.getPlaylist(request.getParameter("titrePlaylist"));
			request.setAttribute("playlist", pl);
			request.setAttribute("utilisateur", request.getParameter("utilisateur"));
			Musique m = new Musique();
			m.setAuteur(request.getParameter("auteur"));
			m.setTitre(request.getParameter("titre"));
			m.setLien(request.getParameter("lien"));
			m.addPlaylist(pl);
			facadePlaylist.ajouterMusique(pl, m);
			request.getRequestDispatcher("modifierPlaylist.jsp").forward(request, response);
		} 
		else if (op.equals("Rechercher Musique")) {
			pl = facadePlaylist.getPlaylist(request.getParameter("titrePlaylist"));
			request.setAttribute("playlist", pl);
			request.setAttribute("utilisateur", request.getParameter("utilisateur"));
			rechercheMusique = request.getParameter("rechercheMusique");
			List<SearchResult> resultatsRecherche = Search.youtubeSearch(rechercheMusique);
			request.setAttribute("resultatsRechercheMusique", resultatsRecherche);
			request.getRequestDispatcher("modifierPlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Ecouter")) {
			int playlistId = Integer.parseInt(request.getParameter("idPlaylist"));
			String pseudo = request.getParameter("utilisateur");
			String typePlaylist = request.getParameter("typePlaylist");
			request.setAttribute("typePlaylist", typePlaylist);
			request.setAttribute("utilisateur", pseudo);
			request.setAttribute("musiqueCourante", 0);
			request.setAttribute("playlist", facadePlaylist.getPlaylist(playlistId));
			request.getRequestDispatcher("lirePlaylist.jsp").forward(request, response);
		}
		else if(op.equals("Supprimer Musique")) {
			pl = facadePlaylist.getPlaylist(request.getParameter("titrePlaylist"));
			String m = request.getParameter("idMusique");
			System.err.println(pl.getMusiques());
			facadePlaylist.supprimerMusique(pl, m);
			System.err.println(pl.getMusiques());
			request.setAttribute("playlist", pl);
			request.setAttribute("utilisateur", request.getParameter("utilisateur"));
			request.getRequestDispatcher("modifierPlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Musique suivante")) {
			int musiqueCourante = Integer.parseInt(request.getParameter("musiqueCourante"));
			int playlistId = Integer.parseInt(request.getParameter("idPlaylist"));
			String pseudo = request.getParameter("utilisateur");
			musiqueCourante++;
			request.setAttribute("utilisateur", pseudo);
			request.setAttribute("musiqueCourante", musiqueCourante);
			request.setAttribute("playlist", facadePlaylist.getPlaylist(playlistId));
			request.getRequestDispatcher("lirePlaylist.jsp").forward(request, response);
		}
		else if (op.equals("Musique precedente")) {
			int musiqueCourante = Integer.parseInt(request.getParameter("musiqueCourante"));
			int playlistId = Integer.parseInt(request.getParameter("idPlaylist"));
			String pseudo = request.getParameter("utilisateur");
			musiqueCourante--;
			request.setAttribute("utilisateur", pseudo);
			request.setAttribute("musiqueCourante", musiqueCourante);
			request.setAttribute("playlist", facadePlaylist.getPlaylist(playlistId));
			request.getRequestDispatcher("lirePlaylist.jsp").forward(request, response);
		}
	}
}
