package LogicaNegocios;

import Operadores.Seleccion;
import Operadores.Cruza;
import Operadores.Mutacion;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Poblacion {

    private Set<Individuo> individuos = new TreeSet();
    private Random r = new Random();
    private String operacion;

    //Constructor para generar primer poblacion aleatoria
    public Poblacion(String operacion, int cantIndividuos, ArrayList<ArrayList<Integer>> restricciones) {
        this.operacion = operacion;
        //obtiene 10 letras diferentes a partir de la operacion        
        String unVectorPalabra = vectorPalabraOperacion(operacion);
        //creacion de x cantidad de individuos nuevos y aleatorios
        for (int i = 0; i < cantIndividuos; i++) {         
            this.individuos.add(new Individuo(mezclaVector(unVectorPalabra), operacion, restricciones));
        }
    }

    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(int cantIndividuos, Poblacion poblacion, ArrayList<ArrayList<Integer>> restricciones, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, int maximaAptitud) {
        
        //Seleccion Ruleta        
        Seleccion unaSeleccion = new Seleccion(poblacion, porcentajeSeleccion, maximaAptitud, Poblacion.this);        
        //Cruza Ciclica        
        Cruza unaCruza = new Cruza(poblacion, porcentajeCruza, restricciones, operacion, Poblacion.this);       
        //Mutacion
        Mutacion unaMutacion = new Mutacion(poblacion, porcentajeMutacion, restricciones, operacion, Poblacion.this);
       
        //metodo que ejecuta los operadores en forma paralela
        ejecutarOperadores(unaSeleccion, unaCruza, unaMutacion);
        System.out.println("fin llamador");
    }
    
    private void ejecutarOperadores(Seleccion unaSeleccion, Cruza unaCruza, Mutacion unaMutacion){
        //creacion de hilos
        Thread hiloSeleccion = new Thread(unaSeleccion);
        Thread hiloCruza = new Thread(unaCruza);
        Thread hiloMutacion = new Thread(unaMutacion);
        
        //ejecucion de hilos paralelos
        hiloSeleccion.start();
        hiloCruza.start();
        hiloMutacion.start();
        
        try {
            System.out.println("esperando....");
            hiloSeleccion.join();
            hiloCruza.join();
            hiloMutacion.join();            
        } catch (InterruptedException ex) {
            Logger.getLogger(Poblacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("fin espera");
    }
    
    public synchronized void agregarIndividuo(Individuo unIndividuo){
        this.individuos.add(unIndividuo);
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
}
