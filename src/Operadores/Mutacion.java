
package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Mutacion implements Runnable{
    
    Poblacion poblacionVieja;
    int porcentajeMutacion;
    ArrayList<ArrayList<Integer>> restricciones;
    String operacion;
    Set<Individuo> individuos;
    
    public Mutacion(Poblacion poblacion, int porcentajeMutacion, ArrayList<ArrayList<Integer>> restricciones, String operacion, Set<Individuo> individuos){
        this.poblacionVieja = poblacion;
        this.porcentajeMutacion = porcentajeMutacion;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.individuos = individuos;
    }
    
    @Override
    public void run() {
        Iterator it = poblacionVieja.getIndividuos().iterator();
        for (int i = 0; i < porcentajeMutacion; i++) {
            Individuo aux = (Individuo) it.next();
            agregarIndividuo(new Individuo(aux.mutacion(), operacion, restricciones));
        }
        //return (individuosResultados);
    }
    
    private synchronized void agregarIndividuo(Individuo individuo){
        this.individuos.add(individuo);
    }
}
