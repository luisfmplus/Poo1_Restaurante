package Personas;

/**
 * Esta clase, a diferencia de ColaCliente permite agregar Clientes. 
 * Esta clase es una cola y permite agregar clientes hata que se llene la cola
 * 
 */


public class ListaCliente{
    
    /**
     * Manejo de errores
     * 
     * las funciones retornaran true cuando no pasa nada y
     * retornanran false cuando suceda un error
     * 
     * retornarCabeza retorna el objeto o un null
     * 
     * tambien aplica para sus clases hijas
     */


    private int tamanoMax; // tamaño de la cola
    private int cabeza; //cabeza de la cola
    private int fondo; // fin de la cola
    private Cliente colaClientes[]; // la cola

    public ListaCliente(int tamanoMax){

        this.tamanoMax = tamanoMax;
        this.colaClientes = new Cliente[tamanoMax];
        this.fondo = 0;
        this.cabeza = 0;

    }

    public boolean agregar(Cliente objeto){

        //agrega un elemento a la lista

        if (colaLlena()) { //maxima capacidad de la cola
            return false;//no se agrega
        }

        colaClientes[fondo] = objeto;
        aumentarFondo();//siguiente posicion lista
        return true;


    }
    public boolean quitar(){

        //quita un elemento en la cola

        if (colaVacia()) { //cola vacia
            return false;//no se elimina
        }

        colaClientes[cabeza] = null;
        aumentarCabeza();
        return true;


    }

    public boolean colaLlena(){
        boolean igualda = (fondo == cabeza);//si es verdadera toda la cola esta llena
        boolean valor;

        aumentarFondo();
        valor = (colaClientes[fondo] != null); //permite distinguir entre llena y vacia; verdadera == cola llena
        disminuirFondo();

        return (igualda && valor); //true = llena|| false = no llena
    }
    public boolean colaVacia(){
        boolean igualda = (fondo == cabeza); //si es verdadera esa pocision esta vacia
        boolean valor;

        aumentarFondo();
        valor = (colaClientes[fondo] == null);//permite distinguir entre llena y vacia; verdadera == cola vacia
        disminuirFondo();

        return (igualda && valor); //true = vacia|| false = no vacia
    }

    public Cliente retornarCabeza(){

        //retorna la cabeza de la cola

        if (colaVacia()) { //cola vacia
            return null;
        }

        return colaClientes[cabeza];

    }

    public void aumentarFondo(){ //aumenta; con modulo tamanomax.

        this.fondo = (this.fondo + 1)%this.tamanoMax;
        return;
    }
    public void disminuirFondo(){ //disminuye; con modulo tamanomax.

        this.fondo--;
        if (this.fondo == -1) {
            this.fondo = this.tamanoMax-1;
        }
        return;
    }
    public void aumentarCabeza(){ //aumenta; con modulo tamanomax.

        this.cabeza = (this.cabeza + 1)%this.tamanoMax;
        return;
    }
    public void disminuirCabeza(){ //disminuye; con modulo tamanomax.

        this.fondo--;
        if (this.fondo == -1) {
            this.fondo = this.tamanoMax-1;
        }
        return;
    }
    public void disminuirPaciencia(){

        int sostenCabeza = cabeza;

        while (cabeza != fondo) {// circulamos toda la cola

            if (colaClientes[cabeza].isClientepaciencia0()) {
            
                aumentarCabeza();
                continue;
    
            }
            colaClientes[cabeza].disminuirTiempoPaciencia();
            aumentarCabeza();//movimiento del contador

        }
        setCabeza(sostenCabeza);//retornamos cabeza a su posicion original
        return;
    }


    public void setCabeza(int cabeza){
        this.cabeza = cabeza;
        return;
    }

    public boolean isIdentificacionEnCola(int identificacion){ //disminuye la duracion de todo en la cola en 1

        int sostenCabeza = cabeza;

        while (cabeza != fondo) {// circulamos toda la cola

            if (identificacion == colaClientes[cabeza].getIdentificacion()) {
            
                setCabeza(sostenCabeza);
                return true;
    
            }
            aumentarCabeza();//movimiento del contador

        }
        setCabeza(sostenCabeza);//retornamos cabeza a su posicion original
        return false;
    }
    
}


