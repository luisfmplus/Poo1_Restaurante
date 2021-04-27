package Cocina;
import Alimentos.*;

/**
 * Clase hija de la clase ColaComida
 */

public class ColaPedidosFaltantes extends ColaComida{
    
    private int pedidosTotales;


    
    public ColaPedidosFaltantes(int tamanoMax){ //tamanoMax debe ser razonablemente grande
        
        super(tamanoMax);
        this.pedidosTotales = 0;
    }

    public boolean agregar(Comida objeto){

        if (colaLlena()) { //maxima capacidad de la cola
            return false;//no se agrega
        }

        colaComidas[fondo] = objeto;
        this.pedidosTotales++;
        aumentarFondo();//siguiente posicion lista
        return true;
    }
    public boolean quitar(){

        if (colaVacia()) { //cola vacia
            return false;//no se elimina
        }

        colaComidas[cabeza] = null;
        this.pedidosTotales--;
        aumentarCabeza();
        return true;
    }

    public int getPedidosTotales(){
        return this.pedidosTotales;
    }

    public boolean isIdentificacionEnCola(int identificacion){ //disminuye la duracion de todo en la cola en 1

        if (colaVacia()) {
            return false;
        }

        if (identificacion == colaComidas[cabeza].getIdentidad()) {
            
                return true;
    
            }

        return false;
    }

}