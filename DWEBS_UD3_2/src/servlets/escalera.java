package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utilities;

/**
 * Servlet implementation class escalera
 */
@WebServlet("/escalera")
public class escalera extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public escalera() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(Utilities.headWithTitle("escalera") + "<body>");

		String cad = "";
		if (request.getParameter("n") != null) {
			try {
				Integer num = Integer.parseInt(request.getParameter("n"));
				for (int i = 1; i <= num; i++) {
					if (num > 10 && i % 2 == 0) {
						response.getWriter().append("<div style='color:aqua'>");
					} else {
						response.getWriter().append("<div>");
					}
					cad += "&#9851;";
					response.getWriter().append(cad);
					response.getWriter().append("</div>");
				}
			} catch (NumberFormatException e) {
				response.getWriter().append("No has introducido un número");
			}
		} else {
			response.getWriter().append("No has pasado el parámetro n");
		}
		response.getWriter().append("</body></html>");
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
