package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import model.Libro;
import utils.Conexion;

public class DAOImpl implements DAO {
	private static final Logger logTag = Logger.getLogger(DAOImpl.class.getName());
	private Conexion conexion;
	
	public DAOImpl(String driver, String url, String bd, String username, String password) {
		this.conexion = new Conexion(driver, url, bd, username, password);
	}

	public DAOImpl(Properties props) {
		this.conexion = new Conexion(props);
	}

	@Override
	public boolean insertar(Libro libro) {
		Connection c;
		boolean valueReturn = false;
		String sql = "INSERT INTO libro (titulo, autor, fecha) VALUES (?, ?, ?)";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, libro.getTitulo());
			statement.setString(2, libro.getAutor());
			statement.setString(3, libro.getFecha());

			// Valor de retorno
			valueReturn = statement.executeUpdate() > 0;

			// Cierre de conexiones
			statement.close();
			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al insertar: " + e.getMessage());
		}

		return valueReturn;
	}

	@Override
	public List<Libro> listarLibros() {
		Connection c;
		List<Libro> listaLibros = new ArrayList<Libro>();
		String sql = "SELECT * FROM libro";

		try {
			c = this.conexion.conectar();
			Statement statement = c.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				// Consultar campos
				String titulo = resulSet.getString("titulo");
				String autor = resulSet.getString("autor");
				String fecha = resulSet.getString("fecha");

				// Crear objeto Libro
				Libro libro = new Libro(fecha, titulo, autor);

				// Insertar objeto en colecci�n
				listaLibros.add(libro);
			}

			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al consultar: " + e.getMessage());
		}

		return listaLibros;
	}

	@Override
	public Libro obtenerPorId(int id) {
		Connection c;
		Libro libro = null;
		String sql = "SELECT * FROM libro WHERE id= ? ORDER BY id";
		boolean encontrado = false;

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet res = statement.executeQuery();
			if (res.next() && !encontrado) {
				if (res.getInt("id") == id) {
					libro = new Libro(res.getInt("id"), res.getString("fecha"), res.getString("titulo"),
							res.getString("autor"));
					encontrado = true;
				}
			}

			// Cierre de conexiones
			res.close();
			this.conexion.desconectar();

		} catch (SQLException e) {
			logTag.severe("Error en la obtenci�n por ID: " + e.getMessage());
		}

		return libro;
	}

	@Override
	public boolean actualizar(Libro libro) {
		Connection c;
		boolean valueReturn = false;
		String sql = "UPDATE libro SET titulo=?,autor=?,fecha=? WHERE titulo=? and autor=? and fecha=?";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, libro.getTitulo());
			statement.setString(2, libro.getAutor());
			statement.setString(3, libro.getFecha());
			statement.setString(4, libro.getTitulo());
			statement.setString(5, libro.getAutor());
			statement.setString(6, libro.getFecha());

			// Valor de retorno
			valueReturn = statement.executeUpdate() > 0;

			// Cierre de conexiones
			statement.close();
			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al actualizar: "+ e.getMessage());
		}

		return valueReturn;
	}

	@Override
	public boolean eliminar(Libro libro) {
		Connection c;
		boolean valueReturn = false;
		String sql = "DELETE FROM libro WHERE titulo=? and autor=? and fecha=?";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, libro.getTitulo());
			statement.setString(2, libro.getAutor());
			statement.setString(3, libro.getFecha());
			// Valor de retorno
			valueReturn = statement.executeUpdate() > 0;

			// Cierre de conexiones
			statement.close();
			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al eliminar: " + e.getMessage());
		}

		return valueReturn;
	}
}
