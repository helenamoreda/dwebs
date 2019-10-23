package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class buscador
 */
@WebServlet("/buscador")
public class buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String cad;
	private static String busc;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public buscador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("cadena") != null && request.getParameter("buscador") != null) {
			cad = request.getParameter("cadena");
			busc = request.getParameter("buscador");

			switch (busc) {
			case "google":
				response.sendRedirect("https://www.google.com/search?q=" + cad);
				break;
			case "bing":
				response.sendRedirect("https://www.bing.com/search?q=" + cad);
				break;
			case "yahoo":
				response.sendRedirect("https://es.search.yahoo.com/search?p=" + cad);
				break;
			case "googleI":
				response.sendRedirect("https://www.google.com/images?q=" + cad);
				break;
			default:
				response.sendRedirect("https://www.google.com/search?q=" + cad);
				break;
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Búsqueda no concretada");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
