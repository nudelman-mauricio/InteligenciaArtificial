package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Mutacion implements Runnable {

    private Poblacion poblacionVieja;
    private int porcentajeMutacion;
    private ArrayList<ArrayList<Integer>> restricciones;
    private String operacion;
    private Set<Individuo> individuos;

    public Mutacion(Poblacion poblacionActual, int porcentajeMutacion, ArrayList<ArrayList<Integer>> restricciones, String operacion,  Set<Individuo> individuos) {
        this.poblacionVieja = poblacionActual;
        this.porcentajeMutacion = porcentajeMutacion;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.individuos = individuos;
    }

    @Override
    public void run() {       
        Iterator it = this.poblacionVieja.getIndividuos().iterator();
        for (int i = 0; i < this.porcentajeMutacion; i++) {
            Individuo aux = (Individuo) it.next();
            //agregar de forma Sincronizada al nuevo individuo
            synchronized (individuos) {
                individuos.add(new Individuo(aux.mutacion(), this.operacion, this.restricciones));
                individuos.notify();
            }
        }
    }
}
