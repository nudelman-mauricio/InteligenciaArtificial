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
//import Archivo.Archivo;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Ventana extends javax.swing.JFrame {

    static String[] encabezado = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    static DefaultTableModel contenidoTabla = new DefaultTableModel(encabezado, 0);
//    static Archivo unArchivo;
    static String nuevalinea = System.getProperty("line.separator");
    double lambda = 0;
    DecimalFormat formatoDecimal = new DecimalFormat("0.00000");
    
    public Ventana() {
        initComponents();
        try {
            UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Actualiza los cambios
        SwingUtilities.updateComponentTreeUI(this);

    }

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonCalcular = new javax.swing.JButton();
        jButtonParar = new javax.swing.JButton();
        jSliderCruza = new javax.swing.JSlider();
        jSliderSeleccion = new javax.swing.JSlider();
        jSliderMutacion = new javax.swing.JSlider();
        jSliderLambda = new javax.swing.JSlider();
        jLabelLambda = new javax.swing.JLabel();
        jLabelMutacion = new javax.swing.JLabel();
        jLabelCruza = new javax.swing.JLabel();
        jLabelSeleccion = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelGrafica = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIteraciones = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelTiempo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelOpeResultado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableGenes = new javax.swing.JTable();
        jLabelCantIteraciones = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelPromSeleccion = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelPromCruza = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabelPromMutacion = new javax.swing.JLabel();
        jLabelOpeOriginal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inteligencia Artificial II - Alumnos: Dei Castelli, Nudelman y Witzke - Año: 2013");
        setBounds(new java.awt.Rectangle(200, 50, 0, 0));
        setPreferredSize(new java.awt.Dimension(990, 620));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(1050, 590));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso de Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jTextOperacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextOperacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextOperacion.setText("send+more=money");
        jTextOperacion.setPreferredSize(new java.awt.Dimension(201, 21));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Operación:");

        jComboBoxCantidadIndividuos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "100", "500", "1000", "5000" }));
        jComboBoxCantidadIndividuos.setSelectedIndex(2);
        jComboBoxCantidadIndividuos.setToolTipText("");

        jLabel1.setText("Cantidad de Individuos");

        jLabel2.setText("% Selección");

        jLabel3.setText("%Cruza");

        jLabel4.setText("%Mutación");

        jLabel5.setText("Lambda");

        jButtonCalcular.setText("Calcular");
        jButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularActionPerformed(evt);
            }
        });

        jButtonParar.setText("Parar");
        jButtonParar.setEnabled(false);
        jButtonParar.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonParar.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonParar.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPararActionPerformed(evt);
            }
        });

        jSliderCruza.setValue(70);
        jSliderCruza.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderCruzaStateChanged(evt);
            }
        });

        jSliderSeleccion.setValue(20);
        jSliderSeleccion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderSeleccionStateChanged(evt);
            }
        });

        jSliderMutacion.setValue(10);
        jSliderMutacion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderMutacionStateChanged(evt);
            }
        });

        jSliderLambda.setValue(25);
        jSliderLambda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderLambdaStateChanged(evt);
            }
        });

        jLabelLambda.setText("0.00025");
        jLabelLambda.setToolTipText("");

        jLabelMutacion.setText("10");
        jLabelMutacion.setToolTipText("");

        jLabelCruza.setText("70");
        jLabelCruza.setToolTipText("");

        jLabelSeleccion.setText("20");
        jLabelSeleccion.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButtonCalcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonParar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSliderCruza, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSliderLambda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSliderSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jSliderMutacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLambda)
                            .addComponent(jLabelMutacion)
                            .addComponent(jLabelCruza)
                            .addComponent(jLabelSeleccion))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxCantidadIndividuos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSliderSeleccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelSeleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jSliderCruza, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCruza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSliderMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelMutacion, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jSliderLambda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelLambda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonParar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCalcular))
                .addContainerGap())
        );

        jLabelMutacion.getAccessibleContext().setAccessibleName("10");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grafico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(619, 275));
        jPanel1.setRequestFocusEnabled(false);

        jLabelGrafica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Archivo/Grafico limpio.png"))); // NOI18N
        jLabelGrafica.setPreferredSize(new java.awt.Dimension(500, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Iteraciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        jTableIteraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Poblacion", "Aptiptud Promedio", "Mejor Individuo", "Cantidad Cruza", "Cantidad Mutacion"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        jPanel4.setToolTipText("");
        jPanel4.setPreferredSize(new java.awt.Dimension(340, 100));
        jPanel4.setRequestFocusEnabled(false);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Operación: ");
        jLabel6.setMaximumSize(new java.awt.Dimension(308, 17));
        jLabel6.setPreferredSize(new java.awt.Dimension(308, 17));

        jLabelTiempo.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelTiempo.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTiempo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTiempo.setText("  ");
        jLabelTiempo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Operación Codificada: ");
        jLabel8.setPreferredSize(new java.awt.Dimension(308, 17));

        jLabelOpeResultado.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelOpeResultado.setForeground(new java.awt.Color(255, 0, 0));
        jLabelOpeResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpeResultado.setText("  ");
        jLabelOpeResultado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabelOpeResultado.setPreferredSize(new java.awt.Dimension(308, 17));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Iteraciones:");
        jLabel9.setMaximumSize(new java.awt.Dimension(118, 17));
        jLabel9.setMinimumSize(new java.awt.Dimension(118, 17));
        jLabel9.setPreferredSize(new java.awt.Dimension(118, 17));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Genes Resultante:");
        jLabel10.setPreferredSize(new java.awt.Dimension(308, 17));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTableGenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableGenes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableGenes.setEnabled(false);
        jTableGenes.setMaximumSize(new java.awt.Dimension(2147483647, 222222));
        jTableGenes.setMinimumSize(new java.awt.Dimension(300, 79));
        jTableGenes.setPreferredSize(new java.awt.Dimension(300, 79));
        jTableGenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableGenes);

        jLabelCantIteraciones.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelCantIteraciones.setForeground(new java.awt.Color(255, 0, 0));
        jLabelCantIteraciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCantIteraciones.setText("  ");
        jLabelCantIteraciones.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tiempo:");
        jLabel11.setMaximumSize(new java.awt.Dimension(118, 17));
        jLabel11.setMinimumSize(new java.awt.Dimension(118, 17));
        jLabel11.setPreferredSize(new java.awt.Dimension(118, 17));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Promedio Selección:");
        jLabel12.setMaximumSize(new java.awt.Dimension(118, 17));
        jLabel12.setMinimumSize(new java.awt.Dimension(118, 17));
        jLabel12.setPreferredSize(new java.awt.Dimension(118, 17));

        jLabelPromSeleccion.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelPromSeleccion.setForeground(new java.awt.Color(255, 0, 0));
        jLabelPromSeleccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPromSeleccion.setText("  ");
        jLabelPromSeleccion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Promedio Cruza:");
        jLabel13.setMaximumSize(new java.awt.Dimension(118, 17));
        jLabel13.setMinimumSize(new java.awt.Dimension(118, 17));
        jLabel13.setPreferredSize(new java.awt.Dimension(118, 17));

        jLabelPromCruza.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelPromCruza.setForeground(new java.awt.Color(255, 0, 0));
        jLabelPromCruza.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPromCruza.setText("  ");
        jLabelPromCruza.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Promedio Mutación:");
        jLabel14.setMaximumSize(new java.awt.Dimension(118, 17));
        jLabel14.setMinimumSize(new java.awt.Dimension(118, 17));
        jLabel14.setPreferredSize(new java.awt.Dimension(118, 17));

        jLabelPromMutacion.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelPromMutacion.setForeground(new java.awt.Color(255, 0, 0));
        jLabelPromMutacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPromMutacion.setText("  ");
        jLabelPromMutacion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabelOpeOriginal.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabelOpeOriginal.setForeground(new java.awt.Color(255, 0, 0));
        jLabelOpeOriginal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOpeOriginal.setText(" ");
        jLabelOpeOriginal.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelOpeOriginal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabelOpeOriginal.setPreferredSize(new java.awt.Dimension(308, 17));
        jLabelOpeOriginal.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelOpeResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOpeOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPromCruza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelPromMutacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelCantIteraciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelPromSeleccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelOpeOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelOpeResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCantIteraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPromSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPromCruza, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPromMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularActionPerformed
        if (((Integer.parseInt(jLabelSeleccion.getText()) + Integer.parseInt(jLabelCruza.getText()) + Integer.parseInt(jLabelMutacion.getText())) == 100)) {
            //deshabilitar campos
            habilitarCampos(false);

            //descolorea los field
            jLabelSeleccion.setBackground(null);
            jLabelMutacion.setBackground(null);
            jLabelCruza.setBackground(null);

            //mostrar operacion en panel de resultados
            jLabelOpeOriginal.setText(jTextOperacion.getText());

            //setear tamaño de barra de progreso
            jProgressBar1.setMaximum(Integer.parseInt(jComboBoxCantidadIndividuos.getSelectedItem().toString()));
            jProgressBar1.setMinimum(0);

//            //grabar en archivo
//            unArchivo = new Archivo();
//            unArchivo.escribirEnArchivo(nuevalinea + "Operacion: " + jTextOperacion.getText());
//            unArchivo.escribirEnArchivo(nuevalinea + "TamañoPoblacion: " + jComboBoxCantidadIndividuos.getSelectedItem().toString());
//            unArchivo.escribirEnArchivo(nuevalinea + "%Seleccion: " + jTextFieldSeleccion.getText());
//            unArchivo.escribirEnArchivo(nuevalinea + "%Cruza: " + jTextFieldCruza.getText());
//            unArchivo.escribirEnArchivo(nuevalinea + "%Mutacion: " + jTextFieldMutacion.getText());

            //limpieza de campos en pestaña resultados
            limpiarTabla(jTableIteraciones);
            limpiarTabla(jTableGenes);
            jLabelCantIteraciones.setText("");
            jLabelOpeResultado.setText("");
            jLabelTiempo.setText("");
            jLabelPromSeleccion.setText("");
            jLabelPromCruza.setText("");
            jLabelPromMutacion.setText("");


            //llamado especial del metodo comenzarAlgoritmo, lo ejecuta en un hilo diferente por ser muy pesado
            //de esta forma se evita que la interfaz se congele
            final SwingWorker worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    Main.comenzarAlgoritmo(
                            jTextOperacion.getText(),
                            Integer.parseInt(jComboBoxCantidadIndividuos.getSelectedItem().toString()),
                            Integer.parseInt(jLabelSeleccion.getText()),
                            Integer.parseInt(jLabelCruza.getText()),
                            Integer.parseInt(jLabelMutacion.getText()),
                            (double)jSliderLambda.getValue()/100000);
                    return null;
                }
            };
            worker.execute();
        } else {
            //colorea en rojo los fiedl debido a un error que no da el 100%
//            ---------------------REVISAR---------------------
//            jTextFieldSeleccion.setBackground(Color.red);
//            jTextFieldMutacion.setBackground(Color.red);
//            jTextFieldCruza.setBackground(Color.red);
        }
    }//GEN-LAST:event_jButtonCalcularActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void jButtonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPararActionPerformed
        Main.pararAlgoritmo();
        habilitarCampos(true);
    }//GEN-LAST:event_jButtonPararActionPerformed

    private void jSliderLambdaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderLambdaStateChanged
        lambda = jSliderLambda.getValue();
        jLabelLambda.setText(formatoDecimal.format(lambda/100000));
    }//GEN-LAST:event_jSliderLambdaStateChanged

    private void jSliderMutacionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderMutacionStateChanged
        jLabelMutacion.setText(String.valueOf(jSliderMutacion.getValue()));
    }//GEN-LAST:event_jSliderMutacionStateChanged

    private void jSliderCruzaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderCruzaStateChanged
        jLabelCruza.setText(String.valueOf(jSliderCruza.getValue()));
    }//GEN-LAST:event_jSliderCruzaStateChanged

    private void jSliderSeleccionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderSeleccionStateChanged
        jLabelSeleccion.setText(String.valueOf(jSliderSeleccion.getValue()));
    }//GEN-LAST:event_jSliderSeleccionStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButtonCalcular;
    private static javax.swing.JButton jButtonParar;
    private static javax.swing.JComboBox jComboBoxCantidadIndividuos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private static javax.swing.JLabel jLabel9;
    private static javax.swing.JLabel jLabelCantIteraciones;
    private static javax.swing.JLabel jLabelCruza;
    private static javax.swing.JLabel jLabelGrafica;
    private static javax.swing.JLabel jLabelLambda;
    private static javax.swing.JLabel jLabelMutacion;
    private static javax.swing.JLabel jLabelOpeOriginal;
    private static javax.swing.JLabel jLabelOpeResultado;
    private static javax.swing.JLabel jLabelPromCruza;
    private static javax.swing.JLabel jLabelPromMutacion;
    private static javax.swing.JLabel jLabelPromSeleccion;
    private static javax.swing.JLabel jLabelSeleccion;
    private static javax.swing.JLabel jLabelTiempo;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private static javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JSlider jSliderCruza;
    private static javax.swing.JSlider jSliderLambda;
    private static javax.swing.JSlider jSliderMutacion;
    private static javax.swing.JSlider jSliderSeleccion;
    private static javax.swing.JTable jTableGenes;
    private static javax.swing.JTable jTableIteraciones;
    private static javax.swing.JTextField jTextOperacion;
    // End of variables declaration//GEN-END:variables

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

    public static void crearTabla(DefaultTableModel contenidoTabla) {
        jTableIteraciones.setModel(contenidoTabla);

        int row = jTableIteraciones.getRowCount() - 1;
        Rectangle rect = jTableIteraciones.getCellRect(row, 0, true);
        jTableIteraciones.scrollRectToVisible(rect);
        jTableIteraciones.clearSelection();
        jTableIteraciones.setRowSelectionInterval(row, row);
    }

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

    public static void mostrarResultados(Individuo unIndividuo, int numeroPoblacion, String operacion, String tiempo, String promedioSeleccion, String promedioCruza, String promedioMutacion) {
        jLabelCantIteraciones.setText(String.valueOf(numeroPoblacion));
        jLabelOpeResultado.setText(unIndividuo.convOperacion(operacion));
        jLabelTiempo.setText(tiempo + " seg");
        jLabelPromSeleccion.setText(promedioSeleccion);
        jLabelPromCruza.setText(promedioCruza);
        jLabelPromMutacion.setText(promedioMutacion);

        //procesar genes del resultado
        char[] genes = {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'};
        for (int i = 0; i < jLabelOpeResultado.getText().length(); i++) {
            if (!(jLabelOpeResultado.getText().charAt(i) == '=' || jLabelOpeResultado.getText().charAt(i) == '+' || jLabelOpeResultado.getText().charAt(i) == '-' || jLabelOpeResultado.getText().charAt(i) == '/' || jLabelOpeResultado.getText().charAt(i) == '*' || jLabelOpeResultado.getText().charAt(i) == '(' || jLabelOpeResultado.getText().charAt(i) == ')')) {
                genes[Integer.parseInt(String.valueOf(jLabelOpeResultado.getText().charAt(i)))] = operacion.charAt(i);
            }
        }
        //mostrar genes del resultado en tabla de genes
        Object datos[] = new Object[10];
        for (int i = 0; i < 10; i++) {
            datos[i] = genes[i];
        }
        contenidoTabla.addRow(datos);
        jTableGenes.setModel(contenidoTabla);
        
//        //escribir en archivo los resultados
//        unArchivo.escribirEnArchivo(nuevalinea + "CantIteraciones: " + jLabelCantIteraciones.getText());
//        unArchivo.escribirEnArchivo(nuevalinea + "Tiempo: " + tiempo + " seg");
//        unArchivo.escribirEnArchivo(nuevalinea + "OperacionCodificada: " + jLabelOpeResultado.getText());
//        unArchivo.escribirEnArchivo(nuevalinea + "Genes: " + String.copyValueOf(genes) + nuevalinea + nuevalinea);
//        unArchivo.cerrarFW();
    }

    public static void habilitarCampos(boolean estado) {
        jTextOperacion.setEnabled(estado);
        jComboBoxCantidadIndividuos.setEnabled(estado);
        jSliderSeleccion.setEnabled(estado);
        jSliderCruza.setEnabled(estado);
        jSliderMutacion.setEnabled(estado);
        jSliderLambda.setEnabled(estado);
        jButtonCalcular.setEnabled(estado);
        jButtonParar.setEnabled(!estado);
    }

    public static void graficar(ArrayList<Double> listaAptitudesPromedio, ArrayList<Double> listaMejorIndividuo) {
        //crear serie de pares X,Y para graficar
        XYSeries serieAptPromedio = new XYSeries("Grafico Aptiptud Promedio");
        XYSeries serieMejorIndividuo = new XYSeries("Grafico Mejor Individuo");
        for (int i = 0; (i < listaAptitudesPromedio.size()); i++) {
            serieAptPromedio.add(i, listaAptitudesPromedio.get(i));
            serieMejorIndividuo.add(i,listaMejorIndividuo.get(i));
        }

        // agregar la serie a la coleccion de series a graficar
        XYSeriesCollection unaColeccionSeries = new XYSeriesCollection();
        unaColeccionSeries.addSeries(serieAptPromedio);
        unaColeccionSeries.addSeries(serieMejorIndividuo);
        
        // crear un contenedor de graficos
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // titulo del grafico anulado para ganar espacio
                "Generaciones", // etiqueta de eje X
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
        renderer.setSeriesPaint(1, Color.BLUE);

        //comenzar con un trazo de linea ancho para desestimar pequeñas variaciones de aptitud
        //ir aumentando a medida que las iteraciones crecen para ganar presicion
        renderer.setSeriesStroke(0, new BasicStroke(7.0f));
        renderer.setSeriesStroke(1, new BasicStroke(7.0f));
        
        if (serieAptPromedio.getItemCount() > 100) {
            renderer.setSeriesStroke(0, new BasicStroke(6.0f));
            renderer.setSeriesStroke(1, new BasicStroke(6.0f));
        }
        if (serieAptPromedio.getItemCount() > 200) {
            renderer.setSeriesStroke(0, new BasicStroke(5.0f));
            renderer.setSeriesStroke(1, new BasicStroke(5.0f));
        }
        if (serieAptPromedio.getItemCount() > 300) {
            renderer.setSeriesStroke(0, new BasicStroke(4.0f));
            renderer.setSeriesStroke(1, new BasicStroke(4.0f));
        }
        if (serieAptPromedio.getItemCount() > 400) {
            renderer.setSeriesStroke(0, new BasicStroke(3.0f));
            renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        }
        if (serieAptPromedio.getItemCount() > 500) {
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        }
        if (serieAptPromedio.getItemCount() > 600) {
            renderer.setSeriesStroke(0, new BasicStroke(1.0f));
            renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        }

        // crear imagen del grafico
        BufferedImage graficoLinea = chart.createBufferedImage(590, 275);

        // assignar imagen de grafico al label contenedor final
        jLabelGrafica.setIcon(new ImageIcon(graficoLinea));
    }

    public static void cargarBarra(int posicion) {
        jProgressBar1.setValue(posicion);
    }
}
