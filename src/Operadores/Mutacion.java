package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.ArrayList;
import java.util.Iterator;

public class Mutacion implements Runnable {

    Poblacion poblacionVieja;
    int porcentajeMutacion;
    ArrayList<ArrayList<Integer>> restricciones;
    String operacion;
    Poblacion poblacion;

    public Mutacion(Poblacion poblacion, int porcentajeMutacion, ArrayList<ArrayList<Integer>> restricciones, String operacion,  Poblacion unaPoblacion) {
        this.poblacionVieja = poblacion;
        this.porcentajeMutacion = porcentajeMutacion;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.poblacion = unaPoblacion;
    }

    @Override
    public void run() {
       
        System.out.println("mutacion");
        
        Iterator it = poblacionVieja.getIndividuos().iterator();
        for (int i = 0; i < porcentajeMutacion; i++) {
            Individuo aux = (Individuo) it.next();
            poblacion.agregarIndividuo(new Individuo(aux.mutacion(), operacion, restricciones));
        }
        //return (individuosResultados);
    }
}
