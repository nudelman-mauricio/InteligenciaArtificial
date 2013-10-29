package Operadores;

import LogicaNegocios.Individuo;
import LogicaNegocios.Poblacion;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

public class Cruza implements Runnable {

    private Poblacion poblacionVieja;
    private int porcentajeCruza;
    private ArrayList<ArrayList<Integer>> restricciones;
    private String operacion;
    private Set<Individuo> individuos;
    private Random generadorAleatorio;

    public Cruza(Poblacion poblacionActual, int porcentajeCruza, ArrayList<ArrayList<Integer>> restricciones, String operacion, Set<Individuo> individuos) {
        this.poblacionVieja = poblacionActual;
        this.porcentajeCruza = porcentajeCruza;
        this.restricciones = restricciones;
        this.operacion = operacion;
        this.individuos = individuos;
        this.generadorAleatorio = new Random();
    }

    @Override
    public void run() {
        char[] padre, madre, hijo1, hijo2;
        boolean bandera, cantidadImpar = false;
        int pos;

        //prueba si la cantidad es impar. de serlo agrega uno para hacerla par y que el algoritmo funcione
        //luego el ultimo individuo se descarta para devolver exactamente la cantidad que pidieron
        if (this.porcentajeCruza % 2 != 0) {
            this.porcentajeCruza++;
            cantidadImpar = true;
        }
        //se repite la creacion de un hijo cruza de dos y su complemento como segundo hijo
        for (int j = 0; j < (this.porcentajeCruza / 2); j++) {
            hijo1 = new char[10];
            hijo2 = new char[10];
            bandera = true;

            //tomar progenitores aleatorios
            padre = progenitorAleatorio();
            madre = progenitorAleatorio();
            //bucle para asegurarse de tener dos progenitores diferentes entre si
            while (sonIguales(padre, madre)) {
                madre = progenitorAleatorio();
            }

            //sacar # para que no existan caracteres repetidos en cada progenitor
            padre = quitarNumerales(padre);
            madre = quitarNumerales(madre);

            //elegir punto inicial de cruza ciclica
            //si son iguales los cromosomas hay que elegir otro punto porque sino quedan iguales los hijos
            pos = 0;
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
                            i--;
                        } else {
                            bandera = false;
                            i = 10;
                        }
                    }
                }
            }

            //armado de los hijos
            for (int i = 0; i < 10; i++) {
                if (hijo1[i] == 0) {
                    hijo1[i] = madre[i];
                    hijo2[i] = padre[i];
                }
            }

            //quitar los numeros y volver a poner #
            hijo1 = ponerNumerales(hijo1);
            hijo2 = ponerNumerales(hijo2);
            padre = ponerNumerales(padre);

            //creacion de nuevos individuos
            Individuo unHijo1 = new Individuo(String.copyValueOf(hijo1), operacion, restricciones);
            Individuo unHijo2 = new Individuo(String.copyValueOf(hijo2), operacion, restricciones);

            //en el caso de que los individuos nuevos sean iguales a sus padres se los muta
            if (sonIguales(hijo1, padre)) {
                unHijo1.mutarme();
                unHijo2.mutarme();
            }

            //agregar de forma Sincronizada al nuevo individuo hijo1
            synchronized (individuos) {
                individuos.add(unHijo1);
                individuos.notify();
            }

            //agregar segundo hijo de forma sincronizada, pero solo si la cantidad de hijos solicitada es par,
            //de lo contrario ignora al segundo hijo
            if (!((cantidadImpar) && (j + 1 == (porcentajeCruza / 2)))) {
                //agregar de forma Sincronizada al nuevo individuo
                synchronized (individuos) {
                    individuos.add(unHijo2);
                    individuos.notify();
                }
            }
        }
    }

    private char[] progenitorAleatorio() {
        int cont = 0;
        char[] progenitor = null;
        int aleatorio = this.generadorAleatorio.nextInt(this.poblacionVieja.getIndividuos().size());
        for (Individuo aux : this.poblacionVieja.getIndividuos()) {
            if (cont == aleatorio) {
                progenitor = aux.getGenes().toCharArray();
            }
            cont++;
        }
        return progenitor;
    }

    //busca los # y los cambia por numeros consecutivos para que no existan caracteres repetidos
    private char[] quitarNumerales(char[] genes) {
        int numeroEscribir = 1;
        for (int i = 0; i < 10; i++) {
            if (genes[i] == '#') {
                genes[i] = Character.forDigit(numeroEscribir, 10);
                numeroEscribir++;
            }
        }
        return genes;
    }

    //busca los numeros y los cambia por numerales
    private char[] ponerNumerales(char[] genes) {
        for (int i = 0; i < 10; i++) {
            if (Character.isDigit(genes[i])) {
                genes[i] = '#';
            }
        }
        return genes;
    }

    //compara dos arreglos de caracteres, retorna TRUE si son iguales
    private boolean sonIguales(char[] unArreglo, char[] otroArreglo) {
        boolean resultado = true;
        for (int i = 0; i < 10; i++) {
            if (unArreglo[i] != otroArreglo[i]) {
                resultado = false;
                i = 10;
            }
        }
        return resultado;
    }
}
