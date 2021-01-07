package db.inicial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.inicial.model.Profesor;

public class ProfesorDAO {

	public static Profesor findById(int idProfesor, Connection connection) throws SQLException {
		String sql = "SELECT * FROM PROFESOR WHERE ID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, idProfesor);
		ResultSet rs = stmt.executeQuery();

		Profesor profesor = null;
		if (rs.next()) {
			profesor = new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		return profesor;
	}

	public static int delete(int idProfesor, Connection connection) throws SQLException {
		PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM PROFESOR WHERE ID = ?");
		prepStmt.setInt(1, idProfesor);
		return prepStmt.executeUpdate();
	}

	public static int update(Profesor profesor, Connection connection) throws SQLException {
		PreparedStatement prepStmt = connection.prepareStatement("UPDATE PROFESOR SET nombre = ? WHERE ID = ?");
		prepStmt.setString(1, profesor.getNombre());
		prepStmt.setInt(2, profesor.getId());
		return prepStmt.executeUpdate();

	}

	public static List<Profesor> findByName(String nombre, String apellidoProfesor, Connection connection)
			throws SQLException {
		String sql = "SELECT * FROM PROFESOR WHERE nombre LIKE '%" + nombre + "%' ORDER BY nombre";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		Profesor profesor = null;
		while (rs.next()) {
			profesor = new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3));
			listaProfesores.add(profesor);
		}
		return listaProfesores;
	}

	public static List<Profesor> findAll(Connection connection) throws SQLException {
		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		String sql = "SELECT * FROM PROFESOR";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String nombreProfesor = rs.getString(2);
			int idProfesor = rs.getInt(1);
			Profesor profesor = new Profesor(idProfesor, nombreProfesor, rs.getString(3));
			profesor.setId(idProfesor);
			listaProfesores.add(profesor);
		}

		return listaProfesores;
	}

	public static void insert(Profesor profesor, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO PROFESOR (nombre, apellido) VALUES(?,?)");
		stmt.setString(1, profesor.getNombre());
		stmt.setString(2, profesor.getApellido());
		stmt.executeUpdate();

	}
}
