package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/tomorrow")
public class Tomorrow {
	@GET
	@Path("{ciudad}")
	public String mostrarCodigo(@PathParam("ciudad") String ciudad, @QueryParam("format") String format) throws MalformedURLException, IOException, ParseException {
		Util u = new Util();
		
		if (format.equals("json")) {
			u.buscaProvincia(ciudad);
		} else if (format.equals("xml")) {
		} else {
			
		}
			
		return "";
	}
}
