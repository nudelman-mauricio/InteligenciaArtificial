package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.Random;
import java.util.Set;

public class Seleccion implements Runnable {

    private Poblacion poblacionVieja;
    private int porcentajeSeleccion;
    private int maximaAptitud;
    private Set<Individuo> individuos;
    private Random generadorAleatorio;
    private int aleatorio;

    public Seleccion(Poblacion poblacionActual, int porcentajeSeleccion, int maximaAptitud, Set<Individuo> individuos) {
        this.poblacionVieja = poblacionActual;
        this.porcentajeSeleccion = porcentajeSeleccion;
        this.maximaAptitud = maximaAptitud;
        this.individuos = individuos;
        this.generadorAleatorio = new Random();
    }

    @Override
    public void run() {
        //dormirHiloPoblacion(poblacion);

        System.out.println("Seleccion");
        double sum = 0;
        //Suma aptitud Poblacion
        for (Individuo aux : poblacionVieja.getIndividuos()) {
            sum += (maximaAptitud - (aux.getAptitud() + 1));
        }

        double[] auxCalculo = new double[poblacionVieja.getIndividuos().size()];
        double[] auxCalculoAcum = new double[poblacionVieja.getIndividuos().size()];
        double sumatoria = 0;
        int cont = 0;
        for (Individuo aux : poblacionVieja.getIndividuos()) {
            auxCalculo[cont] = redondear(((maximaAptitud - (aux.getAptitud() + 1)) / sum) * 1000, 0); //cantidad de espacios en la ruleta por individuo
            sumatoria += auxCalculo[cont];
            auxCalculoAcum[cont] = sumatoria - 1;
            cont++;
        }
        auxCalculo[poblacionVieja.getIndividuos().size() - 1] += (1000 - sumatoria); //Corrige problema redondeo para el rango



        //Hasta acá es el calculo de los rangos.

        //Seleccion
        int pos, num;
        for (int i = 0; i < porcentajeSeleccion; i++) {
            aleatorio = generadorAleatorio.nextInt(1000);
            pos = 0;
            for (int j = 0; j < poblacionVieja.getIndividuos().size(); j++) {
                if (j == 0) {
                    if (aleatorio <= auxCalculoAcum[j]) {
                        j = poblacionVieja.getIndividuos().size();
                    }
                } else {
                    if ((aleatorio >= auxCalculoAcum[j - 1] + 1) && (aleatorio <= auxCalculoAcum[j])) {
                        pos = j;
                        j = poblacionVieja.getIndividuos().size();
                    }
                }
            }
            num = 0;
            for (Individuo aux : poblacionVieja.getIndividuos()) {
                if (num == pos) {
                    //agregar de forma Sincronizada al nuevo individuo
                    synchronized (individuos) {
                        individuos.add(new Individuo(aux));
                        //individuos.notify();
                    }
                }
                num++;
            }
        }
    }

    private double redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }
}
