package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class s3
 */
//@WebServlet({"/s3", "/getInfo"})
@WebServlet(name = "s3", urlPatterns = {"/s3", "/getInfo"})
public class s3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appName = this.getServletContext().getInitParameter("appName");
		
		String color = this.getServletConfig().getInitParameter("color");
		
		if (color != null) {
			//response.getWriter().append("COLOR CONFIDURADO ES: " + color);
		}			
		
		response.getWriter().append("<html><header></header><body style='background-color:"+color+"'>");
		response.getWriter().append("Protocolo: " + request.getProtocol()+"<br/>");
		response.getWriter().append("Server Port: " + request.getServerPort()+"<br/>");
		response.getWriter().append("Server Name: " + request.getServerName()+"<br/>");
		response.getWriter().append("Server Address: " + request.getLocalAddr()+"<br/>");
		response.getWriter().append("Remote Client address: " + request.getRemoteAddr()+"<br/>");
		response.getWriter().append("</body></html>");
		
	}

}
