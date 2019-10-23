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
 * Servlet implementation class procesa
 */
@WebServlet("/procesa")
public class ProcesadorRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesadorRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("nombre") != null && request.getParameter("apellidos") != null
				&& request.getParameter("edad") != null && request.getParameter("email") != null) {
			
			response.setCharacterEncoding("utf-8");
			response.getWriter().append(Utilities.headWithTitle("Registro") + "<body><ul>");
			Enumeration <String> nombres = request.getParameterNames();
			
			while(nombres.hasMoreElements()) {
				String elemento = nombres.nextElement();
				response.getWriter().append("<li>"+elemento+": "+request.getParameter(elemento)+"</li>");
			}
			response.getWriter().append("</ul></body></html>");
		}
	}
}
