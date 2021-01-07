package db.inicial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.inicial.model.Alumno;

public class AlumnoDAO {

	public static void insert(Alumno alumno, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO ALUMNO (nombre, apellido) VALUES(?,?)");
		stmt.setString(1, alumno.getNombre());
		stmt.setString(2, alumno.getApellido());
		stmt.executeUpdate();
	}

	public static List<Alumno> findAll(Connection connection) throws SQLException {
		List<Alumno> listaAlumnos = new ArrayList<Alumno>();
		String sql = "SELECT * FROM ALUMNO";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int idAlumno = rs.getInt(1);
			String nombreAlumno = rs.getString(2);
			String apellido = rs.getString(3);
			Alumno alumno = new Alumno(nombreAlumno, apellido);
			alumno.setId(idAlumno);
			listaAlumnos.add(alumno);
		}

		return listaAlumnos;
	}

	public static int delete(int idAlumno, Connection connection) throws SQLException {
		PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM ALUMNO WHERE ID = ?");
		prepStmt.setInt(1, idAlumno);
		return prepStmt.executeUpdate();
	}

	public static int update(Alumno alumno, Connection connection) throws SQLException {
		PreparedStatement prepStmt = connection
				.prepareStatement("UPDATE ALUMNO SET nombre = ?, APELLIDO = ? WHERE ID = ?");
		prepStmt.setString(1, alumno.getNombre());
		prepStmt.setString(2, alumno.getApellido());
		prepStmt.setInt(3, alumno.getId());
		return prepStmt.executeUpdate();
	}

	public static List<Alumno> findByName(String nombre, Connection connection) throws SQLException {

		String sql = "SELECT * FROM ALUMNO WHERE nombre LIKE '%" + nombre + "%' ORDER BY nombre";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Alumno> lista = new ArrayList<Alumno>();
		Alumno alumno = null;
		while (rs.next()) {
			alumno = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
			lista.add(alumno);
		}
		return lista;
	}

	public static Alumno findById(int id, Connection connection) throws SQLException {
		String sql = "SELECT * FROM ALUMNO WHERE ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Alumno alumno = null;
		if (rs.next()) {
			alumno = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		return alumno;
	}

	public static List<Alumno> findAlumnosByIdCurso(int idCurso, Scanner sc, Connection connection)
			throws SQLException {

		String sql = "SELECT A.* FROM ALUMNO AS A INNER JOIN INSCRIPCION AS I ON I.id_alumno = A.id WHERE I.id_curso = "
				+ idCurso + ";";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Alumno> lista = new ArrayList<Alumno>();
		Alumno alumno = null;
		while (rs.next()) {
			alumno = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3));
			lista.add(alumno);
		}
		return lista;
	}

}
