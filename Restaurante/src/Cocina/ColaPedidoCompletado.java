package Cocina;

/**
 * Clase hija de clase ColaComida.
 * 
 */

public class ColaPedidoCompletado extends ColaComida{
    

    public ColaPedidoCompletado(int tamanoMax){ //tamanoMax debe ser razonable
        
        super(tamanoMax);
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