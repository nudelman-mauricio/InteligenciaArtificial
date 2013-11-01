package LogicaNegocios;

import java.util.ArrayList;
import Interfaz.Ventana;
import javax.swing.table.DefaultTableModel;

public class AlgoritmoGenetico {

    private String operacion;
    private ArrayList<ArrayList<Integer>> restricciones;
    private int maximaAptitud;
    private int cantIndividuos;
    private Poblacion poblacionActual, poblacionNueva;
    private int poblacionNumero;
    private int porcentajeSeleccion, porcentajeCruza, porcentajeMutacion;
    private double lambda;
    private int mutacionMax, mutacionMin;
    private double mutacionAcumulador;
    private static String[] encabezado = {"Nro Poblacion", "Aptiptud Promedio", "Cantidad Selección", "Cantidad Cruza", "Cantidad Mutación"};
    private static DefaultTableModel contenidoTabla = new DefaultTableModel(encabezado, 0);
    private ArrayList<Double> listaAptitudesPromedio;
    private boolean parar = false;

    public AlgoritmoGenetico(int maximaAptitud, String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, double lambda, ArrayList<ArrayList<Integer>> restricciones) {
        this.maximaAptitud = maximaAptitud;
        this.operacion = operacion;
        this.cantIndividuos = cantIndividuos;
        this.porcentajeSeleccion = porcentajeSeleccion;
        this.porcentajeCruza = porcentajeCruza;
        this.porcentajeMutacion = porcentajeMutacion;
        this.lambda = lambda;
        this.restricciones = restricciones;
        this.mutacionMax = ((int) (0.50 * cantIndividuos));
        this.mutacionMin = ((int) (0.10 * cantIndividuos));
        this.mutacionAcumulador = 0;
        this.poblacionNumero = 1;
        this.listaAptitudesPromedio = new ArrayList<Double>();
    }

    public void comenzarAlgoritmo() {
        double promedioCruza = 0, promedioMutacion = 0;

        //Setea la parada en falso
        parar = false;

        //guardar tiempo en que comenzó la ejecucion del algoritmo
        double startTime = System.currentTimeMillis() * 0.001;

        //Generar primer población ALEATORIA
        this.poblacionActual = new Poblacion(this.operacion, this.cantIndividuos, this.restricciones);

        //generar poblaciones nuevas a partir de una vieja mientras no se alcance un individuo resultado
        while (this.poblacionActual.esSolucion() == null && !parar) {
            
            //mostrar datos en tabla
            Object datos[] = {this.poblacionNumero, redondear(poblacionActual.aptitudProm(), 2), this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion};
            this.contenidoTabla.addRow(datos);
            Ventana.crearTabla(this.contenidoTabla);

            //mostrar datos en grafico
            this.listaAptitudesPromedio.add(poblacionActual.aptitudProm());
            Ventana.graficar(this.listaAptitudesPromedio);

            //crar nueva poblacion con los operadores
            this.poblacionNueva = new Poblacion(this.operacion, this.cantIndividuos, this.poblacionActual, this.restricciones, this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion, this.maximaAptitud);
            this.poblacionActual = this.poblacionNueva;
            this.poblacionNumero++;

            //calculo de mutacion adaptativa por temperatura ascendente
            this.mutacionAcumulador += this.lambda * this.cantIndividuos;
            if (this.mutacionAcumulador >= 1) {
                if (this.porcentajeMutacion < this.mutacionMax) {
                    this.porcentajeMutacion++;
                    this.porcentajeCruza--;
                    this.mutacionAcumulador = 0; //Setea devuelta a 0 para solucionar el problema que sumaba siempre 
                } else {
                    this.porcentajeMutacion = this.mutacionMin;
                    this.porcentajeCruza = (this.cantIndividuos - this.porcentajeMutacion - this.porcentajeSeleccion);
                }
            }
            promedioCruza += this.porcentajeCruza;
            promedioMutacion += this.porcentajeMutacion;
            Ventana.cargarBarra(this.cantIndividuos);
        }
        //obtener tiempo en el que finalizó la ejecucion del algoritmo, por encontrar solucion o por parada forzosa
        double stopTime = System.currentTimeMillis() * 0.001;
       
        //mostrar tabla
        Object datos[] = {poblacionNumero, redondear(poblacionActual.aptitudProm(),2), this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion};
        this.contenidoTabla.addRow(datos);
        Ventana.crearTabla(this.contenidoTabla);

        //mostrar gráfico
        this.listaAptitudesPromedio.add(poblacionActual.aptitudProm());
        Ventana.graficar(this.listaAptitudesPromedio);

        //calculo de promedio de operadores
        promedioCruza = redondear(promedioCruza / (poblacionNumero - 1) * 100 / cantIndividuos, 2);
        promedioMutacion = redondear(promedioMutacion / (poblacionNumero - 1) * 100 / cantIndividuos, 2);

        //mostrar solucion en pestaña resultados
        Ventana.mostrarResultados(poblacionActual.esSolucion(),
                poblacionNumero,
                this.operacion,
                Double.toString(redondear(Math.rint((stopTime - startTime) * 100) / 100, 2)),
                String.valueOf(this.porcentajeSeleccion * 100 / cantIndividuos),
                String.valueOf(promedioCruza),
                String.valueOf(promedioMutacion));

        //habilitar campos para nueva
        Ventana.habilitarCampos(true);

    }

    private double redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }

    public void pararAlgoritmo() {
        this.parar = true;
    }
}
