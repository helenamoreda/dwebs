package webservices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/today")
public class Today {
	
	@GET
	@Path("{ciudad}")
	public String mostrarCodigo(@PathParam("ciudad") String ciudad) throws MalformedURLException, IOException, ParseException {
		Util u = new Util();
		u.buscaProvincia(ciudad);
		return "";
	}
}
