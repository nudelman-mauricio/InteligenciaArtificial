package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

public class Cruza implements Runnable {

    Poblacion poblacionVieja;
    int porcentajeCruza;
    ArrayList<ArrayList<Integer>> restricciones;
    String operacion;
    Set<Individuo> individuos;
    private Random generadorAleatorio;
    int aleatorio;

    public Cruza(Poblacion poblacion, int porcentajeCruza, ArrayList<ArrayList<Integer>> restricciones, String operacion, Set<Individuo> individuos) {
        this.poblacionVieja = poblacion;
        this.porcentajeCruza = porcentajeCruza;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.individuos = individuos;
        this.generadorAleatorio = new Random();
    }
    
    @Override
    public void run() {
//    private Set<Individuo> cruzaCiclico(Poblacion poblacionVieja, int porcentajeCruza, ArrayList<ArrayList<Integer>> restricciones, String operacion) {
        //Set<Individuo> individuosResultados = new TreeSet();
        Individuo unIndividuo;
        char[] padre = null, madre = null, hijo1 = new char[10], hijo2 = new char[10];
        boolean bandera = true, cantImp = false;
        int aux1 = 1, aux2 = 1;

        if (porcentajeCruza % 2 != 0) {
            porcentajeCruza = porcentajeCruza + 1;
            cantImp = true;
        }

        for (int j = 0; j < (porcentajeCruza / 2); j++) {
            hijo1 = new char[10];
            hijo2 = new char[10];
            bandera = true;
            aux1 = 1;
            aux2 = 1;

            padre = progenitorAleatorio(poblacionVieja).toCharArray();
            madre = progenitorAleatorio(poblacionVieja).toCharArray();

            for (int i = 0; i < 10; i++) {
                if (padre[i] == '#') {
                    padre[i] = Character.forDigit(aux1, 10);
                    aux1++;
                }
                if (madre[i] == '#') {
                    madre[i] = Character.forDigit(aux2, 10);
                    aux2++;
                }
            }

            //si son iguales los cromosomas hay que elegir otro para porque sino quedan iguales los hijos
            int pos = 0;
            for (int i = 0; i < 10; i++) {
                if (padre[i] != madre[i]) {
                    pos = i;
                    i = 10;
                }
            }
            hijo1[pos] = padre[pos];
            hijo2[pos] = madre[pos];

            while (bandera) {
                for (int i = 0; i < 10; i++) {
                    if (hijo2[pos] == padre[i]) {
                        if (hijo1[i] == 0) {
                            hijo1[i] = padre[i];
                            hijo2[i] = madre[i];
                            pos = i;
                            i = -1;
                        } else {
                            bandera = false;
                            i = 10;
                        }
                    }
                }
            }
            for (int i = 0; i < 10; i++) {
                if (hijo1[i] == 0) {
                    hijo1[i] = madre[i];
                    hijo2[i] = padre[i];
                }
            }
            for (int i = 0; i < 10; i++) {
                if (Character.isDigit(hijo1[i])) {
                    hijo1[i] = '#';
                }
                if (Character.isDigit(hijo2[i])) {
                    hijo2[i] = '#';
                }
            }

            unIndividuo = new Individuo(String.copyValueOf(hijo1), operacion, restricciones);
            agregarIndividuo(unIndividuo);

            if (!((cantImp) && (j + 1 == (porcentajeCruza / 2)))) {
                unIndividuo = new Individuo(String.copyValueOf(hijo1), operacion, restricciones);
                agregarIndividuo(unIndividuo);
            }
        }
    }
    
    private synchronized void agregarIndividuo(Individuo individuo){
        this.individuos.add(individuo);
    }

    private String progenitorAleatorio(Poblacion poblacionVieja) {
        int cont = 0;
        String padre = null;
        aleatorio = generadorAleatorio.nextInt(poblacionVieja.getIndividuos().size());
        for (Individuo aux : poblacionVieja.getIndividuos()) {
            if (cont == aleatorio) {
                padre = aux.getGenes();
            }
            cont++;
        }
        return padre;
    }
}
