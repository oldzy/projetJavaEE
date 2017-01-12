package be.belouh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.belouh.modele.Movie;

/**
 * Servlet implementation class MovieWatchServlet
 */
public class MovieWatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAGNET = "magnet";
	private static final String ID = "id";
	private static final String VUE_WATCH = "/WEB-INF/vue/movieWatch.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieWatchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter(ID) != null){
			Movie m = new Movie();
			
			response.getWriter().println(m.getStatus(request.getParameter(ID).toString()));
		}else{
			response.sendRedirect("Movie");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Movie m = new Movie();
		
		m.startDownload(request.getParameter(MAGNET).toString());
		
		request.setAttribute(ID, request.getParameter(MAGNET).toString().split(":")[3].split("&")[0].toLowerCase());
		
		this.getServletContext().getRequestDispatcher(VUE_WATCH).forward(request, response);
	}

}
