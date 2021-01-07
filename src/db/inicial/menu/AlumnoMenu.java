package db.inicial.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.inicial.DAO.AlumnoDAO;
import db.inicial.model.Alumno;

public class AlumnoMenu {

	public static void submenuAlumnos(Scanner sc, Connection connection) throws SQLException {
		System.out.println("Submenu Alumnos");
		System.out.println();
		System.out.println("1. Alta alumno");
		System.out.println("2. Listar alumnos");
		System.out.println("3. Modificar alumno");
		System.out.println("4. Baja de alumno");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion: ");

		int opcion = sc.nextInt();
		switch (opcion) {
		case 0:
			System.out.println("Selecciono la opcion salir, gracias por utilizar nuestra plataforma");
		case 1:
			altaAlumno(sc, connection);
			break;
		case 2:
			listarAlumnos(connection);
			break;
		case 3:
			modificarAlumno(sc, connection);
			break;
		case 4:
			bajaAlumno(sc, connection);
			break;

		}
	}

	private static void bajaAlumno(Scanner sc, Connection connection) throws SQLException {
		// solicitar id alumno
		System.out.println("Baja de alumnos:");
		System.out.println();
		System.out.println("Ingrese ID del alumno: ");
		int idAlumno = sc.nextInt();
		// llamar delete usando el id
		AlumnoDAO.delete(idAlumno, connection);
		System.out.println("Alumno eliminado");
	}

	private static void modificarAlumno(Scanner sc, Connection connection) throws SQLException {
		// solicitar idAlumno
		System.out.println("Modificar alumno: ");
		System.out.println();
		System.out.println("Ingrese ID Alumno");
		int idAlumno = sc.nextInt();
		// solicitar nuevo nombre para el alumno
		System.out.println("Ingrese nuevo nombre");
		String nuevoNombre = sc.next();
		// solicitar nuevo apellido
		System.out.println("Ingrese nuevo apellido");
		String nuevoApellido = sc.next();

		// creo alumno con esos tres datos y
		Alumno alumno = new Alumno(idAlumno, nuevoNombre, nuevoApellido);
		// hacer update alumno
		AlumnoDAO.update(alumno, connection);
		// indicar que el usuario fue creado correctamente
		System.out.println("Datos modificados correctamente");

	}

	private static void listarAlumnos(Connection connection) throws SQLException {
		List<Alumno> lista = AlumnoDAO.findAll(connection);
		lista.forEach(alumno -> System.out.println(alumno.toString()));
	}

	private static void altaAlumno(Scanner sc, Connection connection) throws SQLException {
		// ingrese nombre alumno
		System.out.println("Alta de alumnos: ");
		System.out.println();
		System.out.println("Ingrese nombre de alumno: ");
		String nombreAlumno = sc.next();
		// ingrese apellido alumno
		System.out.println("Ingrese apellido del alumno: ");
		String apellidoAlumno = sc.next();

		Alumno alumno = new Alumno(nombreAlumno, apellidoAlumno);
		AlumnoDAO.insert(alumno, connection);
		// indicar que el usuario fue creado correctamente
		System.out.println("Nuevo alumno dado de alta correctamente");
	}

}
