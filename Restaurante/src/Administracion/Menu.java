package Administracion;

import java.util.Random;

import Alimentos.*;

/**
 * esta clase funciona como una pequeña base de datos,
 * su unica funcion consiste en almacenar los valores leidos de la clase Lector y 
 * proporcionar facil acceso a los mismos
 */

public class Menu {

    
    private PlatoF platofs[];
    private Bebidas bebidas[];
    private Acompanamiento acompanamientos[];
    private Combo combo[];
    private int modulo;//modulo para los getAleatorio*


    public Menu(){

        Lector lectorJson = new Lector(); // creamos el lector

        this.platofs = lectorJson.getPlatofs();
        this.acompanamientos = lectorJson.getAcompanamientos();
        this.bebidas = lectorJson.getBebidas();
        this.combo = lectorJson.getCombos();
        this.modulo = 4;

    }


    //los get
    public PlatoF[] getPlatofs() {
        return platofs;
    }
    public Acompanamiento[] getAcompanamientos() {
        return acompanamientos;
    }
    public Bebidas[] getBebidas() {
        return bebidas;
    } 
    public Combo[] getCombos() {
        return combo;
    }

    public PlatoF getPlatofs(int opcion) {
        
        if (opcion > platofs.length) {
            return platofs[(platofs.length-1)];
        } else if (opcion< 0){
            return platofs[0];
        }
        return platofs[opcion];
    }
    public Acompanamiento getAcompanamientos(int opcion) {

        if (opcion > acompanamientos.length) {
            return acompanamientos[(acompanamientos.length-1)];
        } else if (opcion< 0){
            return acompanamientos[0];
        }
        
        return acompanamientos[opcion];
    }
    public Bebidas getBebidas(int opcion) {
        
        if (opcion > bebidas.length) {
            return bebidas[(bebidas.length-1)];
        } else if (opcion< 0){
            return bebidas[0];
        }
        
        return bebidas[opcion];
    }
    public Combo getCombo(int opcion) {

        if (opcion > combo.length) {
            return combo[(combo.length-1)];
        } else if (opcion< 0){
            return combo[0];
        }

        return combo[opcion];
    }

    public PlatoF getAleatorioPlatofs() {
        Random j= new Random(4);
        int i = j.nextInt(this.modulo);
        return platofs[i];
    }
    public Acompanamiento getAleatorioAcompanamientos() {
        Random j= new Random(43);
        int i = j.nextInt(this.modulo);
        return acompanamientos[i];
    }
    public Bebidas getAleatorioBebidas() {
        Random j= new Random(437);
        int i = j.nextInt(this.modulo);
        return bebidas[i];
    }

}