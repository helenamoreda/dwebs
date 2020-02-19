package webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	private String codProvincia = "", codMunicipio = "", result;

	public void buscaProvincia(String ciudad) throws MalformedURLException, IOException {

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

	public void buscaPronostico() throws MalformedURLException, IOException {
		URLConnection connectionTiempo = new URL("https://www.el-tiempo.net/api/json/v1/provincias/" + codProvincia
				+ "/municipios/" + codMunicipio + "/weather").openConnection();

		result = conectar(connectionTiempo);

		JSONArray c = new JSONArray(result);
		for (Object object : c) {
			JSONObject json = (JSONObject) object;
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
