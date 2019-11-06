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
 * Servlet implementation class GeneraNumero
 */
@WebServlet("/generanumero")
public class GeneraNumero extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 291732480918277975L;

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
		out.println(new Random().nextInt(10) + 1);
	}
}
