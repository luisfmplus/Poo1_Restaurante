package Alimentos;

    /**
     * Clase hija de la Clase Comida. Esta clase genera objetos del tipo plato Acompanamiento
     */

public class Acompanamiento extends Comida {
    
	private String tipo ;


    public Acompanamiento(){
        super();
    }

	public Acompanamiento(int duracion, String nombre, int precio, int identidad, String tipo) {
		
        super(duracion, nombre, precio, identidad);
        this.tipo = tipo;

	}


    public Acompanamiento Clonar(){

        //implementacion para crear y devolver una copia del ojeto

        Acompanamiento copia = new Acompanamiento(getDuracion(), getNombre(), getPrecio(), getIdentidad(), this.tipo);

        return copia;
    }
    
    // los get y set
    public String getTipo() {
    return tipo;
}
    public void setTipo(String tipo) {
    this.tipo = tipo;
}

    public String toString(){

        return this.getNombre()+ ", Duracion: " +this.getDuracion()+ ", Precio: "+this.getPrecio()+ "\r\nTipo: " +this.getTipo()+ ", Identidad:"+ this.getIdentidad();
    }

}