package webservices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/today")
public class Today {
	
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
