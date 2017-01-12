package be.belouh.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.belouh.bean.Mediatheque;
import be.belouh.bean.Utilisateur;
import be.belouh.modele.Library;

/**
 * Servlet implementation class LibraryServlet
 */
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PAR_ID = "id";
	public static final String PAR_MED = "med";
	public static final String PAR_TITLE = "title";
	public static final String PAR_LIB_NAME = "library_name";
	public static final String VUE_LIBRARY = "/WEB-INF/vue/library.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibraryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(PAR_ID) != null || request.getParameter(PAR_MED) != null
				|| request.getParameter(PAR_TITLE) != null) {

			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			ArrayList<Mediatheque> listMediatheque = (ArrayList<Mediatheque>) session
					.getAttribute(ConnectionServlet.ATT_LIST);
			Library l = new Library();

			Mediatheque m = listMediatheque.stream()
					.filter(x -> request.getParameter(PAR_MED).toString().equals(x.getNom())).findAny().orElse(null);

			l.addFilm(m, request.getParameter(PAR_ID).toString(), request.getParameter(PAR_TITLE).toString());
		} else {
			this.getServletContext().getRequestDispatcher(VUE_LIBRARY).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(PAR_LIB_NAME) != null && !request.getParameter(PAR_LIB_NAME).toString().equals("")) {
			Library l = new Library();
			HttpSession session = request.getSession();

			if (l.addLibrary(request.getParameter(PAR_LIB_NAME).toString(),
					((Utilisateur) session.getAttribute(ConnectionServlet.ATT_USER)).getEmail())) {
				session.setAttribute(ConnectionServlet.ATT_LIST,
						l.getLibraries(((Utilisateur) session.getAttribute(ConnectionServlet.ATT_USER)).getEmail()));
			}
		}
		this.getServletContext().getRequestDispatcher(VUE_LIBRARY).forward(request, response);
	}

}
