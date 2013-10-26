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
        //Setea la parada en falso
        parar=false; 
        
        //Generar primer población ALEATORIA
        this.poblacionActual = new Poblacion(this.operacion, this.cantIndividuos, this.restricciones);

        //generar poblaciones nuevas a partir de una vieja mientras no se alcance un individuo resultado
        while (this.poblacionActual.esSolucion() == null && !parar) {

            //mostrar datos en tabla
            Object datos[] = {this.poblacionNumero, poblacionActual.aptitudProm(), this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion};
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
        }

        //mostrar tabla
        Object datos[] = {poblacionNumero, poblacionActual.aptitudProm(), this.porcentajeSeleccion, this.porcentajeCruza, this.porcentajeMutacion};
        this.contenidoTabla.addRow(datos);
        Ventana.crearTabla(this.contenidoTabla);
        
        //mostrar gráfico
        this.listaAptitudesPromedio.add(poblacionActual.aptitudProm());
        Ventana.graficar(this.listaAptitudesPromedio);
        
        //mostrar solucion en pestaña resultados
        Ventana.mostrarResultados(poblacionActual.esSolucion(), poblacionNumero, this.operacion);

        //habilitar campos para nueva
        Ventana.habilitarCampos(true);
    }
    
    public void pararAlgoritmo(){
        this.parar = true;
    }
}
