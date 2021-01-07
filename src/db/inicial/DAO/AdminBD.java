package db.inicial.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminBD {

	public static Connection obtenerConexion() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cursos?serverTimezone=UTC", "root", "");
	}

	public static Connection obtenerConexion(String string) throws SQLException, ClassNotFoundException {
		Class.forName(string);
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cursos?serverTimezone=UTC", "root", "");
	}
}
