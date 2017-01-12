package be.belouh.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.belouh.bean.Film;
import be.belouh.bean.Mediatheque;
import be.belouh.bean.Utilisateur;
import be.belouh.modele.Library;
import be.belouh.modele.Movie;

/**
 * Servlet implementation class LibraryDetailServlet
 */
public class LibraryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PAR_NAME = "name";
	public static final String VUE_LIBRARY_DETAIL = "/WEB-INF/vue/libraryDetail.jsp";
	public static final String ATT_MED = "mediatheque";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibraryDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Library");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Library l = new Library();
		ArrayList<HashMap<String, String>> films = new ArrayList<HashMap<String, String>>();
		Movie mov = new Movie();

		session.setAttribute(ConnectionServlet.ATT_LIST,
				l.getLibraries(((Utilisateur) session.getAttribute(ConnectionServlet.ATT_USER)).getEmail()));

		@SuppressWarnings("unchecked")
		ArrayList<Mediatheque> list = (ArrayList<Mediatheque>) session.getAttribute(ConnectionServlet.ATT_LIST);

		Mediatheque m = list.stream().filter(x -> request.getParameter(PAR_NAME).equals(x.getNom())).findAny()
				.orElse(null);

		for (Film f : m.getListe()) {
			films.add(mov.getMovie(mov.getUrl(), f.getId()));
		}

		request.setAttribute(ATT_MED, films);

		this.getServletContext().getRequestDispatcher(VUE_LIBRARY_DETAIL).forward(request, response);
	}

}
