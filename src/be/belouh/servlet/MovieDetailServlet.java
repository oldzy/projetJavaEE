package be.belouh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.belouh.modele.Movie;

/**
 * Servlet implementation class MovieDetailServlet
 */
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FILM = "film";
	private static final String VUE_DETAIL = "/WEB-INF/vue/movieDetail.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieDetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Movie");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Movie m = new Movie();

		request.setAttribute(FILM, m.getMovie(m.getUrl(), request.getParameter("id").toString()));
		this.getServletContext().getRequestDispatcher(VUE_DETAIL).forward(request, response);
	}

}
