package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/s3")
public class Ej3 {
	@GET
	@Path("add")
	public String mostrarCodigo(@QueryParam("nombre") String n, @QueryParam("edad") String edad) {
		return "GET: Nombre: " + n + ", Edad: " + edad;
	}

	@GET // <server>/ws/s3/suma?x=3&y=2
	@Path("/suma")
	public String operaDosOperandos(@Context UriInfo ui) {
		int x = 0, y = 0;

		// Obtener parámetros de la request
		MultivaluedMap<String, String> params = ui.getQueryParameters();
		// Número de parámetros: params.values().size();

		// if (params.values().size() == 2 && params.get("x") != null && params.get("y")
		// != null) {
		if (params.values().size() == 2 && params.containsKey("x") && params.containsKey("y")) {
			try {
				x = Integer.valueOf(params.get("x").get(0)); // params.getFirst("x")
				y = Integer.valueOf(params.get("y").get(0)); // params.getFirst("y")
			} catch (NumberFormatException e) {
			}
		}

		return x + " + " + y + " = " + (x + y);
	}

	/*
	 * EJERCICIO: Calcular a partir de 2 parámetros pasados por GET, que devuelva:
	 * suma, resta, multiplicación y división (mostrando operandos, operadores y
	 * resultados)
	 */
	// tambien se puede hacer con queryparam
	@GET // <server>/ws/s3/check-params?x=3&y=2
	@Path("/check-params")
	public String operaciones(@Context UriInfo ui) {
		MultivaluedMap<String, String> params = ui.getQueryParameters();

		int x = Integer.valueOf(params.get("x").get(0));
		int y = Integer.valueOf(params.get("y").get(0));
		String resultado = x + " + " + y + " = " + (x + y) + "\n";
		resultado += x + " - " + y + " = " + (x - y) + "\n";
		resultado += x + " * " + y + " = " + (x * y) + "\n";
		resultado += x + " / " + y + " = " + (x / y) + "\n";
		return resultado;
	}

	/*
	 * EJERCICIO: Calcular a partir de una petición GET y de los parámetros
	 * anio/mes/dia la fecha correspondiente: Por ejemplo si pasa <URL
	 * principal>/2020/02/10 deberá devolver: La fecha es: 10/02/2020 con el formato
	 * DD/MM/YYYY
	 */
	@GET
	@Path("{anio: \\d{4}}/{mes: \\d{2}/{dia: \\d{2}}")
	public String fecha(@PathParam("anio") String a, @PathParam("mes") String m, @PathParam("dia") String d) {
		return "la fecha es: " + d + "/" + m + "/" + a;
	}

	@GET
	@Path("cuentaletras")
	@Produces(MediaType.APPLICATION_XML)
	public String cuentaLetras(@QueryParam("p") String p, @QueryParam("l") String l) {
		int contador = 0;
		for (int n = 0; n < p.length(); n++) {
			if (Character.toString(p.charAt(n)).equals(l)) {
				contador++;
			}
		}
		String resultado = "<palabra><termino>" + p + "</termino><nveces>" + contador + "</nveces></palabra>";
		return resultado;
	}
	
	@POST
	@Path("multiplo")
	@Produces(MediaType.APPLICATION_XML)
	public String multiplo(@QueryParam("n") String n, @QueryParam("max") int max) {
		String resultado = "<multiplos n="+n+">";
		int contador = 1;
		for (int i = 0; i < max; i++) {
			resultado += "<numero>" + (Integer.parseInt(n)*contador) + "</numero>";
			contador++; 
		}
		resultado+= "</multiplos>";
		return resultado;
	}

}
