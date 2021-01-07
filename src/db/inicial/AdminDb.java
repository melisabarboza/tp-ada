package db.inicial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminDb {

	public static Connection obtenerConexion() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cursos", "root", "");
	}
}
