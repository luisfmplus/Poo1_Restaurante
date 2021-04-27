package Administracion;

/**
 * Clase similar a las clases en Alimento, esta clase solamente es utilizada por la clase produccion
 * sin embargo, cuando se piensa en los menus, estos siempre son discutidos por gerencia, y la cocina es quien los aplica,
 * es por esto que esta clase esta en administracion
 * 
 * Aparte de eso, esta clase no tiene nada especial mas que servir como base de datos de combos
 */

public class Combo {
    
    private String nombre;
    private String contenido[];


    public Combo(String nombre, String contenido[]){

        this.nombre = nombre;
        this.contenido = contenido; 
    }


    // los get y set
    public String getContenido(int pos) {
        return contenido[pos];
    }
    public String[] getContenido() {
        return contenido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setContenido(String[] contenido) {
        this.contenido = contenido;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){

        String retorno;
        retorno = "Nombre: " +this.nombre;

        for (int i = 0; ((i < contenido.length) && (contenido[i]!= null)); i++) {
            retorno.concat(" Item" +(i+1)+": " +contenido[i]);
        }

        return retorno;
    }


}



