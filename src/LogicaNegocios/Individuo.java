package LogicaNegocios;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Individuo implements Comparable {

    private double aptitud;
    private String genes;
    private boolean imposible;
    private String cumple = "";

    public Individuo(String palabra, String operacion, ArrayList<ArrayList<Integer>> restricciones) { //palabra = string de letras únicas correspondiente a la operacion
        this.genes = palabra;
        this.imposible = false;

        //Calculo de aptitud
        setAptitud(convOperacion(operacion), restricciones); //convOperacion es para pasar operacion a numeros        
    }

    public Individuo(Individuo unIndividuo) {
        this.aptitud = unIndividuo.getAptitud();
        this.genes = unIndividuo.getGenes();
    }

    public double getAptitud() {
        return this.aptitud;
    }

    //Calcular la APTITUD del individuo respecto de la operacion guardada
    private void setAptitud(String operacion, ArrayList<ArrayList<Integer>> restricciones) {
        if (!imposible) {
            int auxAptitud = 0;
            boolean bandera = true;

            for (int i = 0; i < restricciones.size(); i++) {
                int contador = 0;

                auxAptitud += (i + 1) * (i + 1);

                for (int j = 0; j < restricciones.get(i).size(); j++) {
                    for (int k = contador; k < operacion.length(); k++) {
                        if (k == restricciones.get(i).get(j)) {
                            if (operacion.charAt(k) != operacion.charAt(restricciones.get(i).get(0))) { //Si en la posicion de la restriccion tiene mismo valor.
                                bandera = false;
                            }
                            k = operacion.length();

                        } else {
                            if (operacion.charAt(k) == operacion.charAt(restricciones.get(i).get(0))) {
                                bandera = false;
                                k = operacion.length();
                            }
                        }
                        contador++;
                    }
                }
                if (bandera) {
                    auxAptitud -= (i + 1) * (i + 1);
                    cumple += '1';
                } else {
                    cumple += '0';
                }
                bandera = true;
            }
            this.aptitud = auxAptitud;
        } else {
            int maximaAptitud = 0;
            for (int i = 0; i < restricciones.size(); i++) {
                maximaAptitud += (i + 1) * (i + 1);
            }

            this.aptitud = maximaAptitud;
        }
    }

    //convierto la operacion de letras en numeros a partir de los genes del individuo
    //peeeero el resultado se calcula a partir de la operacion y no de los genes
    public String convOperacion(String operacion) {
        //traducir de letras a numeros
        String resultado = "", numResultado = "";
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '=') {
                i = operacion.length();
            } else {
                if (operacion.charAt(i) == '+' || operacion.charAt(i) == '-' || operacion.charAt(i) == '/' || operacion.charAt(i) == '*' || operacion.charAt(i) == '(' || operacion.charAt(i) == ')') {
                    resultado += String.valueOf(operacion.charAt(i));
                } else {
                    for (int j = 0; j < this.genes.length(); j++) {
                        if (operacion.charAt(i) == this.genes.charAt(j)) {
                            resultado += String.valueOf(j);
                        }
                    }
                }
            }
        }
        //calcular el resultado solamente
        try {
            numResultado = calcularString(resultado);
        } catch (ScriptException ex) {
            Logger.getLogger(Individuo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //rellenar con ceros cuando se pierden por el calculo
        //luego concatenar lo traducido con el resultado calculado
        int longResultado = operacion.length() - resultado.length() - 1; // resto uno por el simbolo =        
        while (longResultado > numResultado.length()) {
            numResultado = "0" + numResultado;
        }
        resultado += "=" + numResultado;

        //preguntar si la longitud del resultado calculado con los ceros agregados
        //difiere de la longitud del resultado de la operacion ingresada originalmente
        //de ser asi, la operacion resulta imposible con la genetica del individuo y debe descartarse el mismo
        if (longResultado != numResultado.length()) {
            this.imposible = true;
        }

        return (resultado);
    }

    /**
     * metodo para calcular el resultado de una expresion matematica definido en
     * un string
     */
    private static String calcularString(String cadena) throws ScriptException {
        String aux = "";

        //Metodo para eliminar los ceros que estan adelante para que pueda calcular correctamente
        int num = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != '0' || i != num) {
                aux += cadena.charAt(i);
                if (cadena.charAt(i) == '+' || cadena.charAt(i) == '-' || cadena.charAt(i) == '*' || cadena.charAt(i) == '/') {
                    num = i + 1;
                }
            } else {
                //Para que no borre un 0 que tiene solo un digito
                if (cadena.charAt(i) == '0' && i == (cadena.length() - 1)) {
                    aux += cadena.charAt(i);
                } else {
                    if (cadena.charAt(i + 1) == '+' || cadena.charAt(i + 1) == '-' || cadena.charAt(i + 1) == '*' || cadena.charAt(i + 1) == '/') {
                        aux += cadena.charAt(i);
                    } else {
                        num++;
                    }
                }

            }

        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        long resultado = ((Number) engine.eval(aux)).longValue();

        return Long.toString(resultado);
    }

    @Override
    public int compareTo(Object aux) {
        int retorno = 0;
        if (aux instanceof Individuo) {
            Individuo unIndividuo = (Individuo) aux;
            if (this.aptitud > unIndividuo.getAptitud()) {
                retorno = 1;
            } else {
                retorno = -1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return (this.genes + " - " + this.aptitud);
    }

    public String getGenes() {
        return this.genes;
    }

    public void mutarme(String operacion, ArrayList<ArrayList<Integer>> restricciones) {
        this.genes = mutacion();
        //Calculo de aptitud
        setAptitud(convOperacion(operacion), restricciones); //convOperacion es para pasar operacion a numeros
    }

    public String mutacion() {
        Random r = new Random();
        boolean bandera1 = false, bandera2 = false;
        char[] mutado;
        int nrand1 = 0, nrand2 = 0;
        for (int i = cumple.length()-1; i>0; i--) {
            if (cumple.charAt(i) == '0') {
                for (int j = 0; j < this.genes.length(); j++) {
                    if (cumple.charAt(i) == genes.charAt(j)) {
                        nrand1 = j;
                        bandera1 = true;
                    }
                }
            }
        }
        for (int i = cumple.length()-1; i>0; i--) {
            if (cumple.charAt(i) == '0' && i != nrand1) {
                for (int j = 0; j < this.genes.length(); j++) {
                    if (cumple.charAt(i) == genes.charAt(j)) {
                        nrand2 = j;
                        bandera2 = true;
                    }
                }
            }
        }

        while (nrand1 == nrand2) {
            if (!bandera1) {
                nrand1 = r.nextInt(10);
            }
            if (!bandera2) {
                nrand2 = r.nextInt(10);
            }
        }
        mutado = this.genes.toCharArray();
        mutado[nrand1] = this.genes.charAt(nrand2);
        mutado[nrand2] = this.genes.charAt(nrand1);

        return String.copyValueOf(mutado);
    }
}
