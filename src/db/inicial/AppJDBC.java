package db.inicial;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.inicial.DAO.AdminBD;
import db.inicial.menu.AlumnoMenu;
import db.inicial.menu.CursoMenu;
import db.inicial.menu.InscripcionMenu;
import db.inicial.menu.ProfesorMenu;

// Menu: 1.cursos 2:alumnos 3:inscripciones
// Necesitamos alta, listado, modificacion y baja de alumno, curso y profesor
// Inscripcion, cancelacion, listados (varios)

public class AppJDBC {

	public static void main(String[] args) {

		try {
			Connection connection = AdminBD.obtenerConexion("com.mysql.cj.jdbc.Driver");

			Scanner sc = new Scanner(System.in);
			System.out.println("SISTEMA DE CURSOS");
			System.out.println();
			int opcion = menuOpciones(sc);
			while (opcion != 0) {

				switch (opcion) {
				case 1:
					CursoMenu.submenuCurso(sc, connection);
					break;
				case 2:
					AlumnoMenu.submenuAlumnos(sc, connection);
					break;
				case 3:
					InscripcionMenu.submenuInscripcion(sc, connection);
					break;
				case 4:
					ProfesorMenu.submenuProfesores(sc, connection);
					break;
				}
				opcion = menuOpciones(sc);
			}
			connection.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Error en carga de Clase driver: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error en ejecución SQL: " + e.getMessage());
		}

	}

	private static int menuOpciones(Scanner sc) {
		System.out.println();
		System.out.println("Menu:");
		System.out.println("1. Curso");
		System.out.println("2. Alumnos");
		System.out.println("3. Inscripción");
		System.out.println("4. Profesor ");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		return sc.nextInt();
	}
}
