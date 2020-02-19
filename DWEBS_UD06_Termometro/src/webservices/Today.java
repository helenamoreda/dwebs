package webservices;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/today")
public class Today {
	Util u = new Util();
	@GET
	@Path("{ciudad}")
	public String mostrarCodigo(@PathParam("ciudad") String ciudad) throws MalformedURLException, IOException {
		u.buscaProvincia(ciudad);
		return "GET: Nombre: ";
	}
}
