
package Interfaz;

import LogicaNegocios.AlgoritmoGenetico;
import java.util.ArrayList;

public class Main {
    
    private static Ventana unaVentana;
    private static AlgoritmoGenetico unAlgoritmoGenetico;
    
    public static void main(String[] args) {
        unaVentana = new Ventana();
        unaVentana.setVisible(true);
    }
    
    public static void comenzarAlgoritmo(String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, double lambda) {
               
        //Calcular porcentajes de Seleccion/Cruza/Mutacion
        porcentajeSeleccion = (porcentajeSeleccion * cantIndividuos) / 100;
        porcentajeCruza = (porcentajeCruza * cantIndividuos) / 100;
        porcentajeMutacion = (porcentajeMutacion * cantIndividuos) / 100;      

        //Generar las restricciones para la operacion
        ArrayList<ArrayList<Integer>> restricciones = obtenerRestricciones(operacion);
        
        //Calcular la peor aptitud
        int maximaAptitud = obtenerMaximaAptitud(restricciones);

        //Generar hilo
        unAlgoritmoGenetico = new AlgoritmoGenetico(maximaAptitud, operacion, cantIndividuos, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, lambda, restricciones);
        unAlgoritmoGenetico.comenzarAlgoritmo();
    }
    
    //Genera las restricciones dada la operacion
    public static ArrayList<ArrayList<Integer>> obtenerRestricciones(String operacion) {
        ArrayList<ArrayList<Integer>> restricciones = new ArrayList<>();
        ArrayList<Integer> posiciones;
        boolean existeRestriccion = false, bandera = false;

        for (int i = 0; i < operacion.length(); i++) {
            if (bandera) {
                //verifica que no existra la restriccion ya creada
                for (int j = 0; j < restricciones.size(); j++) {
                    if (restricciones.get(j).contains(i)) {
                        existeRestriccion = true; //quiere decir que existe ya la restriccion
                        j = restricciones.size();
                    }
                }
                //Si no existe paso a crearla
                if (existeRestriccion == false) {
                    posiciones = new ArrayList<Integer>();
                    for (int k = 0; k < operacion.length(); k++) {//Recorre toda la operacion buscando igualdad con el caracter tomado
                        if (operacion.charAt(k) == operacion.charAt(i)) {
                            posiciones.add(k);
                        }
                    }
                    restricciones.add(posiciones);
                }
                existeRestriccion = false;
            }
            if (operacion.charAt(i) == '=') {
                bandera = true;
            }
        }
        return restricciones;
    }
    
    //calcula la cantidad de restricciones que es igual a la peor aptitud del peor individuo
    public static int obtenerMaximaAptitud(ArrayList<ArrayList<Integer>> restricciones) {
        int maximaAptitud = 0;
        for (int i = 0; i < restricciones.size(); i++) {
            maximaAptitud += (i+1)*2;
        }
        return maximaAptitud;
    }
    
    public static void pararAlgoritmo(){
        unAlgoritmoGenetico.pararAlgoritmo();
    }
    
}
