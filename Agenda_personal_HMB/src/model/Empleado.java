package model;

public class Empleado {
private int id, telfijo, telmovil, extension;
private String nombre, apellidos, foto, email;



public Empleado(int telfijo, int telmovil, int extension, String nombre, String apellidos, String foto, String email) {
	super();
	this.telfijo = telfijo;
	this.telmovil = telmovil;
	this.extension = extension;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.foto = foto;
	this.email = email;
}



public Empleado(int id, int telfijo, int telmovil, int extension, String nombre, String apellidos, String foto, String email) {
	super();
	this.id = id;
	this.telfijo = telfijo;
	this.telmovil = telmovil;
	this.extension = extension;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.foto = foto;
	this.email = email;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTelfijo() {
	return telfijo;
}
public void setTelfijo(int telfijo) {
	this.telfijo = telfijo;
}
public int getTelmovil() {
	return telmovil;
}
public void setTelmovil(int telmovil) {
	this.telmovil = telmovil;
}
public int getExtension() {
	return extension;
}
public void setExtension(int extension) {
	this.extension = extension;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellidos() {
	return apellidos;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}
}
