package Alimentos;



	/**
	 * Clase padre de todos los tipos de comida (PlatoF, Bebidas y Acompanamientos)
	 * la clase en si es abstracta pero multiples metodos so utilizados por todas las clases hijas
	 * por lo cuales no son abstractas.
	 */

public abstract class Comida {
    

	private String nombre ;
	private int precio ;
	private int duracion ;
	private int identidad;
	private int peso;
	

	public Comida(){


	}

	public Comida(int duracion, String nombre, int precio, int identidad) {
		
		this.duracion = duracion;
		this.nombre = nombre;
		this.precio = precio;
		this.identidad = identidad;
		this.peso = duracion;
		
	}

	//este metodo requiere que sea implementado por todas las clases hijas.
	//la finalidad de este metodo es devolver una copia del objeto.
	public abstract Comida Clonar();


	//los get
	public String getNombre() {
		return nombre;
	}
	public int getDuracion() {
		return duracion;
	}
	public int getPrecio() {
		return precio;
	}
	public int getIdentidad() {
		return identidad;
	}
	public int getPeso() {
		return peso;
	}

	//los set
	public void setIdentidad(int identidad) {
		this.identidad = identidad;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}

}