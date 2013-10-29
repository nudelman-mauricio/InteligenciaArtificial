package LogicaNegocios;

import Interfaz.Ventana;
import Operadores.Seleccion;
import Operadores.Cruza;
import Operadores.Mutacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Poblacion {

    private Set<Individuo> individuos = new TreeSet();
    private Random r = new Random();

    //Constructor para generar primer poblacion aleatoria
    public Poblacion(String operacion, int cantIndividuos, ArrayList<ArrayList<Integer>> restricciones) {
        //obtiene 10 letras diferentes a partir de la operacion        
        String unVectorPalabra = vectorPalabraOperacion(operacion);
        //creacion de x cantidad de individuos nuevos y aleatorios
        for (int i = 0; i < cantIndividuos; i++) {         
            this.individuos.add(new Individuo(mezclaVector(unVectorPalabra), operacion, restricciones));
            Ventana.cargarBarra(individuos.size());
        }
    }

    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(String operacion, int cantIndividuos, Poblacion poblacionActual, ArrayList<ArrayList<Integer>> restricciones, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, int maximaAptitud) {
        
        //Seleccion Ruleta        
        Seleccion unaSeleccion = new Seleccion(poblacionActual, porcentajeSeleccion, maximaAptitud, this.individuos);        
        //Cruza Ciclica       
        Cruza unaCruza = new Cruza(poblacionActual, porcentajeCruza, restricciones, operacion, this.individuos);       
        //Mutacion
        Mutacion unaMutacion = new Mutacion(poblacionActual, porcentajeMutacion, restricciones, operacion, this.individuos);
       
        //metodo que ejecuta los operadores en forma paralela
        ejecutarOperadores(unaSeleccion, unaCruza, unaMutacion);
    }
    
    private void ejecutarOperadores(Seleccion unaSeleccion, Cruza unaCruza, Mutacion unaMutacion){
        //creacion de hilos
        Thread hiloSeleccion = new Thread(unaSeleccion, "Seleccion");
        Thread hiloCruza = new Thread(unaCruza, "Cruza");
        Thread hiloMutacion = new Thread(unaMutacion, "Mutacion");
        
        //ejecucion de hilos paralelos
        hiloSeleccion.start();
        hiloCruza.start();
        hiloMutacion.start();
        
        try {
            hiloSeleccion.join();
            hiloCruza.join();
            hiloMutacion.join();            
        } catch (InterruptedException ex) {
            Logger.getLogger(Poblacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        for (Individuo aux : this.individuos) {
            if (aux.getAptitud() == 0) {
                resultado = aux;
            }
        }
        return resultado;
    }

    public double aptitudProm() {
        double sum = 0;
        for (Individuo aux : this.individuos) {
            sum += aux.getAptitud();
        }
        return (sum / this.individuos.size());
    }

    public Set<Individuo> getIndividuos() {
        return this.individuos;
    }      

    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
