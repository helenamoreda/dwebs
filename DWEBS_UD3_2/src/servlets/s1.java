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
 * Servlet implementation class s1
 */
@WebServlet("/s1")
public class s1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if (request.getParameter("p1") != null) {
			response.getWriter().append("el parametro p1 es "+request.getParameter("p1"));
		} else {
			response.getWriter().print("no se ha definido ese parametro p1");
		}*/
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(Utilities.headWithTitle("prueba")+"<body><ul>");

		// recoger todos los parametros recibidos
		Enumeration<String> names = request.getParameterNames();
		
		// comprobar que tiene elementos
		if (names.hasMoreElements()) {
			// procesar los parametros
			while (names.hasMoreElements()) {
				String par = names.nextElement();
				response.getWriter().append("<li>");
				response.getWriter().append("Parámetro " + par + " = " + request.getParameter(par));
				response.getWriter().append("</li>");
			}
			response.getWriter().append("</ul></body></html>");
		} else {
			response.getWriter().append("No se han recibido parámetros");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
