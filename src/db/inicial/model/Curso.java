package db.inicial.model;

public class Curso {

	private int id;
	private String nombre;

	public Curso(String nombreCurso) {
		this.nombre = nombreCurso;
	}

	public Curso(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "{ id:" + this.getId() + ", nombre: " + this.getNombre() + "}";
	}

}
