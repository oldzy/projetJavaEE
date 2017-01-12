package be.belouh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.belouh.bean.Utilisateur;
import be.belouh.modele.Connection;
import be.belouh.modele.Library;

/**
 * Servlet implementation class Connection
 */
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHAMP_EMAIL = "e_mail";
	public static final String CHAMP_PASS = "password";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_LIST = "mediatheque";
	public static final String ATT_CONNECTION = "connection";
	public static final String VUE_CONNECTION = "/WEB-INF/vue/connection.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection c = new Connection();
		Library l = new Library();

		Utilisateur u = c.connecterUtilisateur(request.getParameter(CHAMP_EMAIL), request.getParameter(CHAMP_PASS));

		request.setAttribute(ATT_CONNECTION, c);

		if (c.getResultat()) {
			if (u != null) {
				HttpSession session = request.getSession();
				session.setAttribute(ATT_USER, u);
				session.setAttribute(ATT_LIST, l.getLibraries(request.getParameter(CHAMP_EMAIL)));
				response.sendRedirect("Movie");
			}
		} else
			this.getServletContext().getRequestDispatcher(VUE_CONNECTION).forward(request, response);
	}

}
