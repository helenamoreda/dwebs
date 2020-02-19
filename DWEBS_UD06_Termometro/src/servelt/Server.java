package servelt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class Server
 */
@WebServlet("/server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APIKEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxlbmExMDk0QGhvdG1haWwuY29tIiwianRpIjoiOTVmY2M3ZjItOTQyMi00NjJjLTgwNWQtNWQ0MDdkYTNjNzBmIiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE1ODE1MjgzNjYsInVzZXJJZCI6Ijk1ZmNjN2YyLTk0MjItNDYyYy04MDVkLTVkNDA3ZGEzYzcwZiIsInJvbGUiOiIifQ.VMYViGp_4YAjoq8LpSek5zYvnYqLOl9wWy7F6UNyjJ4";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Server() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ciudad = request.getParameter("ciudad");

		/*
		 * https://www.el-tiempo.net/api/json/v1/provincias
		 */
		URLConnection connectionProvincias = new URL("https://www.el-tiempo.net/api/json/v1/provincias").openConnection();
		/*
		 * https://www.el-tiempo.net/api/json/v1/provincias/41/municipios
		 */
		URLConnection connectionMunicipio = new URL("https://www.el-tiempo.net/api/json/v1/provincias/41/municipios").openConnection();
		/*
		 * https://www.el-tiempo.net/api/json/v1/provincias/[CODPROV]/municipios/[ID]/weather
		 */
		URLConnection connectionTiempo = new URL("https://www.el-tiempo.net/api/json/v1/provincias/[CODPROV]/municipios/[ID]/weather").openConnection();
		
		InputStream is = connectionProvincias.getInputStream();
		BufferedReader input = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = input.readLine()) != null) {
			sb.append(line);
		}
		
		JSONArray c = new JSONArray(sb.toString());
		for (Object object : c) {
			JSONObject json = (JSONObject) object;
			if (json.getString("NOMBRE_PROVINCIA").equalsIgnoreCase("SEVILLA")) {
				String codprovincia = json.getString("CODPROV");
			}
		}
		JSONObject jsonObj = new JSONObject(sb.toString());
		Map<String, Object> map = jsonObj.toMap();
		

//		for (int i = 0; i < jsonObj.length(); i++) {
//		    JSONObject c = jsonObj.getJSONObject("dataArray");
//
//		    String A = c.getString("A");
//		    String B = c.getString("B");
//		    String C = c.getString("C");
//
//		}
//		
//		Gson gson = new Gson();
//		gson.toJson(sb.toString());
//		JSONObject json = new JSONObject(sb.toString());
//		json.getString("CODPROV");
//		
		
		// Cierre
		is.close();
		input.close();
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
