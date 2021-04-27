package Administracion; 


import java.util.Iterator;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import Alimentos.*;


/**
 * Clase exclusiva para la lectura del archivo json y pasar la informacion leida.
 * 
 */

public class Lector {
    
    private PlatoF platofs[];
    private Bebidas bebidas[];
    private Acompanamiento acompanamientos[];
    private Combo combos[];

	public Lector() {
		
	
		JSONParser parser = new JSONParser(); //creacion del parser
        this.platofs = new PlatoF[4]; //arreglo de lo que tendra el menu
        this.bebidas = new Bebidas[4]; //arreglo de lo que tendra el menu
        this.acompanamientos = new Acompanamiento[4]; //arreglo de lo que tendra el menu
        this.combos = new Combo[4]; //arreglo de los combos del menu
        String contenido[][] = new String[4][4]; //arreglo para guardar el arreglo de lo que lleva cada combo
        
        //necesarios deacuerdo a la informacion del json
        JSONObject jsonObject;
        JSONArray jsonArrayPlatoFs;
        JSONArray jsonArrayBebidas;
        JSONArray jsonArrayAcompanamientos;
        JSONArray jsonArrayCombos;
        JSONArray jsonArrayCombosContenido;

        try (Reader archivo = new FileReader("D:\\Personal\\TEC\\Universidad\\2021-6-1\\POO\\Java\\Proyecto 1\\Restaurante\\src\\Administracion\\Menu.json")) {

            jsonObject = (JSONObject) parser.parse(archivo); //objeto con toda la informacion del JSON
            //________________________________
            jsonArrayPlatoFs = (JSONArray) jsonObject.get("Platofuerte"); //arreglo con la informacion de platofuerte
            //________________________________
            jsonArrayBebidas = (JSONArray) jsonObject.get("Bebidas"); //arreglo con la informacion de bebidas
            //________________________________
            jsonArrayAcompanamientos = (JSONArray) jsonObject.get("Acompanamientos"); //arreglo con la informacion de acompanamiento
            //________________________________
            jsonArrayCombos = (JSONArray) jsonObject.get("Combos"); //arreglo con la informacion de combos
            //________________________________

            
            Iterator<String> iterator;
            int i; //contador
            String nombre;
            String tipo;
            String tamano;
            int precio;
            int duracion;

            //platofuertes
            //aqui leemos todos los objetos tipo json que hay dentro del arreglo de platos fuertes
            iterator = jsonArrayPlatoFs.iterator(); //iterador funciona como señal de parada para evitar el error: puntero a nulo
            for (i = 0; iterator.hasNext(); i++) {
                jsonObject = (JSONObject) jsonArrayPlatoFs.get(i);
                
                nombre = (String) jsonObject.get("nombre");
                tamano = (String) jsonObject.get("tamano");
                precio = (int)(long) jsonObject.get("precio");
                duracion = (int)(long) jsonObject.get("duracion");

                platofs[i] = new PlatoF(duracion, nombre, precio, 0, tamano); // creamos el objeto base
                iterator.next();
            }

            //acompanamientos
            //aqui leemos todos los objetos tipo json que hay dentro del arreglo de acompanamiento
            iterator = jsonArrayAcompanamientos.iterator(); //iterador funciona como señal de parada para evitar el error: puntero a nulo
            for (i = 0; iterator.hasNext(); i++) {
                jsonObject = (JSONObject) jsonArrayAcompanamientos.get(i);
                
                nombre = (String) jsonObject.get("nombre");
                tipo = (String) jsonObject.get("tipo");
                precio = (int)(long) jsonObject.get("precio");
                duracion = (int)(long) jsonObject.get("duracion");

                acompanamientos[i] = new Acompanamiento(duracion, nombre, precio, 0, tipo);
                iterator.next();
            }

            //aqui leemos todos los objetos tipo json que hay dentro del arreglo de acompanamiento
            iterator = jsonArrayBebidas.iterator(); //iterador funciona como señal de parada para evitar el error: puntero a nulo
            for (i = 0; iterator.hasNext(); i++) {
                jsonObject = (JSONObject) jsonArrayBebidas.get(i);
                
                nombre = (String) jsonObject.get("nombre");
                tamano = (String) jsonObject.get("tamano");
                tipo = (String) jsonObject.get("tipo");
                precio = (int)(long) jsonObject.get("precio");
                duracion = (int)(long) jsonObject.get("duracion");

                bebidas[i] = new Bebidas(duracion, nombre, precio, 0, tamano, tipo);
                iterator.next();
            }

            //combos
            
            Iterator<String> iterator2;

            iterator = jsonArrayCombos.iterator(); //iterador funciona como señal de parada para evitar el error: puntero a nulo
            for (i = 0; iterator.hasNext(); i++) {
                jsonObject = (JSONObject) jsonArrayCombos.get(i);// obtenemos el objeto json del arreglo combo
                
                nombre = (String) jsonObject.get("nombre");
                jsonArrayCombosContenido =(JSONArray) jsonObject.get("contenido"); // obtenemos el arreglo dentro del objeto
                iterator2 = jsonArrayCombosContenido.iterator(); //iterador funciona como señal de parada para evitar el error: puntero a nulo
                
                for (int j = 0; iterator2.hasNext(); j++) {
                    contenido[i][j] = iterator2.next();

                }
                


                combos[i] = new Combo(nombre, contenido[i]); // Combo(...) requiere el arreglo contenido[i][] para guardarlo
                iterator.next();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}


    //los get
    public Acompanamiento[] getAcompanamientos() {
        return acompanamientos;
    }
    public Bebidas[] getBebidas() {
        return bebidas;
    }
    public PlatoF[] getPlatofs() {
        return platofs;
    }
    public Combo[] getCombos() {
        return combos;
    }

}