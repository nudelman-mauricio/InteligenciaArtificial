package Operadores;

import Interfaz.Ventana;
import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Mutacion implements Runnable {

    private Poblacion poblacionVieja;
    private int porcentajeMutacion;
    private ArrayList<ArrayList<Integer>> restricciones;
    private String operacion;
    private Set<Individuo> individuos;
    private Random generadorAleatorio;

    public Mutacion(Poblacion poblacionActual, int porcentajeMutacion, ArrayList<ArrayList<Integer>> restricciones, String operacion, Set<Individuo> individuos) {
        this.poblacionVieja = poblacionActual;
        this.porcentajeMutacion = porcentajeMutacion;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.individuos = individuos;
        this.generadorAleatorio = new Random();
    }

    @Override
    public void run() {
        int aleatorio, contador =0;
        for (int i = 0; i < this.porcentajeMutacion; i++) {
            aleatorio = this.generadorAleatorio.nextInt(this.poblacionVieja.getIndividuos().size());
            for (Individuo aux : this.poblacionVieja.getIndividuos()) {
                contador++;
                if (aleatorio == contador) {
                    //agregar de forma Sincronizada al nuevo individuo
                    synchronized (individuos) {
                        individuos.add(new Individuo(aux.mutacion(), this.operacion, this.restricciones));
                        individuos.notify();
                        Ventana.cargarBarra(individuos.size());
                    }
                }
            }
        }
    }
}
