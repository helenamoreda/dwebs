package dao;

import java.util.List;

import model.Empleado;

public interface DAO {
	public boolean insertar(Empleado empleado);
	public List<Empleado> listarEmpleados();
	public List<Empleado> empleadosPorAutor(String author);
	public Empleado obtenerPorId(int id);
	public boolean actualizar(Empleado empleado);
	public boolean eliminar(Empleado empleado);
	
}
