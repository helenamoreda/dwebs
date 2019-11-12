package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		generarTodo(request, response, sesion);
	}

	private void generarTodo(HttpServletRequest request, HttpServletResponse response, HttpSession sesion)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		if (sesion.isNew()) {
			sesion.setAttribute("key", generarKey());
			response.getWriter().append(generaCaptcha(sesion.getAttribute("key").toString()));
		} else {
			if (request.getParameter("numero") != null
					&& request.getParameter("numero").equals(sesion.getAttribute("key").toString())) {
				response.getWriter().append("<p style='color:green'>Correcto!</p>");
				response.getWriter().append(generaCaptcha(sesion.getAttribute("key").toString()));
			} else {
				response.getWriter().append("<p style='color:red'>Incorrecto!</p>");
				response.getWriter().append(generaCaptcha(sesion.getAttribute("key").toString()));
			}
		}
	}

	private String generaCaptcha(String key) {
		return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"utf-8\" />\r\n"
				+ "<title>Captcha Ejemplo</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "<h1>Captcha</h1>\r\n" + "\r\n"
				+ "    <p>Caracteres de la imagen</p>\r\n" + "\r\n"
				+ "      <svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"\r\n"
				+ "        width=\"400\" height=\"120\" style=\"background-color: white; border: black 1px solid\">\r\n"
				+ "\r\n" + "        <filter id=\"dropShadow\">\r\n"
				+ "          <feGaussianBlur in=\"SourceAlpha\" stdDeviation=\"3\" />\r\n"
				+ "          <feOffset dx=\"2\" dy=\"4\" />\r\n" + "          <feMerge>\r\n"
				+ "            <feMergeNode />\r\n" + "            <feMergeNode in=\"SourceGraphic\" />\r\n"
				+ "          </feMerge>\r\n" + "        </filter>\r\n" + "\r\n"
				+ "        <text x=\"0\" y=\"60\" style=\"font-size: 60px; stroke: #eee; fill: none\" filter=\"url(#dropShadow)\"\r\n"
				+ "          transform=\"skewX(50) skewY(20)\">" + key + "</text>\r\n" + "      </svg>\r\n" + "	 \r\n"
				+ "\r\n" + "    <form>\r\n"
				+ "      <p>Caracteres: <input type=\"text\" id=\"caracteres\" name=\"numero\" size=\"10\" /></p>\r\n"
				+ "      <p><input type=\"submit\" onclick=\"comprobar()\" value=\"Comprobar\" /></p>\r\n"
				+ "    </form>\r\n" + "</body>\r\n" + "</html>";
	}

	private String generarKey() {
		String key = "";
		for (int i = 0; i < 3; i++) {
			int num = (int) (Math.random() * 26 + 65);
			key += (char) num;
		}
		return key;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
