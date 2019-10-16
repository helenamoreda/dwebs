package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Serv
 */
@WebServlet(name = "Serv", urlPatterns = { "/ajedrez", "/index" })
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String cabecera = "<html><head></head><body><table style='border=solid 1px'>";
	private static final String footer = "</table></body></html>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double numero = Math.random()*23 + 17;
		
		String tabla = "";
		for (int f=1;f<=8;f++) {
			tabla += "<tr>";
			for (int c=1;c<=8;c++) {
				tabla += "<td style='border=solid 1px'>&#98"+numero+";</td>";
			}
			tabla += "</tr>";
		}
		response.getWriter().append(cabecera + tabla + footer);
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
