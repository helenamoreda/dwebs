package webservices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/today")
public class Today {

	@GET
	@Path("{ciudad}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String mostrarCodigo(@PathParam("ciudad") String ciudad, @QueryParam("format") String format)
			throws MalformedURLException, IOException, ParseException {
		Util u = new Util();
		String result = u.buscaProvincia(ciudad);
		if (format.equals("json")) {

		} else if (format.equals("xml")) {

		} else {

		}

		return result;
	}
}
