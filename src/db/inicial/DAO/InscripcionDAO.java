package db.inicial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.inicial.model.Inscripcion;

public class InscripcionDAO {

	public static List<Integer> findById(int idInscripcion, Connection connection) throws SQLException {

		String sql = "SELECT * FROM INSCRIPCION WHERE ID = ?";

		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Integer> IdInscripciones = new ArrayList<Integer>();
		while (rs.next()) {
			IdInscripciones.add(rs.getInt(1));
			IdInscripciones.add(rs.getInt(2));
		}
		return IdInscripciones;
	}

	public static List<Integer> findByNotasDeInscripcion(int idCurso, int idAlumno, Scanner sc, Connection connection)
			throws SQLException {
		String sql = "SELECT nota_1, nota_2 FROM INSCRIPCION WHERE id_alumno = " + idAlumno + " AND id_curso= "
				+ idCurso + ";";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Integer> NotasAlumnosPorCurso = new ArrayList<Integer>();
		while (rs.next()) {
			NotasAlumnosPorCurso.add(rs.getInt(1));
			NotasAlumnosPorCurso.add(rs.getInt(2));
		}
		return NotasAlumnosPorCurso;
	}

	public static int cambiarEstado(int idInscripcion, int estadoInscripcion, Scanner sc, Connection connection)
			throws SQLException {
		PreparedStatement prepStmt = connection
				.prepareStatement("UPDATE INSCRIPCION SET estado_inscripcion = ? WHERE ID = ?");
		prepStmt.setInt(1, estadoInscripcion);
		prepStmt.setInt(2, idInscripcion);
		return prepStmt.executeUpdate();
	}

	public static int ActualizarNota(int idInscripcion, int nota1, int nota2, Scanner sc, Connection connection)
			throws SQLException {
		PreparedStatement prepStmt = connection
				.prepareStatement("UPDATE INSCRIPCION SET nota_1 = ?, nota_2= ? WHERE ID = ?");
		prepStmt.setInt(1, nota1);
		prepStmt.setInt(2, nota2);
		prepStmt.setInt(3, idInscripcion);
		return prepStmt.executeUpdate();
	}

	public static void insert(Inscripcion inscripcion, Scanner sc, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(
				"INSERT INTO INSCRIPCION (id_alumno, id_curso, comision, estado_inscripcion, id_profesor) VALUES(?,?,?,?,?)");
		stmt.setInt(1, inscripcion.getId_alumno());
		stmt.setInt(2, inscripcion.getId_curso());
		stmt.setString(3, inscripcion.getComision());
		stmt.setInt(4, inscripcion.getEstadoIncripcion());
		stmt.setInt(5, inscripcion.getIdProfesor());
		stmt.executeUpdate();
	}

	public static int cambiarEstado(int idAlumno, int idCurso, int estadoInscripcion, Scanner sc, Connection connection)
			throws SQLException {
		PreparedStatement prepStmt = connection
				.prepareStatement("UPDATE INSCRIPCION SET estado_inscripcion = ? WHERE id_alumno = ? and id_curso = ?");
		prepStmt.setInt(1, estadoInscripcion);
		prepStmt.setInt(2, idAlumno);
		prepStmt.setInt(3, idCurso);
		return prepStmt.executeUpdate();
	}

	public static List<Inscripcion> findAll(Scanner sc, Connection connection) throws SQLException {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		String sql = "SELECT * FROM INSCRIPCION";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int idInscripcion = rs.getInt(1);
			int idAlumno = rs.getInt(2);
			int idCurso = rs.getInt(3);
			int estadoIncripcion = rs.getInt(5);
			String comision = rs.getString(6);
			int idProfesor = rs.getInt(4);
			int nota_1 = rs.getInt(7);
			int nota_2 = rs.getInt(8);
			Inscripcion inscrpcion = new Inscripcion(idInscripcion, idAlumno, idCurso, estadoIncripcion, comision,
					idProfesor, nota_1, nota_2);
			inscripciones.add(inscrpcion);
		}

		return inscripciones;
	}

}
