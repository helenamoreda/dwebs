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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	private String codProvincia = "", codMunicipio = "", result;

	public void buscaProvincia(String ciudad) throws MalformedURLException, IOException, ParseException {
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
		buscaPronostico();
	}

	public void buscaPronostico() throws MalformedURLException, IOException, ParseException {
		URLConnection connectionTiempo = new URL("https://www.el-tiempo.net/api/json/v1/provincias/" + codProvincia
				+ "/municipios/" + codMunicipio + "/weather").openConnection();
		//https://www.el-tiempo.net/api/json/v1/provincias/41/municipios/41050/weather
		result = conectar(connectionTiempo);
		Map<String, String> jsonArray = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		/*JSONArray c = new JSONArray(result);*/
		JSONObject jsonobject = new JSONObject(result);
		System.out.println(jsonobject.getString("prediccion"));

		JSONArray c = new JSONArray(jsonobject.getString("prediccion"));
		for (Object object : c) {
			JSONObject prediccion = (JSONObject) object;
			jsonArray.put("prediccion", prediccion.get("prediccion").toString());
			JSONObject dia = new JSONObject(prediccion);
			jsonArray.put("dia", dia.get("dia").toString());
			Date date1 = format.parse(dia.getString("fecha"));
		}
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
