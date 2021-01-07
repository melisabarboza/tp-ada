package db.inicial.model;

public class Alumno {

	private int id;
	private String nombre;
	private String apellido;

	public Alumno(String nombreAlumno, String apellido) {
		this.nombre = nombreAlumno;
		this.apellido = apellido;
	}

	public Alumno(int id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	@Override
	public String toString() {
		return "{ id: " + this.id + ", nombre: " + this.nombre + ", apellido: " + this.apellido + "}";
	}

}
