package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		} else {
			histAux = (ArrayList<String>) sesion.getAttribute("historial");
			if (request.getParameter("frase") != null) {
				histAux.add((String) request.getAttribute("frase"));
			}
		}
		calcula(response, histAux, sesion);
	}

	private void calcula(HttpServletResponse response, ArrayList<String> histAux, HttpSession sesion) {
		int numFrases = 0, numPalabras = 0, numVocales = 0, numCons = 0, numSymbol = 0;
		for (String cad : histAux) {
			// Si la frase no está vacía, añado una frase más al contador
			if (!cad.isEmpty()) {
				numFrases++;
			}
			// Hago el split para ver cuántas palabras hay separadas por los espacios e
			// incremento el contador
			int palabras = cad.split(" ").length;
			numPalabras += palabras;
			// Creo el patron a buscar, 1 para vocales, 2 para consonantes, 3 para simbolos
			Pattern pattern1 = Pattern.compile("[aeiouáéíóú]");
			Pattern pattern2 = Pattern.compile("[^aeiouáéíóú\\w\\d]");
			Pattern pattern3 = Pattern.compile("[\\W]");
			// Aplico un matcher a la cadena usando el patron
			Matcher m1 = pattern1.matcher(cad.toLowerCase().replaceAll(" ", ""));
			Matcher m2 = pattern2.matcher(cad.toLowerCase().replaceAll(" ", ""));
			Matcher m3 = pattern3.matcher(cad.toLowerCase().replaceAll(" ", ""));
			// cada vez que el matcher encuentre una ocurrencia, sumo 1 al contador
			while (m1.find()) {
				numVocales++;
			}
			while (m2.find()) {
				numCons++;
			}
			while (m3.find()) {
				numSymbol++;
			}
		}
		try {
			int media = numPalabras / numFrases;
			response.getWriter().append("Número total de frases: " + numFrases);
			response.getWriter().append("Número total de palabras: " + numPalabras);
			response.getWriter().append("Número total de vocales: " + numVocales);
			response.getWriter().append("Número total de consonantes (incluido símbolo ñ): " + numCons);
			response.getWriter().append("Número total de símbolos (no incluir espacios en blanco): " + numSymbol);
			response.getWriter().append("Media de número de palabras por frase: " + media);
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
