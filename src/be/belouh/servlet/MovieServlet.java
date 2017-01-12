package be.belouh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.belouh.modele.Movie;

/**
 * Servlet implementation class MovieServlet
 */
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_FILM = "/WEB-INF/vue/movie.jsp";
	private static final String NB_PAGE = "nb_page";
	private static final String FILMS = "films";
	private static final String NUMERO_PAGE = "numero";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpProcess(request, response);
	}

	private void HttpProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Movie m = new Movie();

		int numero = 1;

		if (request.getParameter(NUMERO_PAGE) != null)
			numero = Integer.parseInt(request.getParameter(NUMERO_PAGE).toString());

		request.setAttribute(NUMERO_PAGE, numero);
		request.setAttribute(NB_PAGE, m.getPages(m.getUrl()));
		request.setAttribute(FILMS, m.getPage(m.getUrl(), numero));

		this.getServletContext().getRequestDispatcher(VUE_FILM).forward(request, response);
	}
}
