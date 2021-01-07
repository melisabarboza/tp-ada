package db.inicial.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.DAO.CursoDAO;
import db.inicial.model.Curso;

public class CursoMenu {

	private static void bajaCurso(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese ID del curso a borrar:");
		int idCurso = sc.nextInt();
		Curso cursoDelete = CursoDAO.findById(idCurso, connection);

		if (cursoDelete != null) {
			int modificadas = CursoDAO.delete(idCurso, connection);
			if (modificadas == 1) {
				System.out.println("Baja realizada");
			} else {
				System.err.println("No se realizó la baja");
			}
		} else {
			System.err.println("No se encontró el curso");
		}

	}

	private static void modificarCursos(Scanner sc, Connection connection) throws SQLException {

		System.out.println("Ingrese ID del curso a modificar:");
		int idCurso = sc.nextInt();
		System.out.println("Ingrese nuevo nombre del curso:");
		String nuevoNombre = sc.next();
		Curso curso = new Curso(nuevoNombre);
		curso.setId(idCurso);
		int updated = CursoDAO.update(curso, connection);
		if (updated == 1) {
			System.out.println("Modificacion realizada");
		} else {
			System.err.println("Error en la modificacion de curso: actualizados = " + updated);
		}
	}

	private static void listaCursos(Connection connection) throws SQLException {
		System.out.println("LISTA CURSOS:");
		List<Curso> listaCursos = CursoDAO.findAll(connection);
		for (Curso curso : listaCursos) {
			System.out.println(curso);
		}
	}

	private static void altaCurso(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Ingrese nombre curso: ");
		String nombreCurso = sc.next();
		Curso curso = new Curso(nombreCurso);
		CursoDAO.insert(curso, connection);

	}

	public static void submenuCurso(Scanner sc, Connection connection) throws SQLException {
		System.out.println();
		System.out.println("Submenu Curso:");
		System.out.println("1. Alta curso");
		System.out.println("2. Listar cursos");
		System.out.println("3. Modificar curso");
		System.out.println("4. Baja curso");
		System.out.println("5. Buscar curso por nombre");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");
		int opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			altaCurso(sc, connection);
			break;
		case 2:
			listaCursos(connection);
			break;
		case 3:
			modificarCursos(sc, connection);
			break;
		case 4:
			bajaCurso(sc, connection);
			break;
		case 5:
			buscarCursoPorNombre(sc, connection);
			break;
		}
	}

	private static void buscarCursoPorNombre(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Buscar curso por nombre");
		System.out.println();
		System.out.println("Ingrese nombre del curso:");
		String nombre = sc.next();
		List<Curso> listaCursos = CursoDAO.findByName(nombre, connection);
		System.out.println();
		listaCursos.forEach(curso -> System.out.println(curso));
	}

}
