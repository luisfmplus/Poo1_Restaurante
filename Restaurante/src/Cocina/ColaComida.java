package Cocina;
import Alimentos.*;

/**
 * Clase padre de las clases Cola
 */

public abstract class ColaComida {
    
    /**
     * Manejo de errores
     * 
     * las funciones retornaran true cuando no pasa nada y
     * retornaran false cuando suceda un error
     * 
     * retornarCabeza retorna el objeto o un null
     * 
     * tambien aplica para sus clases hijas
     */


    protected int tamanoMax; // tamaño de la cola
    protected int cabeza; //cabeza de la cola
    protected int fondo; // fin de la cola
    protected Comida colaComidas[]; // la cola
    private Comida colacomida5[];

    public ColaComida(int tamanoMax){
        
        this.tamanoMax = tamanoMax;
        this.colaComidas = new Comida[tamanoMax];
        this.colacomida5 = new Comida[5];
        this.fondo = 0;
        this.cabeza = 0;

    }

    public boolean agregar(Comida objeto){

        if (colaLlena()) { //maxima capacidad de la cola
            return false;//no se agrega
        }

        colaComidas[fondo] = objeto;
        aumentarFondo();//siguiente posicion lista
        return true;


    }
    public boolean quitar(){

        if (colaVacia()) { //cola vacia
            return false;//no se elimina
        }

        colaComidas[cabeza] = null;
        aumentarCabeza();
        return true;


    }

    protected boolean colaLlena(){
        boolean igualda = (fondo == cabeza);//si es verdadera toda la cola esta llena
        boolean valor;

        aumentarFondo();
        valor = (colaComidas[fondo] != null); //permite distinguir entre llena y vacia; verdadera == cola llena
        disminuirFondo();

        return (igualda && valor); //true = llena|| false = no llena
    }
    protected boolean colaVacia(){
        boolean igualda = (fondo == cabeza); //si es verdadera esa pocision esta vacia
        boolean valor;

        aumentarFondo();
        valor = (colaComidas[fondo] == null);//permite distinguir entre llena y vacia; verdadera == cola vacia
        disminuirFondo();

        return (igualda && valor); //true = vacia|| false = no vacia
    }

    public Comida retornarCabeza(){

        if (colaVacia()) { //cola vacia
            return null;
        }

        return colaComidas[cabeza];

    }

    protected void aumentarFondo(){ //aumenta; con modulo tamanomax.

        this.fondo = (this.fondo + 1)%this.tamanoMax;
        return;
    }
    protected void disminuirFondo(){ //disminuye; con modulo tamanomax.

        this.fondo--;
        if (this.fondo == -1) {
            this.fondo = this.tamanoMax-1;
        }
        return;
    }
    protected void aumentarCabeza(){ //aumenta; con modulo tamanomax.

        this.cabeza = (this.cabeza + 1)%this.tamanoMax;
        return;
    }
    protected void disminuirCabeza(){ //disminuye; con modulo tamanomax.

        this.cabeza--;
        if (this.cabeza == -1) {
            this.cabeza = this.tamanoMax-1;
        }
        return;
    }

    protected void setCabeza(int cabeza){
        this.cabeza = cabeza;
        return;
    }

    public boolean isIdentificacionEnCola(int identificacion){ //disminuye la duracion de todo en la cola en 1

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
    
    public Comida[] getComidas(){

        int sostenCabeza = this.cabeza;
        int i = 0;

        while (i < colacomida5.length) {
            
            colacomida5[i] = retornarCabeza();
            aumentarCabeza();
            i++;
        }

        this.cabeza = sostenCabeza;
        return colacomida5;

    }

}