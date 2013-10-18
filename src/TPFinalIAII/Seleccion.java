package TPFinalIAII;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Seleccion {

    Poblacion poblacionVieja;
    int porcentajeSeleccion;
    int maximaAptitud;
    Set<Individuo> individuosResultados;    
    private Random generadorAleatorio;
    int aleatorio;

    public Seleccion(Poblacion poblacion, int porcentajeSeleccion, int maximaAptitud) {
        this.poblacionVieja = poblacion;
        this.porcentajeSeleccion = porcentajeSeleccion;
        this.maximaAptitud = maximaAptitud;
        this.generadorAleatorio = new Random();
        this.individuosResultados = new TreeSet();
    }

    public Set<Individuo> seleccionRuleta() {
        
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
                    Individuo unIndividuo = new Individuo(aux);
                    individuosResultados.add(unIndividuo);
                }
                num++;
            }
        }
        return (this.individuosResultados);
    }

    private double redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }
}
