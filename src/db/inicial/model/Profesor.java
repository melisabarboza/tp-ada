package db.inicial.model;

public class Profesor {
	private String nombre;
	private String apellido;
	private int id;

	public Profesor(int id, String nombreProfesor, String apellidoProfesor) {
		this.nombre = nombreProfesor;
		this.apellido = apellidoProfesor;
		this.id = id;
	}

	public String getNombre() {

		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idProfesor) {
		this.id = idProfesor;
	}

	@Override
	public String toString() {
		return "{ id:" + this.getId() + ", nombre: " + this.getNombre() + ", apellido: " + this.getApellido() + "}";
	}
}
