package Personas;

import Administracion.Menu;

import java.util.Random;

/**
 * Ninguno de Los Clientes puede tener identificacion = 0. 
 * Esto genera conflicto con la identificacion de los platos base
 * leidos en la clase "Lector"
 * 
 * Esta clase crea una lista de tamaño definido y con contenido aleatorio y no permite agregar mas clientes a la cola.
 */
public class ColaClientes {

    private Cliente CCliente[];
    private ClienteKaren clienteK;
    private ClienteRegular clienteR;
    private int cabeza;
    private int tamano;
    private int numero;
    private Random random;


    public ColaClientes(int cantidadClientes, Menu menu){

        //el constructor es el encargado de crear a los clientes y sus atributos,
        // tambien maneja le contador con el cual se les asigna una identificacion a los clientes

        this.CCliente = new Cliente[cantidadClientes];
        this.numero = 0; // es un contador
        this.tamano = cantidadClientes;
        this.cabeza = 0;
        this.random = new Random();

        while (numero < cantidadClientes){ // crea una cola de clientes del tamanno "cantidadClientes"
            
            //(numero+1) se usa para evitar crear otro contador exclusivo para la identificacion de los clientes.
            //ya cumpliria la misma funcion pero con una traslacion de 1

            if (random.nextInt(3) == 2) { // crea un cliente karen con un 33% de probabilidad aproximadamente
                this.clienteK = new ClienteKaren((numero+1),(random.nextInt(5) + 1));
                this.clienteK.leerMenu(menu); //se realiza las signaciones de las ordenes que el cliente ordenara
                this.CCliente[numero] = clienteK;
                numero++;

            } else { //
                this.clienteR = new ClienteRegular((numero+1),(random.nextInt(5) + 1));
                this.clienteR.leerMenu(menu); //se realiza las signaciones de las ordenes que el cliente ordenara
                this.CCliente[numero] = clienteR;
                numero++;
            }
        }
    }

    //los get
    public Cliente[] getColaCliente() {
        return CCliente;
    }
    public Cliente getCliente(int pos) {

        if (tamano <= pos) {
            return null;
        }

        return CCliente[pos];
    }
    public Cliente getCliente(){

        /**
        * Obtiene el cliente en la cabeza y mueve la cabeza al siguiente cliente
        */
        int i = this.cabeza;
        this.cabeza++;
        if (tamano <= i) {
            return null;
        }
        return this.CCliente[i];
        
    }
    public Cliente getTopeCliente(){
        /**
        * Obtiene el cliente en la cabeza
        */
        return this.CCliente[this.cabeza];
    }

}