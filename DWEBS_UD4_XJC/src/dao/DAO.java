package dao;

import java.util.List;

import model.Libro;

public interface DAO {
	public boolean insertar(Libro libro);
	public List<Libro> listarLibros();
	public Libro obtenerPorId(int id);
	public boolean actualizar(Libro libro);
	public boolean eliminar(Libro libro);
}
