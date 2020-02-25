package webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	private String codProvincia = "", codMunicipio = "", result;

	public String buscaProvincia(String ciudad) throws MalformedURLException, IOException, ParseException {
		URLConnection connectionMunicipio = new URL("https://www.el-tiempo.net/api/json/v1/municipios")
				.openConnection();

		result = conectar(connectionMunicipio);

		JSONArray c = new JSONArray(result);
		for (Object object : c) {
			JSONObject json = (JSONObject) object;
			if (json.getString("NOMBRE").equalsIgnoreCase(ciudad)) {
				codProvincia = json.getString("CODPROV");
				codMunicipio = json.getString("COD_GEO");
			}
		}
		return buscaPronostico();
	}

	public String buscaPronostico() throws MalformedURLException, IOException, ParseException {
		URLConnection connectionTiempo = new URL("https://www.el-tiempo.net/api/json/v1/provincias/" + codProvincia
				+ "/municipios/" + codMunicipio + "/weather").openConnection();
		//https://www.el-tiempo.net/api/json/v1/provincias/41/municipios/41050/weather
		result = conectar(connectionTiempo);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		JSONObject jsonobject = new JSONObject(result);
		
		String pronostico = "";
		JSONObject pre = new JSONObject(jsonobject.getJSONObject("prediccion"));
		//objeto
		JSONArray dias = pre.getJSONArray("dia");
		for (Object object : dias) {
			JSONObject dia = (JSONObject) object;
			Date date1 = format.parse(dia.getString("fecha"));
			Date fechahoy = new Date();
			if (date1.equals(fechahoy)) {
				pronostico =  "hola";
			} else {
				pronostico = "nada";
			}
		}
		return pronostico;
	}

	public String conectar(URLConnection url) throws IOException {
		InputStream is = url.getInputStream();
		BufferedReader input = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = input.readLine()) != null) {
			sb.append(line);
		}
		is.close();
		input.close();

		return sb.toString();
	}
}
