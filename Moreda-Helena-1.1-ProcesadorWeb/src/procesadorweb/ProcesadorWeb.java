package procesadorweb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcesadorWeb {
	private String cadena, tipoContenido, encoding;
	private URL url;
	private InetAddress address;
	private URLConnection urlConexion;
	private HashMap<String, Integer> etiquetas;

	ProcesadorWeb() {
		etiquetas = new HashMap<>();
		etiquetas.put("<meta", 0);
		etiquetas.put("<a", 0);
		etiquetas.put("<ul", 0);
		etiquetas.put("<ol", 0);
		etiquetas.put("<table", 0);
		this.tipoContenido = "";
		this.cadena = "";
		this.encoding = "";
	}

	/**
	 * método para pedir datos de entrada
	 */
	public void pedirDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Por favor, introduzca url/uri a analizar: ");
		this.cadena = sc.nextLine();
		// Si no incluye el protocolo, se lo añadimos
		if (!cadena.startsWith("http")) {
			this.cadena = "http://" + cadena;
		}
		sc.close();
	}

	/**
	 * método para analizar la url
	 */
	public void analizar() {
		try {
			this.url = new URL(this.cadena);
			this.address = InetAddress.getByName(this.url.getHost());
			this.urlConexion = url.openConnection();
			this.tipoContenido = this.urlConexion.getContentType().split(";")[0];
			
			// si la url no comienza por http, se lo añadimos
			if (this.urlConexion.getContentType().split(";").length > 1) {
				this.encoding = this.urlConexion.getContentType().split(";")[1].split("charset=")[1];
			}

			this.visualizarDatos();

			// Buscamos las etiquetas solo en el caso de ser una página HTML
			if (tipoContenido.equals("text/html")) {
				InputStream inputstream = urlConexion.getInputStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(inputstream));
				String line;
				// Recorro línea a línea el contenido HTML de la url
				while ((line = input.readLine()) != null) {
					// Recorro el map de todas las etiquetas para buscar coincidencias en la línea
					for (Map.Entry<String, Integer> entry : etiquetas.entrySet()) {
						// Genero un array donde cada posición incluye la etiqueta (usando expresión
						// regular) que estamos recorriendo en caso de coincidencia
						String[] array = line.split("(?<=" + entry.getKey() + ")");
						// Recorremos el array anterior para ver si cada posición contiene la etiqueta
						// que estamos recorriendo
						for (String linea : array) {
							if (linea.contains(entry.getKey())) {
								// Si la etiqueta está incluida sumamos uno al contador
								entry.setValue(entry.getValue() + 1);
							}
						}
					}
				}
				// Cierre
				inputstream.close();
				input.close();
				// Pintamos el resultado
				this.visualizarDatosContenido();
			}
			// excepción en caso de url mal formada
		} catch (MalformedURLException e) {
			System.out.println("Dirección no válida");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void visualizarDatosContenido() {
		System.out.println("Número de metaetiquetas: " + etiquetas.get("<meta"));
		System.out.println("Número de hiperenlaces: " + etiquetas.get("<a"));
		System.out.println("Número de listas: " + (etiquetas.get("<ol") + etiquetas.get("<ul")));
		System.out.println("Número de tablas: " + etiquetas.get("<table"));
	}

	public void visualizarDatos() {
		System.out.println("Dirección URL: " + this.url.toString());
		System.out.println("Dirección IP: " + this.address.getHostAddress());
		System.out.println("Puerto: " + this.url.getDefaultPort());
		System.out.println("Tipo de contenido: " + this.tipoContenido);
		System.out.println("Codificación de caracteres: " + this.encoding);
	}

	// getters y setters

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public URLConnection getUrlConexion() {
		return urlConexion;
	}

	public void setUrlConexion(URLConnection urlConexion) {
		this.urlConexion = urlConexion;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public HashMap<String, Integer> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(HashMap<String, Integer> etiquetas) {
		this.etiquetas = etiquetas;
	}
}
