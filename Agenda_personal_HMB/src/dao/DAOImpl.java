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

import model.Empleado;
import conexion.Conexion;

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
	public boolean insertar(Empleado empleado) {
		Connection c;
		boolean valueReturn = false;
		String sql = "INSERT INTO empleado (nombre, apellidos, telmovil, telfijo, extension, foto) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getApellidos());
			statement.setInt(3, empleado.getTelmovil());
			statement.setInt(4, empleado.getTelfijo());
			statement.setInt(5, empleado.getExtension());
			statement.setString(6, empleado.getFoto());

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
	public List<Empleado> listarEmpleados() {
		Connection c;
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		String sql = "SELECT * FROM empleado";

		try {
			c = this.conexion.conectar();
			Statement statement = c.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				// Consultar campos
				String nombre = resulSet.getString("nombre");
				String apellidos = resulSet.getString("apellidos");
				String foto = resulSet.getString("foto");
				int telfijo = resulSet.getInt("telfijo");
				int telmovil = resulSet.getInt("telmovil");
				int extension = resulSet.getInt("extension");
				int id = resulSet.getInt("id");

				// Crear objeto Empleado
				Empleado empleado = new Empleado(id, telfijo, telmovil, extension, nombre, apellidos, foto);

				// Insertar objeto en colecci�n
				listaEmpleados.add(empleado);
			}

			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al consultar: " + e.getMessage());
		}

		return listaEmpleados;
	}

	@Override
	public Empleado obtenerPorId(int id) {
		Connection c;
		Empleado empleado = null;
		String sql = "SELECT * FROM empleado WHERE id= ? ORDER BY id";
		boolean encontrado = false;

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet res = statement.executeQuery();
			if (res.next() && !encontrado) {
				if (res.getInt("id") == id) {
					empleado = new Empleado(res.getInt("id"), res.getInt("telfijo"), res.getInt("telmovil"),
							res.getInt("extension"), res.getString("nombre"), res.getString("apellidos"), res.getString("foto"));
					encontrado = true;
				}
			}

			// Cierre de conexiones
			res.close();
			this.conexion.desconectar();

		} catch (SQLException e) {
			logTag.severe("Error en la obtenci�n por ID: " + e.getMessage());
		}

		return empleado;
	}
	
	public List<Empleado> empleadosPorDepartamento(int ext) {
		Connection c;
		String sql = "select * from empleado where extension = '" + ext + "'";
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();

		try {
			c = this.conexion.conectar();
			Statement statement = c.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);

			while (resulSet.next()) {
				// Consultar campos
				String nombre = resulSet.getString("nombre");
				String apellidos = resulSet.getString("apellidos");
				String foto = resulSet.getString("foto");
				int telfijo = resulSet.getInt("telfijo");
				int telmovil = resulSet.getInt("telmovil");
				int extension = resulSet.getInt("extension");
				int id = resulSet.getInt("id");
				// Crear objeto Empleado
				Empleado empleado = new Empleado(id, telfijo, telmovil, extension, nombre, apellidos, foto);

				// Insertar objeto en colecci�n
				listaEmpleados.add(empleado);
			}

			this.conexion.desconectar();
		} catch (SQLException e) {
			logTag.severe("Error al consultar: " + e.getMessage());
		}
		return listaEmpleados;
	}

	@Override
	public boolean actualizar(Empleado empleado) {
		Connection c;
		boolean valueReturn = false;
		String sql = "UPDATE empleado SET titulo=?,autor=?,fecha=? WHERE autor=?";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getApellidos());
			statement.setInt(3, empleado.getTelmovil());
			statement.setInt(4, empleado.getTelfijo());
			statement.setInt(5, empleado.getExtension());
			statement.setString(6, empleado.getFoto());

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
	public boolean eliminar(Empleado empleado) {
		Connection c;
		boolean valueReturn = false;
		String sql = "DELETE FROM empleado WHERE titulo=? and autor=? and fecha=?";

		try {
			c = this.conexion.conectar();
			PreparedStatement statement = c.prepareStatement(sql);
			statement.setString(1, empleado.getTitulo());
			statement.setString(2, empleado.getAutor());
			statement.setString(3, empleado.getFecha());
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
