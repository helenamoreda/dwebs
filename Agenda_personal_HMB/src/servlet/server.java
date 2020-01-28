package servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DAO;
import dao.DAOImpl;
import model.Empleado;

/**
 * Servlet implementation class server
 */
@WebServlet("/server")
public class server extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public server() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO dao = getDAO(request.getSession());
		Gson gson = new Gson();

		if (request.getParameter("opcion") != null && request.getParameter("opcion") != "") {
			String opcion = request.getParameter("opcion");
			int id;
			Empleado emp;
			switch (opcion) {
			case "create":
				emp = gson.fromJson(request.getParameter("empleado"), Empleado.class);
				dao.insertar(emp);
				break;
			case "update":
				id = Integer.valueOf(request.getParameter("id"));
				emp =  gson.fromJson(request.getParameter("empleado"), Empleado.class);
				dao.actualizar(emp);
				break;
			case "delete":
				id = Integer.valueOf(request.getParameter("id"));
				dao.eliminar(id);
				break;
			default:
				break;
			}
			dao.listarEmpleados();
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

	private DAO getDAO(HttpSession sesion) {
		if (sesion.isNew()) {
			Properties p = new Properties();
			try {
				p.load(new FileReader("res/config.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 2. Generar atributo en la sesi�n: objeto DAO dedicado a cada sesi�n
			sesion.setAttribute("dao", new DAOImpl(p));
		}
		return (DAO) sesion.getAttribute("dao");
	}
}
