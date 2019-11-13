package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class palabras
 */
@WebServlet("/palabras")
public class Palabras extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Palabras() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		ArrayList<String> histAux;
		if (sesion.isNew()) {
			sesion.setAttribute("historial", histAux = new ArrayList<String>());
			sesion.setAttribute("numFrases", 0);
			sesion.setAttribute("numPalabras", 0);
			sesion.setAttribute("numVocales", 0);
		} else {
			histAux = (ArrayList<String>) sesion.getAttribute("historial");
			if (request.getParameter("frase") != null) {
				histAux.add((String)request.getAttribute("frase"));
			}
		}
		calcula(response, histAux, sesion);
	}

	private void calcula(HttpServletResponse response, ArrayList<String> histAux, HttpSession sesion) {
		int numPalabras = (int)sesion.getAttribute("numPalabras");
		int numVocales = (int)sesion.getAttribute("numVocales");
		for (String cad : histAux) {
			// Si la frase no está vacía, añado una frase más al contador
			if (!cad.isEmpty()) {
				sesion.setAttribute("numFrases", (int)sesion.getAttribute("numFrases")+1);
			}
			// Hago el split para ver cuántas palabras hay separadas por los espacios e incremento el contador
			int palabras = cad.split(" ").length;
			sesion.setAttribute("numPalabras", numPalabras+palabras);
			
			int vocales = cad.replaceAll("[AEIOUaeiou]", "").length();
			sesion.setAttribute("numVocales", numVocales+vocales);
		}

		try {
			response.getWriter().append("Número total de frases: " + (int)sesion.getAttribute("numFrases"));
			response.getWriter().append("Número total de palabras: " + (int)sesion.getAttribute("numVocales"));
			//response.getWriter().append("Número total de vocales: " + numVocales);
			// response.getWriter().append("Número total de consonantes (incluido símbolo
			// ñ): " + );
			// response.getWriter().append("Número total de símbolos (no incluir espacios en
			// blanco): " + );
			// response.getWriter().append("Media de número de palabras por frase: " + );
		} catch (IOException e) {
			e.printStackTrace();
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
