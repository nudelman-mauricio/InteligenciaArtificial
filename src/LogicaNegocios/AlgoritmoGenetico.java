package LogicaNegocios;

import java.util.ArrayList;

public class AlgoritmoGenetico{

    int maximaAptitud;
    String operacion;
    int cantIndividuos;
    int porcentajeSeleccion;
    int porcentajeCruza;
    int porcentajeMutacion;
    double lambda;
    ArrayList<ArrayList<Integer>> restricciones;
    

   public AlgoritmoGenetico(int maximaAptitud, String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, double lambda, ArrayList<ArrayList<Integer>> restricciones) {
        this.maximaAptitud = maximaAptitud;
        this.operacion = operacion;
        this.cantIndividuos = cantIndividuos;
        this.porcentajeSeleccion = porcentajeSeleccion;
        this.porcentajeCruza = porcentajeCruza;
        this.porcentajeMutacion = porcentajeMutacion;
        this.lambda = lambda;
        this.restricciones = restricciones;
    }
    
    public void comenzarAlgoritmo () {
        int poblacionNumero = 1;
        Poblacion poblacionActual, poblacionNueva;

        //Generar primer población ALEATORIA        
        poblacionActual = new Poblacion(operacion, cantIndividuos, restricciones);
        
        //para la mutacion por temperatura
        int valorMax = ((int) (0.50 * cantIndividuos));
        double acumulador = 0;       

        //generar poblaciones nuevas a partir de una vieja mientras no se alcance un individuo resultado
        while (poblacionActual.esSolucion() == null) {
            System.out.println("Población Número: " + poblacionNumero + " Aptitud: " + poblacionActual.aptitudProm() + " %Mutación: " + porcentajeMutacion + " Cantided de porblación: "+ poblacionActual.getIndividuos().size());
             
            poblacionNueva = new Poblacion(operacion, cantIndividuos, poblacionActual, restricciones, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, maximaAptitud);
            poblacionActual = poblacionNueva;
            poblacionNumero++;
            
            //calculo de mutacion adaptativa por temperatura ascendente
            acumulador += lambda * cantIndividuos;
            if (acumulador >= 1) {
                if (porcentajeMutacion < valorMax) {
                    porcentajeMutacion += 1; //aumento 4 individuos en mutacion     
                    porcentajeCruza -= 1; //disminuyo 4individuos en Cruza
                    acumulador = 0; //Setea devuelta a 0 para solucionar el problema que sumaba siempre 
                } else {
                    porcentajeMutacion = valorMax;
                    porcentajeCruza = (100 - porcentajeMutacion - porcentajeSeleccion);
                }
            }
        }
        //CARTEL GANASTE
        if (poblacionActual.esSolucion() != null) {
            System.out.println("\n" + poblacionActual.esSolucion().toString());
            System.out.println("Cantidad de Iteracciones: " + poblacionNumero);
            System.out.println("%Seleccion: " + porcentajeSeleccion + " %Cruza: " + (porcentajeCruza * 2) + " %Mutacion: " + porcentajeMutacion + " CantIndividuos: " + poblacionActual.getIndividuos().size());
        }
    }
}
