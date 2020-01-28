package webservices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/s1")
public class Ej1 {

	@GET
	public String get() {
		return "Peticion GET";
	}
	
	@POST
	public String post1() {
		return "Peticion POST";
	}
	
	@GET
	@Path("/fecha_hora")
	@Produces(MediaType.APPLICATION_XML)
	public String fechaHora() {
		SimpleDateFormat df = new SimpleDateFormat("EEEEE dd MMMMM yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss.SSSZ");
		Date d = Calendar.getInstance().getTime();
		String resp = "<fecha_hora><fecha>";
		resp += df.format(d) + "</fecha>";
		resp += "<hora>" + df2.format(d) + "</hora></fecha_hora>";
		return resp;
	}
	
	@GET
	@Path("/fecha")
	public String fecha() {
		SimpleDateFormat df = new SimpleDateFormat("EEEEE dd MMMMM yyyy");
		Date d = Calendar.getInstance().getTime();
		String fecha = "Hoy es " + df.format(d);
		return fecha;
	}
	
	@POST
	@Path("/hora")
	public String hora() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSSZ");
		Date d = Calendar.getInstance().getTime();
		String fecha = "Son las " + df.format(d);
		return fecha;
	}
}
