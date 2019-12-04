package utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

public class ProcesadorJAXB<T> {

	/**
	 * Serialización desde una colección de objetos hacia formato XML
	 * 
	 * @param objeto
	 *            Objeto de entrada a serializar
	 * 
	 * @return cadena XML de la colección de objetos
	 */
	public String serializarXML(T object) {
		String result = null;

		try {
			// 1. Crea una instancia JAXB
			JAXBContext contexto = JAXBContext.newInstance(object.getClass());

			// 2. Crea un objeto Marshaller preparado para formatear
			Marshaller u = contexto.createMarshaller();

			// 3. Definir formato de salida
			u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			u.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			// 4. SERIALIZACIÓN (marshall) de la colección de objetos
			StringWriter stream = new StringWriter();
			u.marshal(object, stream);
			result = stream.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Serialización desde una colección de objetos hacia formato JSON
	 * 
	 * @param objeto
	 *            Objeto de entrada a serializar
	 * 
	 * @return cadena JSON de la colección de objetos
	 */
	public String serializarJSON(T object) {
		String result = null;

		try {
			// 1. Crea una instancia JAXB
			JAXBContext contexto = JAXBContext.newInstance(object.getClass());

			// 2. Crea un objeto Marshaller preparado para formatear
			Marshaller u = contexto.createMarshaller();

			// 3. Definir formato de salida
			u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			u.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			// 3. Parseo JSON usando Jettison
			Configuration config = new Configuration();
			Map<String, String> xmlToJsonNamespaces = new HashMap<String, String>(1);
			config.setXmlToJsonNamespaces(xmlToJsonNamespaces);
			MappedNamespaceConvention con = new MappedNamespaceConvention(config);

			// 4. SERIALIZACIÓN (marshall) de la colección de objetos
			StringWriter stream = new StringWriter();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, stream);
			u.marshal(object, xmlStreamWriter);
			result = stream.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}