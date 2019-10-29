package session;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Utilities;

/**
 * Servlet implementation class MostrarSesion
 */
@WebServlet("/misesion")
public class MostrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Integer contador;
	private static Date fechaCreacion;
	private static Date fechaLastSesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Recupero la sesion del contexto de la conex
		HttpSession sesion = request.getSession();

		// seteo las fechas para la tabla
		fechaCreacion = new Date(sesion.getCreationTime());
		fechaLastSesion = new Date(sesion.getLastAccessedTime());
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(Utilities.headWithTitle("Control de Sesión"));

		// 2. Compruebo si la sesion es nueva
		if (sesion.isNew()) {
			response.getWriter().append("<h1>Bienvenido a la sesión</h1>");
			contador = 0;
			sesion.setAttribute("accesos", contador);
		} else {
			response.getWriter().append("<h1>Bienvenido de nuevo</h1>");
			// añadimos una visita más al contador
			contador = (Integer) sesion.getAttribute("accesos");
			contador++;
			sesion.setAttribute("accesos", contador);
		}

		response.getWriter().append("<h2>Información de la sesión</h2>");
		response.getWriter().append("<table><thead><tr><td>Información</td><td>Valor</td></tr></thead><tbody>");
		response.getWriter().append("<tr><td>IP cliente</td><td>" + request.getRemoteAddr() + "</td></tr>");
		response.getWriter().append("<tr><td>Identificador de la sesión</td><td>" + sesion.getId() + "</td></tr>");
		response.getWriter().append("<tr><td>Fecha Creación</td><td>" + fechaCreacion + "</td></tr>");
		response.getWriter().append("<tr><td>Fecha del último acceso</td><td>" + fechaLastSesion + "</td></tr>");
		response.getWriter().append("<tr><td>Número de accesos previos</td><td>" + sesion.getAttribute("accesos") + "</td></tr>");
		response.getWriter().append("</tbody></table></tbody></html>");
	}
}
