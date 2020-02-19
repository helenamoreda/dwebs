package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/tomorrow")
public class Tomorrow {
	@GET
	@Path("{ciudad}")
	public String mostrarCodigo(@PathParam("ciudad") String ciudad) {
		return "GET: Nombre: ";
	}
}
