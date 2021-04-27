package Administracion;


    /**
     * Clase exclusiva para el manejo de la mayoria de las estadisticas del programa
     */

public class Gerente {

    private int gananciatotal;
    private int perdidatotal;
    private int clientesperdidos;
    private int OrdenesCompletadas;
    private int CantidadTicks;
    

    public Gerente(){

        this.gananciatotal = 0;
        this.perdidatotal = 0;
        this.clientesperdidos = 0;
        this.CantidadTicks = 0;
        this.OrdenesCompletadas = 0;


    }

    

    //aumentadores de contadores
    public void aumetarOrdenesCompletadas(){

        this.OrdenesCompletadas++;
    }
    public void aumentarGananciaTotal(int numero) {
        this.gananciatotal = this.gananciatotal + numero;
        return;
    }
    public void aumentarPerdidaTotal(int numero) { //recibe numero
        
        if (numero < 0) {
            numero = numero * (-1);
        }
        
        this.perdidatotal = this.perdidatotal + numero;
        return;
    }
    public void aumentarClientesPerdidos(int numero) { //aumenta en "numero"
        this.clientesperdidos+= numero;
        return;
    }
    public void aumentarClientesPerdidos() { //aumenta en 1
        this.clientesperdidos++;
        return;
    }
    public void aumetarCantidadTicks(){

        this.CantidadTicks++;
    }

    // los get
    public int getOrdenesCompletadas() {
        return OrdenesCompletadas;
    }
    public int getClientesperdidos() {
        return clientesperdidos;
    }
    public int getGananciatotal() {
        return gananciatotal;
    }
    public int getPerdidatotal() {
        return perdidatotal;
    }
    public int getCantidadTicks() {
        return CantidadTicks;
    }

    // los set
    public void setClientesperdidos(int clientesperdidos) {
        this.clientesperdidos = clientesperdidos;
    }
    public void setGananciatotal(int gananciatotal) {
        this.gananciatotal = gananciatotal;
    }
    public void setPerdidatotal(int perdidatotal) {
        this.perdidatotal = perdidatotal;
    }
    public void setOrdenesCompletadas(int ordenesCompletadas) {
        OrdenesCompletadas = ordenesCompletadas;
    }
    public void setCantidadTicks(int cantidadTicks) {
        CantidadTicks = cantidadTicks;
    }

}