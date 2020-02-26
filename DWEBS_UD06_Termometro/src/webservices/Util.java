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
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	private String codProvincia = "", codMunicipio = "", result;

	public String buscaProvincia(String ciudad, String cuando, String format)
			throws MalformedURLException, IOException, ParseException {
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
		return buscaPronostico(cuando, format);
	}

	public String buscaPronostico(String cuando, String format)
			throws MalformedURLException, IOException, ParseException {
		URLConnection connectionTiempo = new URL("https://www.el-tiempo.net/api/json/v1/provincias/" + codProvincia
				+ "/municipios/" + codMunicipio + "/weather").openConnection();
		
		result = conectar(connectionTiempo);
		String pronostico = "";
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		JSONObject jsonobject = new JSONObject(result);
		JSONObject pre = jsonobject.getJSONObject("prediccion");
		JSONArray dias = pre.getJSONArray("dia");

		JSONObject diaHoy = (JSONObject) dias.get(0);
		JSONObject diaManana = (JSONObject) dias.get(1);
		String fechajsonHoy = diaHoy.getJSONObject("@attributes").getString("fecha");
		String fechajsonMañana = diaHoy.getJSONObject("@attributes").getString("fecha");
		Date fechahoy = new Date();
		Date fechaMañana = sumarUnDia(fechahoy);
		if (cuando.equals("today") && formatoFecha.format(fechahoy).equals(fechajsonHoy)) {
			if (format.equals("json")) {
				pronostico = getInfoJson(diaHoy);
			} else if (format.equals("xml")) {
				pronostico = getInfoXml(diaHoy);
			}
		} else if (cuando.equals("tomorrow") && formatoFecha.format(fechaMañana).equals(fechajsonMañana)) {
			if (format.equals("json")) {
				pronostico = getInfoJson(diaManana);
			} else if (format.equals("xml")) {
				pronostico = getInfoXml(diaManana);
			}
		} else {
			pronostico = "no ha funcionado";
		}

		return pronostico;
	}

	private String getInfoXml(JSONObject dia) {
		String maxima = dia.getJSONObject("temperatura").getString("maxima");
		String minima = dia.getJSONObject("temperatura").getString("minima");
		String sensacionMaxima = dia.getJSONObject("sens_termica").getString("minima");
		String sensacionMinima = dia.getJSONObject("sens_termica").getString("minima");

		return "<prediccion><temperatura_maxima>" + maxima + "</temperatura_maxima><temperatura_minima>" + minima
				+ "</temperatura_minima><sensacion_termica_maxima>" + sensacionMaxima
				+ "</sensacion_termica_maxima><sensacion_termica_minima>" + sensacionMinima
				+ "</sensacion_termica_minima></prediccion>";
	}

	private String getInfoJson(JSONObject dia) {
		String maxima = dia.getJSONObject("temperatura").getString("maxima");
		String minima = dia.getJSONObject("temperatura").getString("minima");
		String sensacionMaxima = dia.getJSONObject("sens_termica").getString("minima");
		String sensacionMinima = dia.getJSONObject("sens_termica").getString("minima");

		return "{ temperatura_maxima: " + maxima + ", temperatura_minima: " + minima + ", sensacion_termica_maxima: "
				+ sensacionMaxima + ", sensacion_termica_minima: " + sensacionMinima + "}";
	}

	public Date sumarUnDia(Date hoy) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoy);
		cal.add(Calendar.DATE, 1);
		Date nuevaFecha = cal.getTime();
		return nuevaFecha;
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
