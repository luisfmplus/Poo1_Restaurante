
package Administracion;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Cocina.*;
import Graficos.Ventana;
import Personas.*;



public class Ejecutar {
    
    /*boolean siguienteTick = false;
    boolean siguientePaso = false;*/
    
    public static void main(String[] args) throws Exception {

        Thread main = Thread.currentThread();

        //el lector guarda su informacion en la clase menu
        Menu menuComida = new Menu();
        Gerente gerenteAdmin = new Gerente(); // creamos una gerencia
        
        //creamos a la clase produccion
        Produccion produccionRestaurante = new Produccion(gerenteAdmin, menuComida);

        //creamos las 2 tipos de listas (es: "las tipo de listas" pues a quien describimos son a las listas. "tipos" es un adjetivo de las listas)
        ColaClientes colaClientesRestaurante = new ColaClientes(10, menuComida);
        ListaCliente colaClientesEspera = new ListaCliente(15);

        //Cliente en la cabeza de ambas listas
        Cliente ClienteActual= colaClientesRestaurante.getCliente();
        Cliente ClienteEsperando = colaClientesEspera.retornarCabeza();

        boolean siguienteTick = false;
        boolean siguientePaso = false;

        //ventana GUI
        Ventana frame = new Ventana(gerenteAdmin, main);

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Ventana frame = new Ventana();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});




        //empieza la simulacion

        //El ciclo se mantinen mientras que existan clientes por atender o que no existan clientes esperando su orden
        while ((ClienteEsperando != null) || (ClienteActual != null)) {
            
            gerenteAdmin.aumetarCantidadTicks();

            frame.actualizarValoresGerencia(gerenteAdmin.getGananciatotal(), gerenteAdmin.getPerdidatotal(), gerenteAdmin.getClientesperdidos(), gerenteAdmin.getOrdenesCompletadas(), gerenteAdmin.getCantidadTicks());

            siguienteTick = false;

            synchronized(main){

                    main.wait();

            };

        


            //paso 1:: Se determina si el primer cliente de la cola hará su pedido, si lo hace, se incluye en una lista de pendientes.

            if (ClienteActual != null) { // es nesesaria cuando se acaban los clientes de la colaClientesRestaurante, pues aun quedan clientes en espera

                ClienteActual.disminuirTiempoEspera();


                if (ClienteActual.getTiempoEspera() == 0){ // el cliente actual esta listo para ordenar

                    //paso 2:: Si hace su pedido, la clase producción crea las instancias de los productos para completar la orden.
                
                    produccionRestaurante.recibirOrden(ClienteActual.ordenarPedido()); //recibe la orden del cliente ==( Comida[] )
                
                    colaClientesEspera.agregar(ClienteActual); // agregamos al cliente actual a la lista de espera
                
                    ClienteActual = colaClientesRestaurante.getCliente(); // ponemos al siguiente cliente en la colaClienteRestaurante en la cabeza

                }
            }   

            //paso 3:: Se revisan todos los productos en producción, decrementandolos contadores de los artículos.
        
            produccionRestaurante.disminuirDuracion();
        
            produccionRestaurante.moverCocinaACompletado(); //se mueve lo que se haya completado a la cola completa
        

            //paso 4:: Si todos los contadores de algún pedido llegan a0, se hace el cálculo del precio y se saca de la lista de pendientes.
        
            ClienteEsperando = colaClientesEspera.retornarCabeza(); //asignamos a la cabeza de los clientes en espera, el primer cliente en espera


            if (ClienteEsperando != null) {
                // de no entrar signfica que no hay cliente en espera aun, o que el ultimo atendido no tenenia sucesor

            


                if (produccionRestaurante.entregarOrden(ClienteEsperando.getIdentificacion())){
                    //al entrar significa que se logro "entregar" la orden.
                    // la gerencia actualiza sus estadisticas de forma respectiva a la entrega

                    colaClientesEspera.quitar(); // quitamos al cliente de la cola de espera

                    ClienteEsperando = colaClientesEspera.retornarCabeza(); // asignamos la cabeza de la cola esperando al siguiente en linea
                }

            }



            produccionRestaurante.moverColaACocina(); // movemos ordenes en la cola faltante a la cola produccion
        

            colaClientesEspera.disminuirPaciencia(); // disminuimos el medidor de paciencia de los clientes en cola esperando


            //paso 6:: Se revisa el contador de paciencia del cliente en la cabeza de la lista espera, si se agota, se saca la orden.

            if (ClienteEsperando != null) {

                if (ClienteEsperando.isClientepaciencia0()) { //preguntamos si su paciencia ya llego a 0
                
                    //de ser afirmativo procedemos a remover de las colas producion, faltante y completo 
                    //estas ordenes de este cliente


                    produccionRestaurante.agregarIdentificacionOlvidada(ClienteEsperando.getIdentificacion());

                    colaClientesEspera.quitar();
                

                    // aqui es donde se realiza la eliminacion de las ordenes
                    //y se le reporta a a gerencia acerca de esto
                    produccionRestaurante.manejoDeComidaOlvidada(); 
                    ClienteEsperando = colaClientesEspera.retornarCabeza(); //movemos la cabeza de la cola al siguiente en cola esperando
                
                }
            }

        


            //paso 7:: Se registra el número de órdenes completadas, la ganancia acumulada y el número de clientes insatisfechos.
            //se deben inprimir en la ventana los resultados y el contador de ticks que han habido.
            //toda esa informacion la tiene gerencia
            // la otra informacion habria que conseguirla alrededor

            //gerencia
            frame.actualizarValoresGerencia(gerenteAdmin.getGananciatotal(), gerenteAdmin.getPerdidatotal(), gerenteAdmin.getClientesperdidos(), gerenteAdmin.getOrdenesCompletadas(), gerenteAdmin.getCantidadTicks());

            //contadores del cliente actual
            if (ClienteEsperando != null) {
                frame.actualizarValoresClienteEsperando(ClienteEsperando.getTiempoEspera(), ClienteEsperando.getTiempoPaciencia());
            
            } else {
                frame.actualizarValoresClienteEsperando(-1, -1);
            }

            //contadores del cliente esperando
            if (ClienteActual != null) {
                frame.actualizarValoresClienteActual(ClienteActual.getTiempoEspera(), ClienteActual.getTiempoPaciencia());
            } else {
                frame.actualizarValoresClienteActual(-1, -1);
            }

            frame.actualizarPedidosFaltantes(produccionRestaurante.getColafaltante().getComidas());
            frame.actualizarPedidosProduccion(produccionRestaurante.getColaProduccion().getComidas());
            
            
        }
    
    
    
        System.out.printf("Termine en Tick:");
    


    }

    


}