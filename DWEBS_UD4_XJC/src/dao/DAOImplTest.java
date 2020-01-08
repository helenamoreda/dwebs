package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import model.Libro;

public class DAOImplTest {
	public static void main(String[] args) {

		Libro libro1 = new Libro("2020-01-08", "El libro amarillo", "Espinosa");
		Libro libro2 = new Libro("2020-01-08", "El libro amarillo", "Espinosa");
		Libro libro3 = new Libro("2020-01-08", "El libro amarillo", "Espinosa");
		Properties p = new Properties();
		try {
			p.load(new FileReader("res/config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DAOImpl test = new DAOImpl(p);
		test.insertar(libro1);
		
		
		
		

	}
}
