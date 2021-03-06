package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class Sesion
 */
@WebServlet("/Sesion")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String fileNameHojaCalculo = "Historial.xls";
	private static final SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sesion() {
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
		ArrayList<Date> historial;
		if (sesion.isNew()) {
			historial = new ArrayList<Date>();
			historial.add(new Date(sesion.getLastAccessedTime()));
			sesion.setAttribute("historial", historial);
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Date> histAux = (ArrayList<Date>) sesion.getAttribute("historial");
			histAux.add(new Date(sesion.getLastAccessedTime()));
			sesion.setAttribute("historial", histAux);
		}
		try {
			createXLS(response, sesion);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Creates a XLS document.
	 * 
	 * @param response
	 *            Main Response
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createXLS(HttpServletResponse response, HttpSession sesion) throws DocumentException, IOException {
		response.setContentType("application/xls");
		response.setHeader("Content-Disposition", "filename=" + fileNameHojaCalculo);
		PrintWriter out = response.getWriter();

		@SuppressWarnings("unchecked")
		ArrayList<Date> histAux = (ArrayList<Date>) sesion.getAttribute("historial");

		out.println("Fecha último acceso\tHora último acceso");
		for (Date d : histAux) {
			out.println(fecha.format(d) + "\t" + hora.format(d));
		}
	}
}
