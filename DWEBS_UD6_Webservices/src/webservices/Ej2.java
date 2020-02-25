package webservices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/s2")
public class Ej2 {

	@GET
	@Path("{nombre}")
	public String mostrarNombre(@PathParam("nombre") String n) {
		return "Hola " + n;
	}

	@GET
	@Path("{numero: \\d+}")
	public String mostrarDigitos(@PathParam("numero") String n) {
		return "Hay " + n.length() + " digitos";
	}

	@GET
	@Path("{codigo: [a-z]{2}[-][0-9]{3}}")
	public String mostrarCodigo(@PathParam("codigo") String n) {
		return "El codigo es " + n;
	}

	@POST
	@Path("{num: \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public String calculaFactorial(@PathParam("num") String numero) {
		int contador = Integer.parseInt(numero);
		int result = 0;
		while (contador > 0) {
			result *= contador;
			contador--;
		}
		return "<factorial> n=\"" + numero + "\">" + String.valueOf(result) + "</factorial>";
	}

	@POST
	@Path("{cadena: \\d+}")
	@Produces(MediaType.APPLICATION_XML)
	public String calculaDigitos(@PathParam("cadena") String cad) {
		int contador = 0;
		Matcher encuentrador = Pattern.compile("\\d+").matcher(cad);
		while (encuentrador.find()) {
			contador++;
		}
		return "El número de dígitos es " + String.valueOf(contador);
	}
	
	/*
	 * EJERCICIO: Calcular a partir de una petición GET y de los parámetros
	 * anio/mes/dia la fecha correspondiente: Por ejemplo si pasa <URL
	 * principal>/2020/02/10 deberá devolver: La fecha es: 10/02/2020 con el formato
	 * DD/MM/YYYY
	 */
}
