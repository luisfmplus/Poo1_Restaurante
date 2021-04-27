package Alimentos;

    /**
     * Clase hija de la Clase Comida. Esta clase genera objetos del tipo plato Bebidas
     */

public class Bebidas extends Comida {
    
	private String tipo ;
    private String tamano;


    public Bebidas() {
        super();
    }

	public Bebidas(int duracion, String nombre, int precio, int identidad, String tamano, String tipo) {
		
        super(duracion, nombre, precio, identidad);
        this.tipo = tipo;
        this.tamano = tamano;

	}

    public Bebidas Clonar(){

        //implementacion para crear y devolver una copia del ojeto

        Bebidas copia = new Bebidas( getDuracion(), getNombre(), getPrecio(), getIdentidad(), this.tamano, this.tipo);
        return copia;
    }

    // los get
    public String getTamano() {
        return tamano;
    }
    public String getTipo() {
        return tipo;
    }
    
    //los set
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){

        return this.getNombre()+ ", Duracion: " +this.getDuracion()+ ", Precio: "+this.getPrecio()+ "\r\nTamano: " +this.getTamano()+ ", Tipo: " +this.getTipo()+ ", Identidad:"+ this.getIdentidad();
    }

}