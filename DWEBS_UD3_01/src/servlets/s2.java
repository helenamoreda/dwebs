package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class s2
 */
@WebServlet("/s2")
public class s2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.getWriter().append("Get realizado: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		resp.getWriter().append("Post realizado");
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
		PrintWriter pr = arg1.getWriter();
		pr.println("SERVICIO DISPONIBLE");
		if(arg0.getMethod().equals("GET")) {
			this.doGet(arg0, arg1);
		} else {
			this.doPost(arg0, arg1);
		}		
	}

	@Override
	public void init() throws ServletException {
		LocalDateTime fecha = LocalDateTime.now();
		super.init();
		System.out.println("Se inicia a las "+fecha.getHour()+":"+fecha.getMinute()+":"+fecha.getSecond());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("se ejecuta destroy");
	}
	
}
