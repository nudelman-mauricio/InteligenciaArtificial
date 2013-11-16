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

        //determinar cantidades, si es par, hace 50/50
        //si son impares se hace un mas por Aleatorio
        int cantidadAleatoria, cantidadElitista;
        if (this.porcentajeMutacion % 2 != 0) {
            cantidadAleatoria = ((int) (porcentajeMutacion / 2)) + 1;
            cantidadElitista = cantidadAleatoria - 1;
        } else {
            cantidadAleatoria = porcentajeMutacion / 2;
            cantidadElitista = cantidadAleatoria;
        }

        //selecccion por Ruleta
        mutacionAleatoria(cantidadAleatoria);

        //seleccion Elitista
        mutacionElitista(cantidadElitista);
    }

    private void mutacionAleatoria(int cantidad) {
        int aleatorio, contador = 0;
        for (int i = 0; i < cantidad; i++) {
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
            contador = 0;
        }
    }

    private void mutacionElitista(int cantidad) {
        Iterator it = this.poblacionVieja.getIndividuos().iterator();
        Individuo unIndividuo;
        for (int i = 0; i < cantidad; i++) {
            unIndividuo = new Individuo((Individuo) it.next());
            synchronized (individuos) {
                individuos.add(new Individuo(unIndividuo.mutacion(), this.operacion, this.restricciones));
                individuos.notify();
                Ventana.cargarBarra(individuos.size());
            }
        }
    }
}
