package Cocina;
import Alimentos.*;

/**
 * Clase hija de la clases ColaComida
 * 
 */

public class ColaProduccion extends ColaComida{
    
    private int capacidadMax; //peso de las ordenes
    private int capacidadActual; //peso actual


    public ColaProduccion(int tamanoMax){
        
        super(tamanoMax);

        this.capacidadActual = 0;
        this.capacidadMax = 10;

    }


    // sobrescribe el metodo de la clase padre
    public boolean agregar(Comida objeto){

        if (objeto == null ||colaLlena()||(this.capacidadActual + objeto.getDuracion()) > this.capacidadMax) { //maxima capacidad de la cola
            return false;//no se agrega
        }

        this.capacidadActual = this.capacidadActual + objeto.getDuracion();
        colaComidas[fondo] = objeto;
        aumentarFondo();//siguiente posicion lista
        return true;


    }
    // sobreescribe el metodo de la clase padre
    public boolean quitar(){

        if (colaVacia()) { //cola vacia
            return false;//no se elimina
        }


        this.capacidadActual -= colaComidas[cabeza].getPeso(); // el peso es la duracion antes de que empezara a disminuirse
        colaComidas[cabeza] = null;
        aumentarCabeza();
        return true;


    }

    // metodo unico de cola produccion
    public void disminuirDuracionEn1(){ //disminuye la duracion de todo en la cola en 1

        int valor;
        int sostenCabeza = cabeza;

        if (colaVacia() ) {
            return;
        }

        //caso inicial
        valor = colaComidas[cabeza].getDuracion();//duracion de la comida

        if (valor > 0) {
            colaComidas[cabeza].setDuracion((valor-1)); //diminuimos si es > 0
        }

        aumentarCabeza();//movimiento del contador

        while (cabeza != fondo) {// circulamos toda la cola

            valor = colaComidas[cabeza].getDuracion();//duracion de la comida
        
            if (valor > 0) {
                colaComidas[cabeza].setDuracion((valor-1)); //diminuimos si es > 0
            }
            aumentarCabeza();//movimiento del contador

        }
        setCabeza(sostenCabeza);//retornamos cabeza a su posicion original
        return;
    }

    public boolean isIdentificacionEnCola(int identificacion){ //

        int sostenCabeza = cabeza;

        while (cabeza != fondo) {// circulamos toda la cola

            if (identificacion == colaComidas[cabeza].getIdentidad()) {
            
                setCabeza(sostenCabeza);
                return true;
    
            }
            aumentarCabeza();//movimiento del contador

        }
        setCabeza(sostenCabeza);//retornamos cabeza a su posicion original
        return false;
    }
}