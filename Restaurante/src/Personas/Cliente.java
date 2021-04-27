package Personas;

import Alimentos.*;
import java.util.Random;
import Administracion.Menu;

    /**
     * Clase padre de las clases ClienteRegular y ClienteKaren.
     * 
     * Actualmente la Clase ClienteRegular es tan similar a su clase padre que no posee metodos propios,
     * sin embargo, por razones de herencia y de orden, se prefirio solo usar las clases hijas.
     */

public abstract class Cliente {

    private int tiempoEspera; //define el numero de tick que se requieren antes de ordenar
    private int identificacion; // numero unico que le es asignado al cliente
    private int numeroOrdenes; // total de pedidos que el cliente ordenara
    private Random numero; // generador de numeros aleatorios

    private PlatoF ordenesPlatof; // objeto temporal durante la creacion del arreglo ordenesComida[]
    private Acompanamiento ordenesAcompanamiento; // objeto temporal durante la creacion del arreglo ordenesComida[]
    private Bebidas ordenesBebidas; // objeto temporal durante la creacion del arreglo ordenesComida[]
    private Comida ordenesComida[]; // arreglo donde se almacena las ordenes del cliente



    public Cliente(int identificacion, int tiempoEspera){

        this.numero = new Random();
        this.identificacion = identificacion;
        this.tiempoEspera = tiempoEspera;

        // se define el tamaño del arreglo de forma aleatoria entre 1 y 5
        int i = (numero.nextInt(5) + 1);
        this.numeroOrdenes = i;
        this.ordenesComida = new Comida[i];

    }

    public void leerMenu(Menu menu){
        /**
         * leerMenu lo que realiza es una lectura aleatoria de los valores almacenados en el objeto menu.
         * Despues realiza un arreglo, y coloca los archivos leeidos y modificados localmente, ahí.
         * 
         * De esta forma cada cliente sabe que ordenar exatamente.
         * El tamaño de este arreglo es definido durante el constructor de la clase
         */

        int k;

        for (int i = 0; i < ordenesComida.length; i++) {
            
            k = numero.nextInt(3);

            switch (k) {
                case 0:
                
                    ordenesPlatof = (menu.getAleatorioPlatofs().Clonar());
                    asignarTamano(ordenesPlatof);
                    asignarIdentificacion(ordenesPlatof);
                    ordenesComida[i] = ordenesPlatof;
                    break;

                case 1:
                    ordenesAcompanamiento = (menu.getAleatorioAcompanamientos().Clonar());
                    asignarTipo(ordenesAcompanamiento);
                    asignarIdentificacion(ordenesAcompanamiento);
                    ordenesComida[i] = ordenesAcompanamiento;
                    break;

                case 2:
                    ordenesBebidas = (menu.getAleatorioBebidas().Clonar());
                    asignarTamano(ordenesBebidas);
                    asignarTipo(ordenesBebidas);
                    asignarIdentificacion(ordenesBebidas);
                    ordenesComida[i] = ordenesBebidas;
                    break;
            
                default:
                    break;
            }
            
        }
    }

    public Comida[] ordenarPedido(){ 

        //permite el traspaso del arreglo con las ordenes del cliente a otras clases. en este caso, la clase produccion

        return ordenesComida;
    }

    //metodos apara asignar a el valor que el cliente desea a la orden correcta
    //se utiliza la palabra signar para evitar confundirse con los "get" y "set" propios
    //ejemplo: que un platoF sea de tamano Grande o Mediano
    private void asignarTamano(PlatoF objeto){ //indica si es "grande" o "pequeno" (lo que diga el json)
        
        String partes[] = objeto.getTamano().split("/");
        String opcion1 = partes[0];
        String opcion2 = partes[1];
        int i = numero.nextInt(1);
        if (i == 0) {
            objeto.setTamano(opcion1);
        } else {
            objeto.setTamano(opcion2);
        }
    }
    private void asignarTamano(Bebidas objeto){//indica si es "grande" o "pequeno" (lo que diga el json)
        
        String partes[] = objeto.getTamano().split("/");
        String opcion1 = partes[0];
        String opcion2 = partes[1];
        String opcion3 = partes[2];
        int i = numero.nextInt(3);
        if (i == 0) {
            objeto.setTamano(opcion1);
        } else if (i == 1){
            objeto.setTamano(opcion2);
        } else {
            objeto.setTamano(opcion3);
        }
    }
    private void asignarTipo(Acompanamiento objeto){//indica si es "dulce" o "salado" (lo que diga el json)
    
        String partes[] = objeto.getTipo().split("/");
        String opcion1 = partes[0];
        String opcion2 = partes[1];
        int i = numero.nextInt(1);
        if (i == 0) {
            objeto.setTipo(opcion1);
        } else {
            objeto.setTipo(opcion2);
        }
    }
    private void asignarTipo(Bebidas objeto){//indica si es "dulce" o "salado" (lo que diga el json)
        String partes[] = objeto.getTipo().split("/");
        String opcion1 = partes[0];
        String opcion2 = partes[1];
        int i = numero.nextInt(1);
        if (i == 0) {
            objeto.setTipo(opcion1);
        } else {
            objeto.setTipo(opcion2);
        }
    }

    // similar a las otras funcions asignar, sin embargo, bajo ninguna circunstancia debe este metodo ser llamado
    // y de forma consecuente asignar un 0 como identidad ala orden del cliente.
    // pues de ser así, podria ocurrir que el programa entero deje de funcionar correctamente
    private void asignarIdentificacion(Comida objeto){

        objeto.setIdentidad(this.identificacion);
        return;

    }


    public void disminuirTiempoEspera() {//el minimo es 0
    
        //disminuye la variable local de tiempoEspera en 1

        if (this.tiempoEspera == 0) {
            return;
        }

        this.tiempoEspera--;
    }
    public void disminuirTiempoEspera(int cantidad) {//el minimo es 0
    
        //disminuye la variable local de tiempoEspera segun lo que se indique.
        
        //cantidad debe ser positiva

        if ((this.tiempoEspera == 0) || (cantidad <=0)) {
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            this.tiempoEspera--;
        }
    }


    /*public Comida[] recibirPedido(){

        //Originalmente pretendia servir como metodo para recibir a la comida de la clase produccion
        //actualmente esta funcion no cumple proposito alguno

        return this.ordenesComida;
    }*/

    

    public boolean isClientepaciencia0(){

        //como solamente los clientes especiales tienen un contador de paciencia, 
        //significa que todos los clientes que no sean especiales siempre informaran 
        //que efectivamente su contador no existente es diferente de 0

        //los clientes especiales tienen este metodo sobreescrito

        return false;
    }


    //Esta funcion existe aqui de forma que permita el uso posterior de la misma en otras circunstancias
    // donde tengo clientes regulares y clientes especiales combinados.

    //esta funcion es inutil cuando se trata de clientes regulares, sin emargo eso no implica que no deban tener la funcion
    // a su disposicion
    public void disminuirTiempoPaciencia(){};//disminuye en 1


    // los get
    public int getIdentificacion() {
        return identificacion;
    }
    public int getTiempoEspera() {
        return tiempoEspera;
    }
    public int getTiempoPaciencia(){
        return -1;
    }


    // los set
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
    public void setOrdenesComida(Comida[] ordenesComida) {
        this.ordenesComida = ordenesComida;
    }
}