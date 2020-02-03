package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Clase implementa la conexi�n a acceso a bases de datos.
 * @autor: Alejandro Cardo Grau
 * @version 1.0
 */

public class Conexion {
	private Connection jdbcConnection; // Representa una conexi�n a la BD
	private String driver; // Driver de conexi�n a la BD
	private String url; // URL de acceso a BD
	private String bd; // Base de datos
	private String username; // Nombre del usuario que acceder� a la BD
	private String password; // Password del usuario que acceder� a la BD

	public Conexion(String driver, String url, String bd, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.bd = bd;
		this.username = username;
		this.password = password;
	}

	public Conexion(Properties props) {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost/";
		this.bd = "agenda_personal";
		this.username = "root";
		this.password = "";
	}

	/**
	 * Conecta a la base de datos.
	 * 
	 * @return la conexi�n a la base de datos
	 * @throws SQLException
	 *             devuelve una excepci�n si no se puede crear la conexi�n.
	 */
	public Connection conectar() throws SQLException {
		try {
			Class.forName(this.driver);
			jdbcConnection = getConexion();
		} catch (ClassNotFoundException e) {
			throw new SQLException("No encontrado el driver");
		} catch (SQLException e) {
			throw new SQLException("Error al establecer al conexi�n");
		}

		return this.jdbcConnection;
	}

	/**
	 * Cierra la conexi�n a la base de datos.
	 * 
	 * @return devuelve cierto si la conexi�n ha sido cerrada, falso en caso
	 *         contrario
	 * @throws SQLException
	 *             devuelve una excepci�n si no puede cerrar la conexi�n.
	 */
	public boolean desconectar() throws SQLException {
		boolean closed = false;

		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
			closed = true;
		}

		return closed;
	}

	/**
	 * Devuelve la conexi�n a la base de datos.
	 * 
	 * @return la conexi�n de la base de datos.
	 * @throws SQLException
	 *             devuelve una excepci�n si no se puede obtener la conexi�n.
	 */
	public Connection getConexion() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed())
			jdbcConnection = DriverManager.getConnection(url + bd, username, password);

		return jdbcConnection;
	}
}