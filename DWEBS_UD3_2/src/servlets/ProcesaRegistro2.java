package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utilities;

/**
 * Servlet implementation class ProcesaRegistro2
 */
@WebServlet("/procesa2")
public class ProcesaRegistro2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcesaRegistro2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(Utilities.headWithTitle("Registro") + "<body><ul>");
		Enumeration<String> nombres = request.getParameterNames();

		while (nombres.hasMoreElements()) {
			String elemento = nombres.nextElement();
			
			if (request.getParameter(elemento) != null && !elemento.equals("departamentos") && !elemento.equals("lenguajes")) {
				response.getWriter().append("<li>" + elemento + ": " + request.getParameter(elemento) + "</li>");
			}
			
			if (elemento.equals("departamentos")) {
				String[] depar = request.getParameterValues("departamentos");
				response.getWriter().append("<li>"+elemento+ ": ");
				for (String it : depar) {
					response.getWriter().append(it+", ");
				}
				response.getWriter().append("</li>");
			}
			
			if (elemento.equals("lenguajes")) {
				String[] leng = request.getParameterValues("lenguajes");
				response.getWriter().append("<li>"+elemento+ ": ");
				for (String it : leng) {
					response.getWriter().append(it+", ");
				}
				response.getWriter().append("</li>");
			}
		}
		response.getWriter().append("</ul></body></html>");
	}

}
