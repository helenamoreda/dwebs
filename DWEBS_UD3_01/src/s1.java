

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// cambiar el tipo de contenido del mensaje http
		response.setContentType(Utilities.CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println(Utilities.headWithTitle("Prueba") + "<body>HOLA, ");
		LocalDateTime fecha = LocalDateTime.now();
		out.println("Son las "+fecha.getHour()+":"+fecha.getMinute()+":"+fecha.getSecond()+"</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body style=\"color: red\">HOLA POR POST</body></html>");
		// cambiar el tipo de contenido del mensaje http
		response.setContentType("text/html");
	}

}
