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
import be.belouh.modele.Inscription;

/**
 * Servlet implementation class InscriptionServlet
 */
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHAMP_LAST_NAME = "last_name";
	public static final String CHAMP_FIRST_NAME = "first_name";
	public static final String CHAMP_EMAIL = "e_mail";
	public static final String CHAMP_DATE = "date";
	public static final String CHAMP_PASS = "password";
	public static final String CHAMP_CONF = "conf_password";
	public static final String ATT_INSCRIPTION = "inscription";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(ConnectionServlet.VUE_CONNECTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Inscription i = new Inscription();

		Utilisateur u = i.inscrireUtilisateur(request.getParameter(CHAMP_LAST_NAME),
				request.getParameter(CHAMP_FIRST_NAME), request.getParameter(CHAMP_EMAIL),
				request.getParameter(CHAMP_DATE), request.getParameter(CHAMP_PASS), request.getParameter(CHAMP_CONF));

		request.setAttribute(ATT_INSCRIPTION, i);

		if (i.getResultat()) {
			if (u != null) {
				HttpSession session = request.getSession();
				session.setAttribute(ConnectionServlet.ATT_USER, u);
				session.setAttribute(ConnectionServlet.ATT_LIST, new ArrayList<Mediatheque>());
				response.sendRedirect("Movie");
			}
		} else
			this.getServletContext().getRequestDispatcher(ConnectionServlet.VUE_CONNECTION).forward(request, response);
	}

}
