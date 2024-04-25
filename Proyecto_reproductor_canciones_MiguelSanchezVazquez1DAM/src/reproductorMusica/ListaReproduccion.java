package reproductorMusica;

import java.util.ArrayList;

public class ListaReproduccion {

	private String nombre;

	private ArrayList<Cancion> listaCanciones;

	public ListaReproduccion(String nombre, ArrayList<Cancion> listaCanciones) {
		super();
		this.nombre = nombre;
		this.listaCanciones = listaCanciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

}
