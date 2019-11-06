package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utilities;

/**
 * Servlet implementation class AdivinaNumero
 */
@WebServlet("/adivinanumero")
public class AdivinaNumero extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int MAX = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdivinaNumero() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(Utilities.CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		if (!request.getParameter("numero").isEmpty() && request.getParameter("numero") != null) {
			int aleatorio = new Random().nextInt(MAX) + 1;
			int num = Integer.parseInt(request.getParameter("numero"));
			
			if (num == aleatorio) {
				out.println("¡ADIVINASTE EL NÚMERO! : " + aleatorio);
			} else {
				out.println("NO ACERTASTE CON EL NÚMERO: " + num);
			}
		} else {
			out.println("NO HAS INTRODUCIDO NINGUN NUMERO");
		}
	}
}
