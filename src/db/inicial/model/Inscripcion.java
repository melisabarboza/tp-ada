package db.inicial.model;

public class Inscripcion {

	public int getId_inscripcion() {
		return id_inscripcion;
	}

	public void setId_inscripcion(int id_inscripcion) {
		this.id_inscripcion = id_inscripcion;
	}

	public int getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	private int id_inscripcion;
	private int id_alumno;
	private int id_curso;
	private int estadoIncripcion = 1;
	private String comision;
	private int idProfesor;
	private int nota_1;
	private int nota_2;

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Inscripcion(int idInscripcion, int idAlumno, int idCurso, int estadoIncripcion, String comision,
			int idProfesor) {
		this.id_inscripcion = idInscripcion;
		this.id_alumno = idAlumno;
		this.id_curso = idCurso;
		this.estadoIncripcion = estadoIncripcion;
		this.comision = comision;
		this.idProfesor = idProfesor;
	}

	public Inscripcion(int idInscripcion, int idAlumno, int idCurso, int estadoIncripcion, String comision,
			int idProfesor, int nota_1, int nota_2) {
		this.id_inscripcion = idInscripcion;
		this.id_alumno = idAlumno;
		this.id_curso = idCurso;
		this.estadoIncripcion = estadoIncripcion;
		this.comision = comision;
		this.idProfesor = idProfesor;
		this.nota_1 = nota_1;
		this.nota_2 = nota_2;
	}

	public int getEstadoIncripcion() {
		return estadoIncripcion;
	}

	public void setEstadoIncripcion(int estadoIncripcion) {
		this.estadoIncripcion = estadoIncripcion;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public int getId() {
		return id_inscripcion;
	}

	public void setId(int idInscripcion) {
		this.id_inscripcion = idInscripcion;
	}

	@Override
	public String toString() {
		return "{ id:" + this.getId() + ", id_alumno: " + this.getId_alumno() + ", id_curso: " + this.getId_curso()
				+ ", estado: " + (estadoIncripcion == 1 ? "ACTIVA" : "INACTIVA") + ", comision: " + this.getComision()
				+ ", id_profesor: " + this.getIdProfesor() + "}";
	}

}
