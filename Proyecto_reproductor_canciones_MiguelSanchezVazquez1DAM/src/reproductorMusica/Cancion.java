package reproductorMusica;

public class Cancion {

	private String titulo;

	private String autor;

	private String genero;

	private int codigoId;

	private int duracion;

	public Cancion(String titulo, String autor, String genero, int codigoId, int duracion) {
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.codigoId = codigoId;
		this.duracion = duracion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getCodigoId() {
		return codigoId;
	}

	public void setCodigoId(int codigoId) {
		this.codigoId = codigoId;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

}
