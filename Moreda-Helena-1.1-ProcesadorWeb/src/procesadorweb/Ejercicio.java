package procesadorweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Ejercicio {
	private String cadena;
	private URL url;
	private InetAddress address;
	private URLConnection urlConexion;
	private String tipoContenido;

	Ejercicio() {
	}

	public void pedirDatos() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Por favor, introduzca url/uri a analizar: ");
		this.cadena = sc.nextLine();
		sc.close();
	}

	public void analizar() {
		try {
			this.url = new URL(this.cadena);
			this.address = InetAddress.getByName(this.url.getHost());
			this.urlConexion = url.openConnection();
			this.tipoContenido = this.urlConexion.getContentType().split(";")[0];

			this.visualizarDatos();

			if (tipoContenido.equals("text/html")) {
				InputStream inputstream = urlConexion.getInputStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(inputstream));
				
				String line;
				while ((line = input.readLine()) != null) {
					// if(line.equals("")
				}
				// Cierre
				inputstream.close();
				input.close();
				this.visualizarDatosContenido();
			}


		} catch (MalformedURLException e) {
			System.out.println("Direcci�n no v�lida");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void visualizarDatosContenido() {
		System.out.println("N�mero de metaetiquetas: ");
		System.out.println("N�mero de hiperenlaces: ");
		System.out.println("N�mero de listas: ");
		System.out.println("N�mero de tablas: ");
	}
	
	public void visualizarDatos() {
		System.out.println("Direcci�n URL: " + this.url.toString());
		System.out.println("Direcci�n IP: " + this.address.getHostAddress());
		System.out.println("Puerto: " + this.url.getDefaultPort());
		System.out.println("Tipo de contenido: " + this.tipoContenido);
		System.out.println("Codificaci�n de caracteres: " + this.urlConexion.getContentType().split(";")[1]);
	}

	public static void main(String[] args) {
		Ejercicio e = new Ejercicio();
		e.pedirDatos();
		e.analizar();
	}

}
