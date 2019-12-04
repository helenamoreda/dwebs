package serializers;

import model.Libro;
import model.Libros;
import utils.ProcesadorJAXB;

public class LibrosJAXB {

	public static void main(String args[]) {
		// Inicialización y agregación de objetos al contenedor
		Libros libros = new Libros();

		Libro libro1 = new Libro(); // También con: ObjectFactory.createLibro();
		libro1.setTitulo("El Médico");
		libro1.setAutor("Noah Gordon");
		libro1.setFecha("1986");

		Libro libro2 = new Libro();
		libro2.setTitulo("El Sanador de Caballos");
		libro2.setAutor("Gonzalo Giner");
		libro2.setFecha("2008");

		libros.getLibro().add(libro1);
		libros.getLibro().add(libro2);

		// SERIALIZACIÓN/MARSHALLING (MEMORIA -> XML)
		System.out.println("XML: " + new ProcesadorJAXB().serializarXML(libros));

		// SERIALIZACIÓN/MARSHALLING (MEMORIA -> JSON)
		System.out.println("JSON: " + new ProcesadorJAXB().serializarJSON(libros));		
	}
}