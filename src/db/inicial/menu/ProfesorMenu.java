package db.inicial.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.DAO.ProfesorDAO;
import db.inicial.model.Profesor;

public class ProfesorMenu {
	public static void submenuProfesores(Scanner sc, Connection connection) throws SQLException {
		System.out.println();
		System.out.println("Submenu Profesores:");
		System.out.println("1. Alta de profesor");
		System.out.println("2. Baja de profesor");
		System.out.println("3. Modificar profesor");
		System.out.println("4. Listar profesores");
		System.out.println("5. Buscar profesor por nombre");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			altaProfesor(sc, connection);
			break;
		case 2:
			bajaProfesor(sc, connection);
			break;
		case 3:
			modificarProfesor(sc, connection);
			break;
		case 4:
			listarProfesor(connection);
			break;
		case 5:
			buscarProfesorPorNombre(sc, connection);
			break;
		}
	}

	private static void buscarProfesorPorNombre(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Buscar profesor por nombre");
		System.out.println();
		System.out.println("Ingrese nombre del profesor:");
		String nombreProfesor = sc.next();
		System.out.println("Ingrese apellido del profesor");
		String apellidoProfesor = sc.next();
		List<Profesor> listaProfesores = ProfesorDAO.findByName(nombreProfesor, apellidoProfesor, connection);
		System.out.println();
		listaProfesores.forEach(profesor -> System.out.println(profesor));

	}

	private static void bajaProfesor(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID del profesor a borrar:");
		int idProfesor = sc.nextInt();
		Profesor profesorDelete = ProfesorDAO.findById(idProfesor, connection);

		if (profesorDelete != null) {
			int modificacion;
			try {
				modificacion = ProfesorDAO.delete(idProfesor, connection);
				if (modificacion == 1) {
					System.out.println("Profesor eliminado");
				} else {
					System.err.println("No se elimino profesor");
				}
			} catch (SQLException e) {
				System.err.println("Error en ejecución SQL: " + e.getMessage());
			}
		} else {
			System.err.println("No se encontró id_profesor");
		}

	}

	private static void modificarProfesor(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID del profesor a modificar:");
		int idProfesor = sc.nextInt();
		System.out.println("Ingrese nuevo nombre del profesor:");
		String nuevoNombre = sc.next();
		System.out.println("Ingrese el nuevo apellido del profesor");
		String nuevoApellido = sc.next();
		Profesor profesor = new Profesor(idProfesor, nuevoNombre, nuevoApellido);
		int updated = ProfesorDAO.update(profesor, connection);
		if (updated == 1) {
			System.out.println("Modificacion realizada, el profesor fue añadido");
		} else {
			System.err.println("Error en la modificacion de profesor " + updated);
		}

	}

	private static void listarProfesor(Connection connection) throws SQLException {
		System.out.println("LISTAR PROFESORES: ");
		List<Profesor> listaProfesores = ProfesorDAO.findAll(connection);
		listaProfesores.forEach(profesor -> System.out.println(profesor));
	}

	private static void altaProfesor(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese nombre de profesor: ");
		String nombreProfesor = sc.next();
		System.out.println("Ingrese apellido del profesor");
		String apellidoProfesor = sc.next();
		Profesor profesor = new Profesor(0, nombreProfesor, apellidoProfesor);
		ProfesorDAO.insert(profesor, connection);

	}

}
