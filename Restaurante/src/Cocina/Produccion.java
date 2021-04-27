package Cocina;

import Alimentos.*;
import Administracion.*;


/**
 * Colafaltante tiene un tamaño maximo, en caso de que este fuera exedido,
 * la orden simplemente no se registra y el programa podria enciclarse en un lugar desconocido
 * 
 * La alteracion de el tamaño de cualquier lista debe realizarse con cuidado pues puede tirar todo el programa fuera de balance
 * en especial el tamaño de las ordenes del cliente. actualmente es de maximo 5 
 * en caso de reducirlo no pasa nada, pero en caso de aumentarlo podria ocasionar multiple errores fatales
 * 
 * Esta clase es mayoritariamente la encarda del manejo de la logica para el manejo de todas las colas y sus diferentes interacciones
 * es por esto que de aumentar el tamaño de los pedidos podria caerse esta clase.
 */

public class Produccion {

    private int identidadperdida[]; // memoria de la identificacion de la ordenes perdidas
    private int precio; // precio de la venta
    private ColaPedidoCompletado colacompletada; //cola de ordenes completas
    private ColaPedidosFaltantes colafaltante; //cola de ordenes en espera
    private ColaProduccion colaproduccion; //cola de ordenes siendo procesadas
    private Gerente gerencia;
    private Combo combos[];


    public Produccion(Gerente gerencia, Menu menuComida ){

        this.gerencia = gerencia;
        this.combos= menuComida.getCombos();
        this.identidadperdida = new int[10];
        this.precio = 0;
        this.colacompletada = new ColaPedidoCompletado(20);
        this.colafaltante = new ColaPedidosFaltantes(30);
        this.colaproduccion = new ColaProduccion(10);//es inutil hacerla mas grande
    }


    public void recibirOrden(Comida orden[]){// se recibe de cliente la orden


        for (int i = 0; (i < orden.length) && (orden[i] != null); i++){
            
            agregarALaCocina(orden[i]);
        }

    }

    private void agregarALaCocina(Comida orden){ 
        
        // envia la orden a la cola produccion o la cola faltante en caso de que cola porduccion este llena
    
        if (colafaltante.colaVacia()) { // esta validacion evita que ordenes puedan saltarse ordenes en espera en Cola faltantes
            
            if (colaproduccion.agregar(orden)){ //retorna true si logro agregar la orden

                return;
            }
    
            colafaltante.agregar(orden);

        }
        colafaltante.agregar(orden);
    }

    public void moverCocinaACompletado(){

        if (colaproduccion.colaVacia()) {
            return;
        }

        while ((this.colaproduccion.retornarCabeza() != null) &&(this.colaproduccion.retornarCabeza().getDuracion() == 0)){ //le preguntamos al primer elemento si ya esta listo en cola produccion

            //retorna true y entra aqui si la cabeza de la cola produccion esta completada

            colacompletada.agregar(this.colaproduccion.retornarCabeza()); //lo movemos a cola completada
            colaproduccion.quitar(); // lo quitamos de cola produccion
        
        }

    }

    public void moverColaACocina(){

        if (colafaltante.colaVacia()) {
            return;
        }

        while (colaproduccion.agregar(colafaltante.retornarCabeza())) {//movemos la orden de la colafaltante a cola produccion
            
            // la funcion entra aquí si la transferencia fue exitosa
            colafaltante.quitar();

        }
    }

    // es necesaria pues la clase no tiene ningun get o set
    public void disminuirDuracion(){

        colaproduccion.disminuirDuracionEn1();
    }

    public boolean entregarOrden(int identificacion){ //se entrega a cliente

        //prosesa la orden del cliente por medio de la identificacion

        int i = 0;
        Comida bufferOrdenCliente[] = new Comida[10]; // las ordenes de los clientes nunca sobrepasan 5

        if (colaproduccion.isIdentificacionEnCola(identificacion)){ // verificamos que no haya ordenes aun en produccion
            //si
            return false;

        }
        
        if (colafaltante.isIdentificacionEnCola(identificacion)) { // verificamos que no haya ordenes aun en cola faltante
            
            //si
            return false;
            
        }
        

        //lo siguiente cambia de lugar la orden; de cola completada a la cola buffer
        while ((!colacompletada.colaVacia()) && (colacompletada.retornarCabeza().getIdentidad() == identificacion)) {
            
            bufferOrdenCliente[i] = colacompletada.retornarCabeza();
            colacompletada.quitar();
            i++;


        }
        bufferOrdenCliente[i] = null; // dejamos como nulo el siguiente campo despues de haber obtenido toda la orden en el buffer

        int numero[];
        this.precio = 0;
        i=0;

        if (elejibleCombo(bufferOrdenCliente)){ //revisamos si la orden puede aplicarsele algun descuento

            numero = valorcombo(bufferOrdenCliente);

            while ((i < bufferOrdenCliente.length) && (bufferOrdenCliente[i] != null)) {//sumamos el precio de todos los articulos restantes
                
                if ((i == numero[1]) || (i == numero[2]) || (i == numero[3]) ) { // nos saltamos los articulos que ya sumamos y aplicamos el descuento
                    i++;
                    continue;
                }

                numero[0] = numero[0] + bufferOrdenCliente[i].getPrecio();
                i++;
            }
            this.precio = numero[0];
            gerencia.aumentarGananciaTotal(this.precio); //reporta la ganacia a gerencia
            gerencia.aumetarOrdenesCompletadas(); //reportamos la completacion de la orden
            return true;

        }

        while ((i < bufferOrdenCliente.length) && (bufferOrdenCliente[i] != null)) {//sumamos el precio de todos los articulos
            this.precio = this.precio + bufferOrdenCliente[i].getPrecio();
            i++;
        }

        //originariamente la orden se le devolvia al cliente. actualmente se borra la orden del buffer

        gerencia.aumentarGananciaTotal(this.precio); //reporta la ganacia a gerencia
        gerencia.aumetarOrdenesCompletadas(); //reportamos la completacion de la orden
        return true;

    }

    private int[] valorcombo(Comida[] bufferOrdenCliente){

        //retorna el valor del combo con el descuento aplicado
        //y las ubicaciones de cuales articulos fueron los que tienen el descuento

        int i = 0;
        int j = 0;
        int k[] = new int[3];
        int numero[] = new int[4];
        String buffercliente = "";
        String buffer1;
        String buffer2;
        String buffer3;
        Bebidas duumyBebidas = new Bebidas();
        Acompanamiento dummyAcompanamiento = new Acompanamiento();
        PlatoF dummyPlatoF = new PlatoF();
        boolean bufer1 = false;
        boolean bufer2 = false;
        boolean bufer3 = false;

        if (bufferOrdenCliente[i] == null) {
            System.out.printf("h");
        }

        while (true) {
            while ((i < bufferOrdenCliente.length) && (bufferOrdenCliente[i] != null)) { // mientra exista una orden que revisar
    
                buffer1 = this.combos[j].getContenido()[0]; //obtenemos lo que debe contener la orden para aplicar combo
                buffer2 = this.combos[j].getContenido()[1]; //obtenemos lo que debe contener la orden para aplicar combo
                buffer3 = this.combos[j].getContenido()[2]; //obtenemos lo que debe contener la orden para aplicar combo
        
                if (dummyPlatoF.getClass() == bufferOrdenCliente[i].getClass()) {
                    buffercliente = "Platofuerte";
                } else if (dummyAcompanamiento.getClass() == bufferOrdenCliente[i].getClass()){
                    buffercliente = "Acompanamientos";
                } else if (duumyBebidas.getClass() == bufferOrdenCliente[i].getClass()){
                    buffercliente = "Bebidas";
                }
        
    
                if ((!bufer1) && (buffercliente.equals(buffer1)) ) {
    
                    bufer1 = true;
                    k[0] = i;
                
                } else if ((!bufer2) &&(buffercliente.equals(buffer2))) {
                    
                    bufer2 = true;
                    k[1] = i;
                
                } else if ((!bufer3) &&(buffercliente.equals(buffer3))) {
                    
                    bufer3 = true;
                    k[2] = i;
                }
    
                if (bufer1 && bufer2 && bufer3) {
                    
                    numero[0] = bufferOrdenCliente[k[0]].getPrecio() + bufferOrdenCliente[k[1]].getPrecio() + bufferOrdenCliente[k[2]].getPrecio();
                    numero[0] = numero[0] - (numero[0]/10);
                    numero[1] = k[0];
                    numero[2] = k[1];
                    numero[3] = k[2];
                    
                    return numero;
                }
    
                i++; //movemos el contador de buffercliente
            }
            j++; //movemos el contador de combos[]
            i = 0; // reseteamos variable
            bufer1 = bufer2 = bufer3 = false; // reseteamos variable
            for (int c = 0; c < k.length; c++) { // reseteamos variable
                k[c] = 0;
            }
            
        }
    }


    private boolean elejibleCombo(Comida[] bufferOrdenCliente){// contine solo 3 buffer para combos

        //verifica si algun combo es aplicable a la orden del cliente en espera

        int i = 0;
        int j = 0;
        String buffercliente = "";
        String buffer1;
        String buffer2;
        String buffer3;
        Bebidas duumyBebidas = new Bebidas();
        Acompanamiento dummyAcompanamiento = new Acompanamiento();
        PlatoF dummyPlatoF = new PlatoF();
        
        boolean bufer1 = false;
        boolean bufer2 = false;
        boolean bufer3 = false;

        if (bufferOrdenCliente[i] == null) { 
            //en caso de que esta validacion se cumpla significa que de alguna manera una orden vacia llego hasta aqui
            // generalmente esto implica que algun cliente se salto su pocision en la cola y su orden esta en produccion antes de tiempo
            System.out.printf("llego una orden vacia");
            return false;
        }

        while (true) {
            while ((i < bufferOrdenCliente.length) && (bufferOrdenCliente[i] != null)) { // mientra exista una orden que revisar
                if (j >= combos.length) {
                    return false;
                }
    
                buffer1 = this.combos[j].getContenido()[0]; //obtenemos lo que debe contener la orden para aplicar combo
                buffer2 = this.combos[j].getContenido()[1]; //obtenemos lo que debe contener la orden para aplicar combo
                buffer3 = this.combos[j].getContenido()[2]; //obtenemos lo que debe contener la orden para aplicar combo
    
                if (dummyPlatoF.getClass() == bufferOrdenCliente[i].getClass()) {
                    buffercliente = "Platofuerte";
                } else if (dummyAcompanamiento.getClass() == bufferOrdenCliente[i].getClass()){
                    buffercliente = "Acompanamientos";
                } else if (duumyBebidas.getClass() == bufferOrdenCliente[i].getClass()){
                    buffercliente = "Bebidas";
                }
    


    
                if ((!bufer1) && (buffercliente.equals(buffer1)) ) {
    
                    bufer1 = true;
                
                } else if ((!bufer2) &&(buffercliente.equals(buffer2))) {
                    
                    bufer2 = true;
                
                } else if ((!bufer3) &&(buffercliente.equals(buffer3))) {
                    
                    bufer3 = true;
                }
    
                if (bufer1 && bufer2 && bufer3) {
                    return true;
                }
    
                i++; //movemos el contador de buffercliente
            }
            j++; //movemos el contador de combos[]
            i = 0;
            bufer1 = bufer2 = bufer3 = false;
        }

    }
    
    public void agregarIdentificacionOlvidada(int identificacion){

        //agrega una identida a la lista de identidades perdidas

        int i = 0;

        while (identidadperdida[i] != 0) {
            i++;
        }

        identidadperdida[i] = identificacion;

    }
    
    public void manejoDeComidaOlvidada(){

        //maneja toda la comida que fuera olvidada

        int i = 0;
        boolean quitamos = false;
        int totalPerdida = 0;

        while ((i < identidadperdida.length)&&(identidadperdida[i] != 0)) { //preguntamos a todas las identidades perdidas
            
            if (colaproduccion.isIdentificacionEnCola(identidadperdida[i])){ // entra aqui si la orden estaba en produccion 

                colacompletada.agregar(colaproduccion.retornarCabeza()); //mueve la orden a la cola completa sin importan si esta lista o no
                colaproduccion.quitar(); //luega la quita de cola produccion
                continue;

            } 
            
            if (colafaltante.isIdentificacionEnCola(identidadperdida[i])) { // entra aqui si la orden estaba en la cola faltante
            
                colacompletada.agregar(colafaltante.retornarCabeza()); // mueve la orden a la cola completa sin importan si esta lista o no
                colafaltante.quitar(); // luego la quita de cola faltante
                continue;
                
            }

            //al completar esos paso anteriores, toda la orden del cliente que se fue esta en una sola cola

            if (colacompletada.isIdentificacionEnCola(identidadperdida[i])) { // borramos los productos pues fueron avandonados
                    
                totalPerdida = totalPerdida + colacompletada.retornarCabeza().getPrecio(); // obtenemos la perdida total
                colacompletada.quitar();
                quitamos = true;
                continue;
            }

            i++;
        }

        if (quitamos) { // si resulta que quitamos comida; terminamos el reporte con gerencia y lo que perdimos
            this.precio = totalPerdida*-1;
            gerencia.aumentarClientesPerdidos();
            gerencia.aumentarPerdidaTotal(totalPerdida);
            return;
        
        }
        
        return;
    }

    public int getPrecio() {
        return precio;
    }
    
    public ColaProduccion getColaProduccion(){
        return this.colaproduccion;
    }

    public ColaPedidosFaltantes getColafaltante() {
        return this.colafaltante;
    }

}