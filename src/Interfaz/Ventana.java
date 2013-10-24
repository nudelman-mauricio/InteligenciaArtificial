package Interfaz;

import LogicaNegocios.Individuo;
import java.awt.BasicStroke;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.SwingWorker;
import java.util.ArrayList;
import Archivo.Archivo;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Ventana extends javax.swing.JFrame {

    static String[] encabezado = {"Valor", "Letra", "Valor", "Letra"};
    static DefaultTableModel contenidoTabla = new DefaultTableModel(encabezado, 0);
    static Archivo unArchivo;
    static String nuevalinea = System.getProperty("line.separator");

    public Ventana() {        
        initComponents();
        
        //Se setea el look and fell
        try {
            UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Actualiza los cambios
        SwingUtilities.updateComponentTreeUI(this);

    }

    public static void crearTabla(DefaultTableModel contenidoTabla) {
        jTableIteraciones.setModel(contenidoTabla);

        int row = jTableIteraciones.getRowCount() - 1;
        Rectangle rect = jTableIteraciones.getCellRect(row, 0, true);
        jTableIteraciones.scrollRectToVisible(rect);
        jTableIteraciones.clearSelection();
        jTableIteraciones.setRowSelectionInterval(row, row);
    }

    public static void resultados(Individuo unIndividuo, int numeroPoblacion, String operacion) {
        jLabelCantIteraciones.setText(String.valueOf(numeroPoblacion));
        jLabelOpeResultado.setText(unIndividuo.convOperacion(operacion));


        //ya ni ganas de comentar, arreglense ustedes

        char[] genes = {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'};
        for (int i = 0; i < jLabelOpeResultado.getText().length(); i++) {
            if (!(jLabelOpeResultado.getText().charAt(i) == '=' || jLabelOpeResultado.getText().charAt(i) == '+' || jLabelOpeResultado.getText().charAt(i) == '-' || jLabelOpeResultado.getText().charAt(i) == '/' || jLabelOpeResultado.getText().charAt(i) == '*' || jLabelOpeResultado.getText().charAt(i) == '(' || jLabelOpeResultado.getText().charAt(i) == ')')) {
                genes[Integer.parseInt(String.valueOf(jLabelOpeResultado.getText().charAt(i)))] = operacion.charAt(i);
            }
        }

        Object datos[] = new Object[4];
        for (int j = 0; j < 5; j++) {
            datos[0] = j + "-";
            datos[1] = genes[j];
            datos[2] = j + 5 + "-";
            datos[3] = genes[j + 5];
            contenidoTabla.addRow(datos);
            jTableGenes.setModel(contenidoTabla);
        }

        //escribir en archivo los resultados
        unArchivo.escribirEnArchivo(nuevalinea + "CantIteraciones: " + jLabelCantIteraciones.getText());
        unArchivo.escribirEnArchivo(nuevalinea + "OperacionCodificada: " + jLabelOpeResultado.getText());
        unArchivo.escribirEnArchivo(nuevalinea + "Genes: " + String.copyValueOf(genes) + nuevalinea + nuevalinea);
        unArchivo.cerrarFW();
    }

    public static void habilitarCampos(boolean estado) {
        jTextOperacion.setEnabled(estado);
        jComboBoxCantidadIndividuos.setEnabled(estado);
        jTextFieldSeleccion.setEnabled(estado);
        jTextFieldCruza.setEnabled(estado);
        jTextFieldMutacion.setEnabled(estado);
        jButtonCalcular.setEnabled(estado);
    }

    public static void graficar(ArrayList<Double> listaAptitudesPromedio) {
        //crear serie de pares X,Y para graficar
        XYSeries unaSerie = new XYSeries("XYGraph");
        for (int i = 0; (i < listaAptitudesPromedio.size()); i++) {
            unaSerie.add(i, listaAptitudesPromedio.get(i));

        }

        // agregar la serie a la coleccion de series a graficar
        XYSeriesCollection unaColeccionSeries = new XYSeriesCollection();
        unaColeccionSeries.addSeries(unaSerie);

        // crear un contenedor de graficos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // titulo del grafico anulado para ganar espacio
                "Cantidad Iteraciones", // etiqueta de eje X
                "Aptitud", // etiqueta de eje Y
                unaColeccionSeries, // la coleccion de series a graficar
                PlotOrientation.VERTICAL, // orientacion del grafico
                false, // leyenda inferior, no mostrar para ahorrar espacio
                true, // uso de los tooltips
                false // generar URLs?
                );

        // configurar colores
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.DARK_GRAY);//LIGHT_GRAY
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // graficar el grafico
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);

        //comenzar con un trazo de linea ancho para desestimar pequeñas variaciones de aptitud
        //ir aumentando a medida que las iteraciones crecen para ganar presicion
        renderer.setSeriesStroke(0, new BasicStroke(7.0f));
        if (unaSerie.getItemCount() > 100) {
            renderer.setSeriesStroke(0, new BasicStroke(6.0f));
        }
        if (unaSerie.getItemCount() > 200) {
            renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        }
        if (unaSerie.getItemCount() > 300) {
            renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        }
        if (unaSerie.getItemCount() > 400) {
            renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        }
        if (unaSerie.getItemCount() > 500) {
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        }
        if (unaSerie.getItemCount() > 600) {
            renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        }

        // crear imagen del grafico
        BufferedImage graficoLinea = chart.createBufferedImage(590, 275);

        // assignar imagen de grafico al label contenedor final
        jLabelGrafica.setIcon(new ImageIcon(graficoLinea));

        // actualizar el contenedor jPanel1 que tiene al label de la imagen del grafico
        //jPanel1.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextOperacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxCantidadIndividuos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldSeleccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCruza = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldMutacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldMutacion1 = new javax.swing.JTextField();
        jButtonCalcular = new javax.swing.JButton();
        jButtonParar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelGrafica = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIteraciones = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelOpeOriginal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelOpeResultado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableGenes = new javax.swing.JTable();
        jLabelCantIteraciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inteligencia Artificial II - Alumnos: Dei Castelli, Nudelman y Wiztke - Año: 2013");
        setBounds(new java.awt.Rectangle(200, 50, 0, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(1050, 602));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso de Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jTextOperacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextOperacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextOperacion.setText("send+more=money");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Operación:");

        jComboBoxCantidadIndividuos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "100", "500", "1000", "5000" }));
        jComboBoxCantidadIndividuos.setSelectedIndex(1);
        jComboBoxCantidadIndividuos.setToolTipText("");

        jLabel1.setText("Cantidad de Individuos");

        jLabel2.setText("% Selección");

        jTextFieldSeleccion.setText("10");

        jLabel3.setText("%Cruza");

        jTextFieldCruza.setText("41");
        jTextFieldCruza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCruzaActionPerformed(evt);
            }
        });

        jLabel4.setText("%Mutación");

        jTextFieldMutacion.setText("49");
        jTextFieldMutacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMutacionActionPerformed(evt);
            }
        });

        jLabel5.setText("Lambda");

        jTextFieldMutacion1.setEditable(false);
        jTextFieldMutacion1.setText("0.00025");
        jTextFieldMutacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMutacion1ActionPerformed(evt);
            }
        });

        jButtonCalcular.setText("Calcular");
        jButtonCalcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCalcularMouseClicked(evt);
            }
        });
        jButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularActionPerformed(evt);
            }
        });

        jButtonParar.setText("Parar");
        jButtonParar.setEnabled(false);
        jButtonParar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonPararMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextOperacion))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldMutacion1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMutacion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCruza, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButtonCalcular)
                                .addGap(49, 49, 49)
                                .addComponent(jButtonParar)))
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCruza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMutacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCalcular)
                    .addComponent(jButtonParar))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grafico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jLabelGrafica.setPreferredSize(new java.awt.Dimension(500, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Iteraciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jTableIteraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Poblacion", "Aptiptud Promedio", "Cantidad Selección", "Cantidad Cruza", "Cantidad Mutacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIteraciones.setEnabled(false);
        jScrollPane1.setViewportView(jTableIteraciones);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        jPanel4.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel6.setText("Operación:");

        jLabelOpeOriginal.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelOpeOriginal.setForeground(new java.awt.Color(255, 0, 0));
        jLabelOpeOriginal.setText(" ");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel8.setText("Operación Codificada:");

        jLabelOpeResultado.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelOpeResultado.setForeground(new java.awt.Color(255, 0, 0));
        jLabelOpeResultado.setText(" ");

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel9.setText("Cantidad de Iteraciones:");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel10.setText("Genes Resultante:");

        jTableGenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0-", null, "5-", null},
                {"1-", null, "6-", null},
                {"2-", null, "7-", null},
                {"3-", null, "8-", null},
                {"4-", null, "9-", null}
            },
            new String [] {
                "Valor", "Letra", "Valor", "Letra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGenes.setEnabled(false);
        jScrollPane3.setViewportView(jTableGenes);

        jLabelCantIteraciones.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelCantIteraciones.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCantIteraciones.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelOpeResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOpeOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCantIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelOpeOriginal)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelOpeResultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelCantIteraciones))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCruzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCruzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCruzaActionPerformed

    private void jTextFieldMutacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMutacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMutacionActionPerformed

    private void jTextFieldMutacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMutacion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMutacion1ActionPerformed

    private void jButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularActionPerformed
    }//GEN-LAST:event_jButtonCalcularActionPerformed

    private void jButtonCalcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCalcularMouseClicked
        //deshabilitar campos
        habilitarCampos(false);

        //mostrar operacion en panel de resultados
        jLabelOpeOriginal.setText(jTextOperacion.getText());

        //Activa el Boton Parar
        jButtonParar.setEnabled(true);

        //grabar en archivo
        unArchivo = new Archivo();
        unArchivo.escribirEnArchivo(nuevalinea + "Operacion: " + jTextOperacion.getText());
        unArchivo.escribirEnArchivo(nuevalinea + "TamañoPoblacion: " + jComboBoxCantidadIndividuos.getSelectedItem().toString());
        unArchivo.escribirEnArchivo(nuevalinea + "%Seleccion: " + jTextFieldSeleccion.getText());
        unArchivo.escribirEnArchivo(nuevalinea + "%Cruza: " + jTextFieldCruza.getText());
        unArchivo.escribirEnArchivo(nuevalinea + "%Mutacion: " + jTextFieldMutacion.getText());

        //limpieza de campos en pestaña resultados
        limpiarTabla(jTableIteraciones);
        limpiarTabla(jTableGenes);
        jLabelCantIteraciones.setText("");
        jLabelOpeResultado.setText("");


        //llamado especial del metodo comenzarAlgoritmo, lo ejecuta en un hilo diferente por ser muy pesado
        //de esta forma se evita que la interfaz se congele
        final SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Main.comenzarAlgoritmo(
                        jTextOperacion.getText(),
                        Integer.parseInt(jComboBoxCantidadIndividuos.getSelectedItem().toString()),
                        Integer.parseInt(jTextFieldSeleccion.getText()),
                        Integer.parseInt(jTextFieldCruza.getText()),
                        Integer.parseInt(jTextFieldMutacion.getText()),
                        ((double) 25) / 100000);
                return null;
            }
        };
        worker.execute();
    }//GEN-LAST:event_jButtonCalcularMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void jButtonPararMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPararMouseClicked
        Main.pararAlgoritmo();
        jButtonCalcular.setEnabled(true);
        jButtonParar.setEnabled(false);
        habilitarCampos(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonPararMouseClicked

    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modeloVacio = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modeloVacio.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButtonCalcular;
    private javax.swing.JButton jButtonParar;
    private static javax.swing.JComboBox jComboBoxCantidadIndividuos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private static javax.swing.JLabel jLabelCantIteraciones;
    private static javax.swing.JLabel jLabelGrafica;
    private javax.swing.JLabel jLabelOpeOriginal;
    private static javax.swing.JLabel jLabelOpeResultado;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JTable jTableGenes;
    private static javax.swing.JTable jTableIteraciones;
    private static javax.swing.JTextField jTextFieldCruza;
    private static javax.swing.JTextField jTextFieldMutacion;
    private javax.swing.JTextField jTextFieldMutacion1;
    private static javax.swing.JTextField jTextFieldSeleccion;
    private static javax.swing.JTextField jTextOperacion;
    // End of variables declaration//GEN-END:variables
}
