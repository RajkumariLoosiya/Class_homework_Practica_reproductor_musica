package reproductorMusica;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PrincipalReproductorMusica {

	public static void main(String[] args) {

		// Esta lista la usaremos a lo largo de la ejecución, para almacenar, cual lista
		// está en uso en ese momento.

		ListaReproduccion listaSeleccionada = null;

		// En esta estructura, vamos a almacenar la colección de lista de reproducción,
		// que el usuario va a crear.
		ArrayList<ListaReproduccion> libreriaListasReproduc = new ArrayList<>();

		// En esta lista, vamos a introducir, las canciones de la lista principal, para
		// usarlas a fuego.
		ArrayList<Cancion> arrayTodasCanciones = new ArrayList<>();

		// Aquí, vamos a instanciar las diferentes canciones, que van a ir en el array
		// de arriba.
		Cancion cancion1 = new Cancion("Welcome to the jungle", "Guns ´n roses", "Rock", 564312, 5);

		Cancion cancion2 = new Cancion("Living on a prayer", "Bon jovi", "Rock", 217895, 5);

		Cancion cancion3 = new Cancion("Highway to hell", "ACDC", "Rock", 000001, 5);

		Cancion cancion4 = new Cancion("Still loving you", "Scorpions", "Rock", 6846563, 8);

		Cancion cancion5 = new Cancion("Ghost division", "Sabaton", "Rock", 98162164, 5);

		Cancion cancion6 = new Cancion("Funkytown", "Lipps inc", "Pop", 564312, 5);

		Cancion cancion7 = new Cancion("Gimme, gimme a man afer midnight", "ABBA", "PoP", 217895, 5);

		Cancion cancion8 = new Cancion("It´s raining men", "The weather girls", "Pop", 000001, 5);

		// las añadimos.
		arrayTodasCanciones.add(cancion1);

		arrayTodasCanciones.add(cancion2);

		arrayTodasCanciones.add(cancion3);

		arrayTodasCanciones.add(cancion4);

		arrayTodasCanciones.add(cancion5);

		arrayTodasCanciones.add(cancion6);

		arrayTodasCanciones.add(cancion7);

		arrayTodasCanciones.add(cancion8);

		// Aquí creamos la lista de reproducción
		ListaReproduccion listaRep1 = new ListaReproduccion("Todas las canciones", arrayTodasCanciones);

		// Antes de hacer nada, vamos a "setear" la lista por defecto, a la que vamos a
		// crear ahora.
		listaSeleccionada = listaRep1;

		// Y añadimos nuestra lista de reprodución, a la estructura para almacenarla.
		libreriaListasReproduc.add(listaRep1);

		// Con esta variable, vamos a guardar la opción del usuario.
		int op = 0;

		// Comienzo juego

		do {
			// Aquí recibimos la opción, de una forma más bonita.
			op = imprimirMenuPrincipal();

			switch (op) {
			case 1:
				reproducirEnOrden(listaSeleccionada);
				break;

			case 2:

				reproducirAleatoriamente(listaSeleccionada);

				break;

			case 3:
				reproducirDesdeIndice(listaSeleccionada);
				break;

			case 4:
				listaSeleccionada = libreriaListasReproduc.get(reproducirOtraLista(libreriaListasReproduc));
				break;

			case 5:
				crearListaNueva(libreriaListasReproduc);
				break;

			case 6:
				modificarLista(libreriaListasReproduc, listaSeleccionada);
				break;

			case 7:
				borrarListaReproduccion(libreriaListasReproduc);
				break;

			case 8:

				if (comprobacionSalida()) {
					System.out.println("Gracias por usar Reproductor de música");

					System.out.println("Aplicación terminada");
					break;
				}

			default:
				break;
			}

		} while (op < 8);

	}

	public static int imprimirMenuPrincipal() {

		Scanner sc = new Scanner(System.in);

		System.out.print("REPRODUCTOR DE MÚSICA \n\n" + "LISTAS DE REPRODUCCIÓN \n ====================== \n"
				+ "1. Reproducir música desde el principio \n" + "2. Reproducir lista aleatoriamente \n"
				+ "3. Reproducir lista desde una posición \n" + "4. Reproducir otra lista \n" + "5. Crear lista \n"
				+ "6. Modificar lista \n" + "7. Borrar lista \n" + "8. Salir \n\n" + "Elija su opción: ");

		return sc.nextInt();
	}

	public static void reproducirEnOrden(ListaReproduccion listaEntrada) {

		System.out.println(
				"Reproduciendo : " + listaEntrada.getNombre() + " " + tiempoTotalCancionesLista(listaEntrada) + "\n");
		// en este primer foreach, lo que haremos, será mostrar las canciones
		// simplemente.
		for (Cancion i : listaEntrada.getListaCanciones()) {
			System.out.print("Sonando: \n" + "\t" + i.getTitulo().toUpperCase() + "\n" + i.getAutor() + "\n\n");

			// Retardo
			try {
				TimeUnit.SECONDS.sleep(i.getDuracion());
			} catch (Exception e) {
				System.out.println("Error en la reproducción " + e);
			}

		}

	}

	// Este método se usará a lo largo del programa, para mostrar los tiempos en el
	// formato acordado, todo ello con una simple operación matemática.
	public static String tiempoTotalCancionesLista(ListaReproduccion listaEntrada) {

		int total = 0;

		for (Cancion i : listaEntrada.getListaCanciones()) {
			total = total + i.getDuracion();
		}

		return total / 60 + " : " + total % 60;
	}

	public static void reproducirAleatoriamente(ListaReproduccion listaEntrada) {

		System.out.println(
				"Reproduciendo : " + listaEntrada.getNombre() + " " + tiempoTotalCancionesLista(listaEntrada) + "\n");
		// El método es muy parecido, pero lo único que hace, es generar en este método
		// de aquí abajo, un ArrayList de integer, la cual es rellenada
		// con un math.random. Todo ello, sin que los números se repitan, para conseguir
		// esa reproducción aleatoria, más fácil, con un simple get de
		// nuestra lista de reproducción.
		for (Integer i : generarListaNumerosAleatorios(listaEntrada)) {

			System.out.print("Sonando: \n" + "\t" + listaEntrada.getListaCanciones().get(i).getTitulo().toUpperCase()
					+ "\n" + listaEntrada.getListaCanciones().get(i).getAutor() + "\n\n");

			// Retardo
			try {
				TimeUnit.SECONDS.sleep(listaEntrada.getListaCanciones().get(i).getDuracion());
			} catch (Exception e) {
				System.out.println("Error en la reproducción " + e);
			}

		}

	}

	public static ArrayList<Integer> generarListaNumerosAleatorios(ListaReproduccion listaEntrada) {

		ArrayList<Integer> listaRandoms = new ArrayList<>();

		int numeroAleatorio = 0;

		int iteraciones = 0;

		do {

			do {

				numeroAleatorio = (int) (Math.random() * listaEntrada.getListaCanciones().size());

			} while (listaRandoms.contains(numeroAleatorio));

			listaRandoms.add(numeroAleatorio);

			iteraciones++;

		} while (iteraciones < listaEntrada.getListaCanciones().size());

		return listaRandoms;
	}

	// Este método simplemente recorre dos foreach que simplemente uno empieza desde
	// la posición indicada y otro desde el principio HASTA la posición indicada.

	public static void reproducirDesdeIndice(ListaReproduccion listaEntrada) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduzca la posición de la canción inicial \n\n");

		int indice = sc.nextInt() - 1;

		if (indice >= 0 && indice < listaEntrada.getListaCanciones().size()) {

			boolean continuar = false;

			boolean parar = false;

			// Este primer bloque simplemente imprime.

			// Esta es la variable de la canción escogida por el índice.
			Cancion cancionEvitar = listaEntrada.getListaCanciones().get(indice);

			System.out.println("Reproduciendo : " + listaEntrada.getNombre() + " "
					+ tiempoTotalCancionesLista(listaEntrada) + "\n");
			System.out.println("Se inicia la reproducción desde la posición" + indice);

			// Bloque a partir del índice

			for (Cancion i : listaEntrada.getListaCanciones()) {

				if (i == cancionEvitar || continuar) {
					System.out.print("Sonando: \n" + "\t" + i.getTitulo().toUpperCase() + "\n" + i.getAutor() + "\n\n");

					// Retardo
					try {
						TimeUnit.SECONDS.sleep(i.getDuracion());
					} catch (Exception e) {
						System.out.println("Error en la reproducción " + e);
					}

					continuar = true;
				}
			}

			// Bloque antes del índice

			for (Cancion i : listaEntrada.getListaCanciones()) {

				if (i == cancionEvitar) {
					parar = true;
				}

				if (i != cancionEvitar && !parar) {

					if (i != cancionEvitar) {
						System.out.print(
								"Sonando: \n" + "\t" + i.getTitulo().toUpperCase() + "\n" + i.getAutor() + "\n\n");

						// Retardo
						try {
							TimeUnit.SECONDS.sleep(i.getDuracion());
						} catch (Exception e) {
							System.out.println("Error en la reproducción " + e);
						}

					}

				}

			}

		} else {
			System.err.println("Lo siento, esa posición, está fuera de los límites");
		}

	}

	public static int reproducirOtraLista(ArrayList<ListaReproduccion> listaListasCancionesEntrada) {

		Scanner sc = new Scanner(System.in);

		int listaSeleccionda = 0;

		int numeroImpresion = 1;

		// Nuevamente, este primer bloque es puramente impresiones.

		System.out.println("Listas disponibles:");

		for (ListaReproduccion i : listaListasCancionesEntrada) {

			System.out.println(numeroImpresion++ + ". " + i.getNombre() + " " + tiempoTotalCancionesLista(i));

		}

		System.out.println("Por favor, elija una:");

		// Aquí, vamos a escoger la opción.
		listaSeleccionda = sc.nextInt() - 1;

		if (listaSeleccionda >= 0 && listaSeleccionda < listaListasCancionesEntrada.size()) {
			System.out.println("Seleccionada la lista n º " + (listaSeleccionda + 1)
					+ listaListasCancionesEntrada.get(listaSeleccionda).getNombre()
					+ tiempoTotalCancionesLista(listaListasCancionesEntrada.get(listaSeleccionda)));

			// Todas las opciones se realizan dentro de un switch.
			// Para una impresión más cómoda, simplemente hacemos un switch de un método que
			// ya nos devuelve un int, así, en su ejecución, nos mostrará
			// el menú y nos proporcionará un valor con el que trabajar.
			switch (subMenuOpCuatro()) {
			case 1:
				reproducirEnOrden(listaListasCancionesEntrada.get(listaSeleccionda));
				break;

			case 2:
				reproducirAleatoriamente(listaListasCancionesEntrada.get(listaSeleccionda));
				break;

			case 3:
				reproducirDesdeIndice(listaListasCancionesEntrada.get(listaSeleccionda));
				break;

			default:
				System.err.println("Opción inválida, lo sentimos");
				break;
			}
			// Aquí.
			return listaSeleccionda;

		} else {
			System.err.println("Lo siento, ese número está fuera de los límites");
		}
		// En el caso que no pase nada, nos elegirá la por defecto y si ocurre el
		// correcto funcionamiento, nos devolverá la lista para setearla a la nueva
		// por defecto.
		return 0;
	}

	public static int subMenuOpCuatro() {

		int op = 0;

		Scanner sc = new Scanner(System.in);

		System.out.print(

				"Modo de reproducción: \n" + "1. Desde inicio\n" + "2. Aleatoriamente\n" + "3. Desde una posición\n"
						+ "¿Su elección? : ");

		return op = sc.nextInt();
	}

	// Con pasar nuestra lista de listas de reproducción, podemos hacerlo todo.
	public static void crearListaNueva(ArrayList<ListaReproduccion> arrayEntradaListasRep) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Teclee el nombre de la lista nueva");

		String nombre = sc.next();

		int numeroImpresion = 1;

		int eleccion = 0;

		ArrayList<Cancion> listaIntermediaCanciones = new ArrayList<>();

		ArrayList<Cancion> listaCancionesNueva = new ArrayList<>();

		System.out.println("Las canciones disponibles son : ");

		for (Cancion e : arrayEntradaListasRep.get(0).getListaCanciones()) {

			System.out.println(numeroImpresion++ + e.getTitulo());

			listaIntermediaCanciones.add(e);

		}

		// Bloque añadir canciones

		do {

			eleccion = sc.nextInt();

			if (eleccion > -1 && eleccion <= arrayEntradaListasRep.get(0).getListaCanciones().size()) {

				if (eleccion > 0) {
					listaCancionesNueva.add(listaIntermediaCanciones.get(eleccion - 1));
					System.out.println("Canción añadida");
				}

			} else {
				System.err.println("Lo siento, ese valor, está fuera de los límites");
			}

		} while (eleccion > 0);

		if (!listaCancionesNueva.isEmpty()) {
			ListaReproduccion listaRepNueva = new ListaReproduccion(nombre, listaCancionesNueva);

			arrayEntradaListasRep.add(listaRepNueva);
		} else {
			System.err.println(nombre + " no tiene canciones");
			System.err.println("No se ha podido crear " + nombre);
		}

	}

	// Nuevamente, igual que la opción anterior, simplemente, vamos a pasar nuestra
	// estructura y con una serie de foreachs podremos acceder a todos los
	// datos necesarios.
	public static void modificarLista(ArrayList<ListaReproduccion> arrayEntradaListasRep,
			ListaReproduccion listaSelec) {

		Scanner sc = new Scanner(System.in);

		int numeroImpresion = 1;

		// Nuevamente, la mayoría simplemente son bloques de impresiones.

		System.out.println("\nListas disponibles:");
		for (ListaReproduccion i : arrayEntradaListasRep) {
			System.out.println(numeroImpresion++ + ". " + i.getNombre() + " " + tiempoTotalCancionesLista(i));
		}

		System.out.println("Por favor, escoja una : \n");

		int eleccion = sc.nextInt() - 1;

		System.out.println("Seleccionada lista nº " + eleccion + 1 + arrayEntradaListasRep.get(eleccion).getNombre()
				+ " " + tiempoTotalCancionesLista(arrayEntradaListasRep.get(eleccion)));

		System.out.print("¿Qué desea hacer con la lista " + arrayEntradaListasRep.get(eleccion).getNombre() + " ? \n"
				+ "1. Añadir canciones \n" + "2. Eliminar canciones \n" + "3. Cambiar nombre \n" + "¿Su elección? : ");

		// Vamos a gestionar de nuevo, todas las opciones tras un switch.
		int op = sc.nextInt();

		if (eleccion != 0) {
			switch (op) {
			case 1:
				ejercicio6AnadirCancionALista(arrayEntradaListasRep, arrayEntradaListasRep.get(eleccion));
				break;

			case 2:
				ejercicio6BorrarTemas(arrayEntradaListasRep.get(eleccion));
				break;

			case 3:
				ejercicio6cambiarNombre(arrayEntradaListasRep.get(eleccion));
				break;

			default:
				System.err.println("Lo siento, esa opción está fuera de los límites");
				break;
			}
		} else {
			System.err.print("\nLa lista nº1 no se puede modificar\nOperación cancelada\n");
		}

	}

	public static void ejercicio6AnadirCancionALista(ArrayList<ListaReproduccion> arrayEntradaListasRep,
			ListaReproduccion listaSeleccionada) {

		Scanner sc = new Scanner(System.in);

		int numeroImpresion = 1;

		int eleccion = 0;
		// Se creará una lista intermedia, para almacenar las canciones con el nuevo
		// formato de 1.Cancion -> 2. .....
		ArrayList<Cancion> listaIntermediaCanciones = new ArrayList<>();

		System.out.println("Las canciones disponibles son : ");
		// Bloque impresión.
		for (Cancion e : arrayEntradaListasRep.get(0).getListaCanciones()) {

			System.out.println(numeroImpresion++ + e.getTitulo());

			listaIntermediaCanciones.add(e);

		}
		// Bloque añadir canciones.
		do {

			eleccion = sc.nextInt();

			if ((eleccion > -1) && (eleccion < arrayEntradaListasRep.get(0).getListaCanciones().size())) {
				if (eleccion > 0) {
					listaSeleccionada.getListaCanciones().add(listaIntermediaCanciones.get(eleccion - 1));
					System.out.println("Canción añadida");
				}
			} else {
				System.err.println("Lo siento, ese valor, está fuera de los límites");
			}

		} while (eleccion > 0);

		System.out.println("Saliendo de añadir canciones");

	}

	public static void ejercicio6BorrarTemas(ListaReproduccion listaSeleccionada) {

		int eleccion = 0;

		Scanner sc = new Scanner(System.in);

		System.out.println("\n Eliminar canciones de " + listaSeleccionada.getNombre() + "\n");

		do {

			int numeroImpresion = 1;

			for (Cancion i : listaSeleccionada.getListaCanciones()) {
				System.out.println(numeroImpresion++ + ". " + i.getTitulo() + i.getAutor() + " " + i.getDuracion());
			}

			eleccion = sc.nextInt() - 1;

			if (eleccion > -1) {
				listaSeleccionada.getListaCanciones().remove(eleccion);
			}

		} while (eleccion > 0);

	}

	public static void ejercicio6cambiarNombre(ListaReproduccion listaSeleccionada) {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n Introduce el nuevo nombre, para sustituir a " + listaSeleccionada.getNombre());

		listaSeleccionada.setNombre(sc.next());

	}

	public static void borrarListaReproduccion(ArrayList<ListaReproduccion> libreriaListasRepEntrada) {

		// Aquí, la mayoría de cosas son impresiones.
		Scanner sc = new Scanner(System.in);

		int numeroEleccion = 0;

		do {

			int numeroImpresion = 1;

			System.out.println("Listas disponibles");

			for (ListaReproduccion i : libreriaListasRepEntrada) {

				System.out.println(numeroImpresion++ + ". " + i.getNombre());

			}

			numeroEleccion = sc.nextInt();

			if (numeroEleccion == 1) {
				System.err.println("Lo siento, la lista por defecto, no se puede modificar");
			}
			// Aquí va la comprobación.
			if (numeroEleccion != 1) {
				System.out.println(
						"Seleccionada la lista " + libreriaListasRepEntrada.get(numeroEleccion - 1).getNombre());

				System.out.println("\n ¿Seguro que desea eliminar la lista de reproducción? \n");

				System.out.println(
						"Seleccionada la lista " + libreriaListasRepEntrada.get(numeroEleccion - 1).getNombre());

				System.out.println("\n Confirme con s/S si se elimina");

				String conf = sc.next();

				if (conf.equals("s") || conf.equals("S")) {
					libreriaListasRepEntrada.remove(numeroEleccion - 1);

					System.out.println("Lista eliminada");
				} else {
					System.out.println("Eliminación abortada. La lista sigue disponible \n1");
				}
			}

		} while (numeroEleccion > 0);

	}

	public static boolean comprobacionSalida() {

		boolean retornar = false;

		Scanner sc = new Scanner(System.in);

		System.out.println("Confirme con s/S si desea salir :");

		String conf = sc.next();

		if (conf.equals("s") || conf.equals("S")) {

			retornar = true;
		}
		return retornar;
	}

	// fin

}
