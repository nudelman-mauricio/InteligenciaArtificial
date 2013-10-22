package LogicaNegocios;

import java.util.ArrayList;
import Interfaz.Ventana;
import javax.swing.table.DefaultTableModel;

public class AlgoritmoGenetico{

    double []y;
    private int maximaAptitud;
    private String operacion;
    private int cantIndividuos;
    private int porcentajeSeleccion;
    private int porcentajeCruza;
    private int porcentajeMutacion;
    private double lambda;
    private ArrayList<ArrayList<Integer>> restricciones;
    
    static String[] encabezado= {"Nro Poblacion","Aptiptud Promedio","Cantidad Selección","Cantidad Cruza","Cantidad Mutación"};
    static DefaultTableModel modelo= new DefaultTableModel(encabezado,0);

   public AlgoritmoGenetico(int maximaAptitud, String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, double lambda, ArrayList<ArrayList<Integer>> restricciones) {
        this.maximaAptitud = maximaAptitud;
        this.operacion = operacion;
        this.cantIndividuos = cantIndividuos;
        this.porcentajeSeleccion = porcentajeSeleccion;
        this.porcentajeCruza = porcentajeCruza;
        this.porcentajeMutacion = porcentajeMutacion;
        this.lambda = lambda;
        this.restricciones = restricciones;
        this.y = new double [5000];
    }
    
    public void comenzarAlgoritmo () {
        int poblacionNumero = 1;
        Poblacion poblacionActual, poblacionNueva;

        //Generar primer población ALEATORIA        
        poblacionActual = new Poblacion(operacion, cantIndividuos, restricciones);
        
        //sssssssssssssssssssssssssssss
        y[poblacionNumero] = poblacionActual.aptitudProm();
        
        //para la mutacion por temperatura
        int valorMax = ((int) (0.50 * cantIndividuos));
        double acumulador = 0;       

        //generar poblaciones nuevas a partir de una vieja mientras no se alcance un individuo resultado
        while (poblacionActual.esSolucion() == null) {
            //System.out.println("Población Número: " + poblacionNumero + " Aptitud: " + poblacionActual.aptitudProm() + " %Mutación: " + this.porcentajeMutacion + " Cantidad de población: "+ poblacionActual.getIndividuos().size());
            Object datos[] = {poblacionNumero, poblacionActual.aptitudProm(), this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion};
            modelo.addRow(datos);
            y[poblacionNumero] = poblacionActual.aptitudProm();
             
            poblacionNueva = new Poblacion(operacion, this.cantIndividuos, poblacionActual, this.restricciones, this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion, this.maximaAptitud);
            poblacionActual = poblacionNueva;
            poblacionNumero++;
            
            //calculo de mutacion adaptativa por temperatura ascendente
            acumulador += this.lambda * this.cantIndividuos;
            if (acumulador >= 1) {
                if (this.porcentajeMutacion < valorMax) {
                    this.porcentajeMutacion++;   
                    this.porcentajeCruza--; 
            acumulador = 0; //Setea devuelta a 0 para solucionar el problema que sumaba siempre 
                } else {
                    this.porcentajeMutacion = valorMax;
                    this.porcentajeCruza = (this.cantIndividuos- this.porcentajeMutacion - this.porcentajeSeleccion);
                }
            }
        }
        //CARTEL GANASTE
        if (poblacionActual.esSolucion() != null) {
            Ventana.addTabla(modelo);
            Ventana.graficar(y);
            
            Ventana.resultados(poblacionActual.esSolucion().getGenes(), poblacionNumero, operacion);
            
            System.out.println("\n" + poblacionActual.esSolucion().toString());
            System.out.println("Cantidad de Iteracciones: " + poblacionNumero);
            System.out.println("%Seleccion: " + this.porcentajeSeleccion + " %Cruza: " + this.porcentajeCruza + " %Mutacion: " + this.porcentajeMutacion + " CantIndividuos: " + poblacionActual.getIndividuos().size());
        }
    }
}
