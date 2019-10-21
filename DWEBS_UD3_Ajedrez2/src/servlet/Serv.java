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
@WebServlet(name = "Serv", urlPatterns = {"/ajedrez", "/index"})
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String cabecera = "<html><head></head><body><table style='border:solid 1px'>";
	private static final String footer = "</table></body></html>";
	private static final String color = "background-color:silver;";
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
		String tabla = "";
		//pintamos las filas
		for (int f = 1; f <= 8; f++) {
			tabla += "<tr>";
			//pintamos las columnas
			for (int c = 1; c <= 8; c++) {
				int numero = (int)(Math.random()*(12-23+1)+23);
				//si la fila y celda son pares/impares, ponemos el fondo gris
				if ((f%2==0 && c%2==0) || (f%2!=0 && c%2!=0)) {
<<<<<<< HEAD
					tabla+="<td style='"+color+"border:1px solid;width:24pt;height:24pt;'>&#98"+numero+";</td>";
				} else {
					tabla+="<td style='border:1px solid;width:24pt;height:24pt;'>&#98"+numero+";</td>";
=======
					tabla+="<td style='"+color+"border:1px solid;width:30px;height:30px;'>&#98"+numero+";</td>";
				} else {
					tabla+="<td style='border:1px solid;width:30px;height:30px;'>&#98"+numero+";</td>";
>>>>>>> master
				}
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
