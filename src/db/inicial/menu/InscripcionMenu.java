package db.inicial.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.DAO.AlumnoDAO;
import db.inicial.DAO.InscripcionDAO;
import db.inicial.model.Alumno;
import db.inicial.model.Inscripcion;

public class InscripcionMenu {

	public static void submenuInscripcion(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Submenu Inscripciones:");
		System.out.println();
		System.out.println("1. Alta de inscripcion");
		System.out.println("2. Modificar inscripción");
		System.out.println("3. Listar alumnos por curso");
		System.out.println("4. Listar notas del alumno en un curso");
		System.out.println("5. Listar inscripciones");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			altaInscripcion(sc, connection);
			break;
		case 2:
			mostrarMenuModificacionInscripcion(sc, connection);
			break;
		case 3:
			listarAlumnosInscriptosPorCurso(sc, connection);
			break;
		case 4:
			listarNotasDeInscripcionDelAlumnoEnUnCurso(sc, connection);
			break;
		case 5:
			listarInscripciones(sc, connection);
			break;
		case 0:
			System.out.println("Selecciono la opcion salir. Gracias por utilizar nuestra plataforma");
		default:
			System.err.println("Opcion invalida");
		}
	}

	private static void mostrarMenuModificacionInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println(" Modificación de Inscripciones:");
		System.out.println();
		System.out.println("1. Cambiar estado");
		System.out.println("2. Cambiar calificaciones");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			estadoInscripcion(sc, connection);
			break;
		case 2:
			insertNotas(sc, connection);
			break;
		case 0:
			System.out.println("Seleccionaste la opcion salir");
			System.out.println("Gracias por utilizar nuestra plataforma ");// salir
			break;
		default:
			System.out.println("Opcion invalida "); // opción invalida / no encontrada
		}

	}

	private static void listarAlumnosInscriptosPorCurso(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID de curso a buscar:");
		int idCurso = sc.nextInt();
		List<Alumno> listado = AlumnoDAO.findAlumnosByIdCurso(idCurso, sc, connection);
		// imprimir listado de alumnos
		System.out.println("Listado de alumnos: " + listado);
	}

	private static void listarNotasDeInscripcionDelAlumnoEnUnCurso(Scanner sc, Connection connection)
			throws SQLException {
		// pedir int idAlumno, int idCurso,
		System.out.println("Ingrese ID del curso");
		int idCurso = sc.nextInt();

		System.out.println("Ingrese ID del alumno: ");
		int idAlumno = sc.nextInt();

		List<Integer> listNotas = InscripcionDAO.findByNotasDeInscripcion(idCurso, idAlumno, sc, connection);
		// imprimir listado de notas
		System.out.println("Listado de notas: " + listNotas);
	}

	private static void estadoInscripcion(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID DEL ALUMNO a modificar: ");
		int idAlumno = sc.nextInt();
		System.out.println("Ingrese id del curso a modificar: ");
		int idCurso = sc.nextInt();

		System.out.println("Ingrese cambio de estado: 1. Estado Activo, 0. Estado Inactivo ");
		int estadoInscripcion = sc.nextInt();
		if (estadoInscripcion != 1 && estadoInscripcion != 0) {
			System.out.println("estado invaldio");
		} else {
			int filasModificadas = InscripcionDAO.cambiarEstado(idAlumno, idCurso, estadoInscripcion, sc, connection);
			if (filasModificadas == 1) {
				System.out.println("Cambio realizado con exito");
			} else {
				System.out.println("No se encontró la inscripcion");
			}
		}
	}

	private static void insertNotas(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese id de la inscripcion a modificar: ");
		int idInscripcion = sc.nextInt();
		System.out.println("Ingresar Calificaciones: ");
		System.out.println();
		System.out.println("Ingrese nota 1: ");
		int nota1 = sc.nextInt();
		System.out.println("Ingrese nota 2: ");
		int nota2 = sc.nextInt();
		int filasModificadas = InscripcionDAO.ActualizarNota(idInscripcion, nota1, nota2, sc, connection);
		if (filasModificadas == 1) {
			System.out.println("Notas actualizadas con exito");
		} else {
			System.out.println("No se actualizaron notas");
		}
	}

	private static void altaInscripcion(Scanner sc, Connection connection) throws SQLException { // ALTA INSCRIPCION
		System.out.println("Alta de inscripcion");
		System.out.println();
		System.out.println("Ingrese ID alumno:");
		int idAlumno = sc.nextInt();
		System.out.println("Ingrese ID curso:");
		int idCurso = sc.nextInt();
		System.out.println("Ingrese ID profesor: ");
		int idProfesor = sc.nextInt();
		int estadoIncripcion = 1;
		System.out.println("Ingrese comision:");
		String comision = sc.next();

		Inscripcion inscripcion = new Inscripcion(0, idAlumno, idCurso, estadoIncripcion, comision, idProfesor);
		InscripcionDAO.insert(inscripcion, sc, connection);

	}

	private static void listarInscripciones(Scanner sc, Connection connection) throws SQLException {

		List<Inscripcion> lista = InscripcionDAO.findAll(sc, connection);
		lista.forEach(inscripcion -> System.out.println(inscripcion));
	}

}
