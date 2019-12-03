package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class S1
 */
@WebServlet("/movies")
public class S1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S1() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String json = "{\"films\":{\"film\":[{\"titulo\":\"Blade Runner 2049\",\"director\":\"Denis Villeneuve\",\"anio\":\"2017\",\"resumen\":\"Treinta años después de los eventos del primer film, un nuevo blade runner, K (Ryan Gosling) descubre un secreto largamente oculto que podría acabar con el caos que impera en la sociedad. El descubrimiento de K le lleva a iniciar la búsqueda de Rick Deckard (Harrison Ford), un blade runner al que se le perdió la pista hace 30 años. \",\"minutos\":\"163\",\"thumbnail\":\"http://es.web.img3.acsta.net/pictures/17/08/25/11/58/463146.jpg\",\"url\":\"https://www.youtube.com/watch?v=S_JAMRKzEHs\"},{\"titulo\":\"Charlot en el teatro\",\"director\":\"Charles Chaplin\",\"anio\":\"1915\",\"resumen\":\"Charlot acude al teatro, perfectamente vestido de etiqueta. Y allí se acumulan situaciones graciosas y molestas, algunas reconocibles para cualquiera... \",\"minutos\":\"29\",\"url\":\"http://www.youtube.es\"}]}}";
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
