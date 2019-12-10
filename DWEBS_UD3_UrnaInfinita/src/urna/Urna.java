package urna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Urna
 */
@WebServlet("/urna")
public class Urna extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Urna() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		if (sesion.isNew()) {
			UrnaBolas u = new UrnaBolas();
			u.generaRojas();
			u.generaNegras();
			sesion.setAttribute("urnaBolas", u);
			response.getWriter().append(u.cuentaRojas()).append("," + u.cuentaNegras());
		} else {
			UrnaBolas urnaAux = (UrnaBolas) sesion.getAttribute("urnaBolas");
			if (request.getParameter("opcion").equals("suma")) {
				int num = (int) (Math.round(Math.random()));
				urnaAux.getBolas().add(num);
			} else if (request.getParameter("opcion").equals("resta")) {
				int num = (int) (Math.random() * urnaAux.getBolas().size() + 1);
				urnaAux.getBolas().remove(num);
			}
			sesion.setAttribute("urnaBolas", urnaAux);
			response.getWriter().append(urnaAux.cuentaRojas()).append("," + urnaAux.cuentaNegras());
		}
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
