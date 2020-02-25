package webservices;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/tomorrow")
public class Tomorrow {
	@GET
	@Path("{ciudad}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String mostrarCodigo(@PathParam("ciudad") String ciudad, @QueryParam("format") String format) throws MalformedURLException, IOException, java.text.ParseException {
		Util u = new Util();
		String result = u.buscaProvincia(ciudad);
		if (format.equals("json")) {

		} else if (format.equals("xml")) {

		} else {

		}
			
		return result;
	}
}
