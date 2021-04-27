package Personas;

import java.util.Random;

/**
 * Clase que continen el contador de paciencia, y unos metodos unicos para tratar con ello. aparte de eso es casi igual a su clase padre (Cliente)
 */

public class ClienteKaren extends Cliente {

    private int tiempoPaciencia;



    public ClienteKaren(int identificacion, int tiempoEspera){

        super(identificacion, tiempoEspera);
        Random numero = new Random();
        this.tiempoPaciencia = (numero.nextInt(5)+2);//maximo de 6

    }


    //implementacion funcional de la funcion
    public void disminuirTiempoPaciencia(){//disminuye en 1

        if (this.tiempoPaciencia == 0) {
            return;
        }
        this.tiempoPaciencia--;
    }
    public void disminuirTiempoPaciencia(int cantidad){//disminuye en "cantidad"

        //actualemente no es utilizada en todo el programa, sin embargo esta presente en caso de que se llegara a usar

        if (this.tiempoPaciencia == 0) {
            return;
        }
        this.tiempoPaciencia-= cantidad;
    }

    //los get y set
    public int getTiempoPaciencia() {
        return tiempoPaciencia;
    }
    public void setTiempoPaciencia(int tiempoPaciencia) {
        this.tiempoPaciencia = tiempoPaciencia;
    }

    //pregunta si el contador de paciencia es 0
    public boolean isClientepaciencia0(){

        if (this.tiempoPaciencia == 0) {
            return true;
        }
        return false;
    }

}