package serializers;

import model.Libro;
import model.Libros;
import utils.ProcesadorJAXB;

public class LibrosJAXB {

	public static void main(String args[]) {
		// Inicializaci�n y agregaci�n de objetos al contenedor
		Libros libros = new Libros();

		Libro libro1 = new Libro(); // Tambi�n con: ObjectFactory.createLibro();
		libro1.setTitulo("El M�dico");
		libro1.setAutor("Noah Gordon");
		libro1.setFecha("1986");

		Libro libro2 = new Libro();
		libro2.setTitulo("El Sanador de Caballos");
		libro2.setAutor("Gonzalo Giner");
		libro2.setFecha("2008");

		libros.getLibro().add(libro1);
		libros.getLibro().add(libro2);

		// SERIALIZACI�N/MARSHALLING (MEMORIA -> XML)
		System.out.println("XML: " + new ProcesadorJAXB().serializarXML(libros));

		// SERIALIZACI�N/MARSHALLING (MEMORIA -> JSON)
		System.out.println("JSON: " + new ProcesadorJAXB().serializarJSON(libros));		
	}
}