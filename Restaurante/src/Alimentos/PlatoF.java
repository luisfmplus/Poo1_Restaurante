package Alimentos;


    /**
     * Clase hija de la Clase Comida. Esta clase genera objetos del tipo plato fuerte
     */

public class PlatoF extends Comida {
    
	private String tamano;


    public PlatoF() {
		super();

	}

	public PlatoF(int duracion, String nombre, int precio, int identidad, String tamano) {
		
        super(duracion, nombre, precio, identidad);
        this.tamano = tamano;

	}

    public PlatoF Clonar(){

        //implementacion para crear y devolver una copia del ojeto

        PlatoF copia = new PlatoF(getDuracion(), getNombre(), getPrecio(), getIdentidad(), this.tamano);

        return copia;
    }

    //get y set
    public String getTamano() {
        return tamano;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    //metodo para imprimir el objeto
    public String toString(){

        return this.getNombre()+ ", Duracion: " +this.getDuracion()+ ", Precio: "+this.getPrecio()+ "\r\nTamano: " +this.getTamano()+ ", Identidad:"+ this.getIdentidad();
    }

}