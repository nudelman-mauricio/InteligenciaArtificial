package TPFinalIAII;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Iterator;

public class Poblacion {

    private Set<Individuo> individuos = new TreeSet();
    private Random r = new Random();
    private String operacion;

    //Constructor para generar primer poblacion aleatoria
    public Poblacion(String operacion, int cantIndividuos, ArrayList<ArrayList<Integer>> restricciones) {
        this.operacion = operacion;
        String vectorPalabra = vectorPalabraOperacion(operacion);
        for (int i = 0; i < cantIndividuos; i++) {
            Individuo unIndividuo = new Individuo(mezclaVector(vectorPalabra), operacion, restricciones);
            this.individuos.add(unIndividuo);
        }
    }

    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(String operacion, int cantIndividuos, Poblacion poblacion, ArrayList<ArrayList<Integer>> restricciones, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, int maximaAptitud) {

        //Seleccion Ruleta        
        Seleccion unaSeleccion = new Seleccion(poblacion, porcentajeSeleccion, maximaAptitud, this.individuos);
        //Hilo de Seleccion Ruleta
        Thread hiloSeleccion = new Thread(unaSeleccion);
        //Comenzar Seleccion
        hiloSeleccion.start();

        //Cruza Ciclica  -------------------------------------------------------        
        this.individuos.addAll(cruzaCiclico(poblacion, porcentajeCruza, restricciones, operacion));

        //Mutacion -------------------------------------------------------
        this.individuos.addAll(mutacion(poblacion, porcentajeMutacion, restricciones, operacion));
        
        
    }

    private String vectorPalabraOperacion(String operacion) {
        String operando = "";
        String operacionCompleta = "";
        boolean bandera = true;
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) != '=' && bandera == true) {
                if ((operando.indexOf(operacion.charAt(i)) == -1) && (operacion.charAt(i) != '+') && (operacion.charAt(i) != '-') && (operacion.charAt(i) != ')') && (operacion.charAt(i) != '(') && (operacion.charAt(i) != '/') && (operacion.charAt(i) != '*') && (operacion.charAt(i) != ' ')) {
                    operando += operacion.charAt(i);
                    operacionCompleta += operacion.charAt(i);
                }
            } else {
                bandera = false;
                if ((operacionCompleta.indexOf(operacion.charAt(i)) == -1) && (operacion.charAt(i) != '+') && (operacion.charAt(i) != '-') && (operacion.charAt(i) != ')') && (operacion.charAt(i) != '(') && (operacion.charAt(i) != '/') && (operacion.charAt(i) != '*') && (operacion.charAt(i) != ' ') && (operacion.charAt(i) != '=')) {
                    operacionCompleta += operacion.charAt(i);
                }
            }
        }
        if (operacionCompleta.length() > 10) {
            operando = null;
        } else {
            for (int i = operando.length(); i < 10; i++) {
                operando += '#';
            }
        }
        return operando;
    }

    private String mezclaVector(String palabra) {
        String resultado = "";
        int nrand1, aux = palabra.length();

        for (int i = 0; i < aux; i++) {
            nrand1 = r.nextInt(palabra.length());
            resultado += palabra.charAt(nrand1);
            palabra = palabra.substring(0, nrand1) + palabra.substring(nrand1 + 1);
        }
        return resultado;// retorna resultado
    }

    public Individuo esSolucion() {
        Individuo resultado = null;
        for (Individuo aux : individuos) {
            if (aux.getAptitud() == 0) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public double aptitudProm() {
        double sum = 0;
        for (Individuo aux : individuos) {
            sum += aux.getAptitud();
        }
        return (sum / individuos.size());
    }

    public Set<Individuo> getIndividuos() {
        return this.individuos;
    }    
    
    //Mutacion -------------------------------------------------------
    private Set<Individuo> mutacion(Poblacion poblacionAnterior, int porcentajeMutacion, ArrayList<ArrayList<Integer>> restricciones, String operacion){
        Set<Individuo> individuosResultados = new TreeSet();
        Iterator it = poblacionAnterior.getIndividuos().iterator();
        for (int i = 0; i < porcentajeMutacion; i++) {
            Individuo aux = (Individuo) it.next();
            //unIndividuo = new Individuo(aux.mutacion(), operacion, restricciones);
            individuosResultados.add(new Individuo(aux.mutacion(), operacion, restricciones));
        }
        return (individuosResultados);
    }    
}
