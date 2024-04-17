

package interfaces;

import entidades.ConsultasAnalizador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Clase que genera la vista de 'INConfigurarRelacionesExamenesAnalizador'. Esta
 * vista se encarga de relacionar los exámenes registrados para el analizador
 * con los exámenes registrados del sistema. De esta manera, cuando el
 * analizador arroje resultados, el sistema pueda entender a que exámenes van
 * dirigidos los resultados.
 */
public class INConfigurarRelacionesExamenesAnalizador extends javax.swing.JFrame {

    //Atributos
    private TableRowSorter trsAnalizador;
    private TableRowSorter trsRegistrados;

    //Constructor
    public INConfigurarRelacionesExamenesAnalizador() {
        initComponents();
        
        actualizarTablaExamenesAnalizador();
        actualizarTablaExamenesSistema();
        actualizarTablaExamenesRelacionados();

        tablaExamenesAnalizador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaExamenesRelacionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaExamenesSistema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        trsAnalizador = new TableRowSorter((DefaultTableModel) tablaExamenesAnalizador.getModel());
        trsRegistrados = new TableRowSorter((DefaultTableModel) tablaExamenesSistema.getModel());
        
        this.setExtendedState(this.MAXIMIZED_BOTH);
        
    }

    /**
     * Método que ajusta los bordes, y el color de fondo del InternalFrame para
     * hacerlo más agradable y adaptable al DesktopPane de GUIPrincipal.
     *
     * Incluye también el evento de ESC para cerrar rápido una ventana.
     */
    

    /**
     * Elimina y carga la lista de exámenes registrados para el analizador
     * dentro de tablaExamenesAnalizador que se encuentren en la base de datos.
     */
    public void actualizarTablaExamenesAnalizador() {
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesAnalizador.getModel();
        modelo.setRowCount(0);

        Object[][] exams = ConsultasAnalizador.darExamenesAnalizadorDispnonibles();
        for (Object[] exam : exams) {
            if (exam[0] != null) {
                Object[] temp = {false, exam[1], exam[2]};
                modelo.addRow(temp);
            }
        }
    }

    /**
     * Elimina y carga la lista de exámenes registrados en el sistema dentro de
     * tablaExamenesAnalizador.
     */
    public void actualizarTablaExamenesSistema() {
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesSistema.getModel();
        modelo.setRowCount(0);

        Object[][] exams = ConsultasAnalizador.darExamenesSistema();
        for (Object[] exam : exams) {
            if (exam[0] != null) {
                Object[] temp = {false, exam[0], exam[1], exam[2]};
                modelo.addRow(temp);
            }
        }
    }

    /**
     * Elimina y carga la lista de exámenes relacionados entre exámenes del
     * analizador y del sistema dentro de tablaExamenesRealcionados.
     */
    public void actualizarTablaExamenesRelacionados() {
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesRelacionados.getModel();
        modelo.setRowCount(0);

        Object[][] examens = ConsultasAnalizador.darExamenesRelacionados();
        for (Object[] examen : examens) {
            if (examen[0] != null) {
                modelo.addRow(examen);
            }
        }
    }

    /**
     * Determina si hay algún elemento seleccionado dentro de
     * tablaExamenesAnalizador.
     *
     * @return true si algun elemento de la tabla está seleccionado, false de lo
     * contrario.
     */
    public boolean tablaExamenesAnalizadorIsSelected() {

        for (int i = 0; i < tablaExamenesAnalizador.getRowCount(); i++) {
            if ((boolean) tablaExamenesAnalizador.getValueAt(i, 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determina si hay algún elemento seleccionado dentro de
     * tablaExamenesSistema.
     *
     * @return true si algun elemento de la tabla está seleccionado, false de lo
     * contrario.
     */
    public boolean tablaExamenesSistemaIsSelected() {

        for (int i = 0; i < tablaExamenesSistema.getRowCount(); i++) {
            if ((boolean) tablaExamenesSistema.getValueAt(i, 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Método encargado de generar un filtro con el TableRowSort, el campo, la
     * columna y la tabla especificada por parámetro.
     *
     * Gracias a este método se reduce la redundancia de código y permite a
     * agregar un filtro a desde cualquier componente a cualquier tabla que se
     * requiera.
     *
     * @param pTrs representa el modelo de filtrado.
     * @param pTextField textFiled de donde se saca los datos para el filtro.
     * @param pColumna número de la columna de la tabla donde se desea filtrar.
     * @param pTabla tabla donde se aplicará el modelo de filtrado.
     */
    public void filtrarTabla(TableRowSorter pTrs, JTextField pTextField, int pColumna, JTable pTabla) {
        //Añade el evento keyListener al campo.
        pTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                //Crea el nuevo modelo de filtrado.
                pTrs.setRowFilter(RowFilter.regexFilter("(?i)" + pTextField.getText(), pColumna));
            }
        });
        //Establece el modelo de filtrado a la tabla.
        pTabla.setRowSorter(pTrs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoExamenAnalizador = new javax.swing.JTextField();
        txtNombreExamenAnalizador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExamenesAnalizador = new javax.swing.JTable();
        butRelacionar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoExamenSistema = new javax.swing.JTextField();
        txtAbreviaturaExamenSistema = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreExamenSistema = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaExamenesSistema = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaExamenesRelacionados = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        butEliminarRelacion = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Exámenes analizador");

        txtCodigoExamenAnalizador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoExamenAnalizadorKeyTyped(evt);
            }
        });

        txtNombreExamenAnalizador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreExamenAnalizadorKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel2.setText("Código");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel3.setText("Nombre Examen");

        tablaExamenesAnalizador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "...", "Código", "Nombre Examen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExamenesAnalizador.getTableHeader().setResizingAllowed(false);
        tablaExamenesAnalizador.getTableHeader().setReorderingAllowed(false);
        tablaExamenesAnalizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaExamenesAnalizadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaExamenesAnalizador);
        if (tablaExamenesAnalizador.getColumnModel().getColumnCount() > 0) {
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setMinWidth(20);
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setMaxWidth(20);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setMinWidth(80);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setPreferredWidth(80);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoExamenAnalizador, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNombreExamenAnalizador))))
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoExamenAnalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreExamenAnalizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        butRelacionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_asignar_48.png"))); // NOI18N
        butRelacionar.setText("Relacionar");
        butRelacionar.setToolTipText("");
        butRelacionar.setEnabled(false);
        butRelacionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butRelacionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butRelacionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRelacionarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Exámenes registrados");

        txtCodigoExamenSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoExamenSistemaKeyTyped(evt);
            }
        });

        txtAbreviaturaExamenSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbreviaturaExamenSistemaKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel5.setText("Código");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel6.setText("Abreviatura");

        txtNombreExamenSistema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreExamenSistemaKeyTyped(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel7.setText("Nombre");

        tablaExamenesSistema.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "...", "Código", "Abreviatura", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExamenesSistema.setToolTipText("");
        tablaExamenesSistema.getTableHeader().setResizingAllowed(false);
        tablaExamenesSistema.getTableHeader().setReorderingAllowed(false);
        tablaExamenesSistema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaExamenesSistemaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaExamenesSistema);
        if (tablaExamenesSistema.getColumnModel().getColumnCount() > 0) {
            tablaExamenesSistema.getColumnModel().getColumn(0).setMinWidth(20);
            tablaExamenesSistema.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaExamenesSistema.getColumnModel().getColumn(0).setMaxWidth(20);
            tablaExamenesSistema.getColumnModel().getColumn(1).setMinWidth(80);
            tablaExamenesSistema.getColumnModel().getColumn(1).setPreferredWidth(80);
            tablaExamenesSistema.getColumnModel().getColumn(1).setMaxWidth(80);
            tablaExamenesSistema.getColumnModel().getColumn(2).setMinWidth(80);
            tablaExamenesSistema.getColumnModel().getColumn(2).setPreferredWidth(80);
            tablaExamenesSistema.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoExamenSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbreviaturaExamenSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreExamenSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel7))))
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoExamenSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbreviaturaExamenSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreExamenSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tablaExamenesRelacionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo (A)", "Nombre (A)", "Codigo (S)", "Abreviatura (S)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExamenesRelacionados.getTableHeader().setResizingAllowed(false);
        tablaExamenesRelacionados.getTableHeader().setReorderingAllowed(false);
        tablaExamenesRelacionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaExamenesRelacionadosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaExamenesRelacionados);

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Exámenes relacionados");

        butEliminarRelacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_cancelar_16.png"))); // NOI18N
        butEliminarRelacion.setText("Eliminar relación");
        butEliminarRelacion.setPreferredSize(new java.awt.Dimension(145, 35));
        butEliminarRelacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarRelacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butEliminarRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(butEliminarRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(butRelacionar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(243, 243, 243)
                            .addComponent(butRelacionar)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(418, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butRelacionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRelacionarActionPerformed

        String codigoAnalizador, codigoSistema;
        codigoAnalizador = codigoSistema = "";

        //Se obtiene el código del exámen del analizador que se quiere relacionar.
        for (int i = 0; i < tablaExamenesAnalizador.getRowCount(); i++) {
            if ((boolean) tablaExamenesAnalizador.getValueAt(i, 0)) {
                codigoAnalizador = tablaExamenesAnalizador.getValueAt(i, 1).toString();
            }
        }

        //Se obtiene el código del exámen del sistema que se quiere relacionar.
        for (int i = 0; i < tablaExamenesSistema.getRowCount(); i++) {
            if ((boolean) tablaExamenesSistema.getValueAt(i, 0)) {
                codigoSistema = tablaExamenesSistema.getValueAt(i, 1).toString();
            }
        }

        //Se realiza una consulta para relacionar ambos exámenes.
        try{
        ConsultasAnalizador.crearRelacionExamenes(codigoAnalizador, codigoSistema);
        actualizarTablaExamenesSistema();
        actualizarTablaExamenesRelacionados();
        actualizarTablaExamenesAnalizador();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error Al crear relacion");
        }

    }//GEN-LAST:event_butRelacionarActionPerformed

    private void tablaExamenesRelacionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaExamenesRelacionadosMouseClicked
    }//GEN-LAST:event_tablaExamenesRelacionadosMouseClicked

    private void txtCodigoExamenAnalizadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoExamenAnalizadorKeyTyped

        txtNombreExamenAnalizador.setText("");
        filtrarTabla(trsAnalizador, txtCodigoExamenAnalizador, 1, tablaExamenesAnalizador);

    }//GEN-LAST:event_txtCodigoExamenAnalizadorKeyTyped

    private void txtNombreExamenAnalizadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreExamenAnalizadorKeyTyped

        txtCodigoExamenAnalizador.setText("");
        filtrarTabla(trsAnalizador, txtNombreExamenAnalizador, 2, tablaExamenesAnalizador);

    }//GEN-LAST:event_txtNombreExamenAnalizadorKeyTyped

    private void txtCodigoExamenSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoExamenSistemaKeyTyped

        txtAbreviaturaExamenSistema.setText("");
        txtNombreExamenSistema.setText("");
        filtrarTabla(trsRegistrados, txtCodigoExamenSistema, 1, tablaExamenesSistema);

    }//GEN-LAST:event_txtCodigoExamenSistemaKeyTyped

    private void txtAbreviaturaExamenSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbreviaturaExamenSistemaKeyTyped

        txtCodigoExamenSistema.setText("");
        txtNombreExamenSistema.setText("");
        filtrarTabla(trsRegistrados, txtAbreviaturaExamenSistema, 2, tablaExamenesSistema);

    }//GEN-LAST:event_txtAbreviaturaExamenSistemaKeyTyped

    private void txtNombreExamenSistemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreExamenSistemaKeyTyped

        txtCodigoExamenSistema.setText("");
        txtAbreviaturaExamenSistema.setText("");
        filtrarTabla(trsRegistrados, txtNombreExamenSistema, 3, tablaExamenesSistema);

    }//GEN-LAST:event_txtNombreExamenSistemaKeyTyped

    private void butEliminarRelacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarRelacionActionPerformed

        int row = tablaExamenesRelacionados.getSelectedRow();
        //Si se selecciona un elemento de la tabla
        if (tablaExamenesRelacionados.getSelectedRow() != -1) {

            //Obtiene el codigo del exámen del analizador y del exámen del sistema de la relación que se desea eliminar.
            String codigoAnalizador = tablaExamenesRelacionados.getValueAt(row, 0).toString();
            String codigoSistema = tablaExamenesRelacionados.getValueAt(row, 2).toString();

            //Elimina la relación a través de una consulta
            ConsultasAnalizador.eliminarRelacionExamenes(codigoAnalizador, codigoSistema);
            actualizarTablaExamenesAnalizador();
            actualizarTablaExamenesRelacionados();
            actualizarTablaExamenesSistema();
        }
    }//GEN-LAST:event_butEliminarRelacionActionPerformed

    private void tablaExamenesAnalizadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaExamenesAnalizadorMouseClicked
        // TODO add your handling code here:
         int row = tablaExamenesAnalizador.getSelectedRow();
        //Si se selecciona un elemento de la tabla
        if (row != -1) {

            //Si la tabla clickeada no se encuentra seleccionada.
            if (!tablaExamenesAnalizadorIsSelected()) {
                //Checkea el elemento seleccionado.
                tablaExamenesAnalizador.setValueAt(true, row, 0);
            } else {
                int rowSelected = -1;
                //Recorre la tabla para identificar el numero de fila del elemento seleccionado.
                for (int i = 0; i < tablaExamenesAnalizador.getRowCount(); i++) {
                    if ((boolean) tablaExamenesAnalizador.getValueAt(i, 0)) {
                        rowSelected = i;
                        break;
                    }
                }

                //Al elemento encontrado lo deschequea 
                tablaExamenesAnalizador.setValueAt(false, rowSelected, 0);

                //Si el elemento encontrado es distinto al que se seleccionó por el evento.
                if (rowSelected != row) {
                    //Lo chequea.
                    tablaExamenesAnalizador.setValueAt(true, row, 0);
                }
            }

            comprobarRelacion();
        }
    }//GEN-LAST:event_tablaExamenesAnalizadorMouseClicked

    private void tablaExamenesSistemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaExamenesSistemaMouseClicked
        // TODO add your handling code here:
        int row = tablaExamenesSistema.getSelectedRow();
        //Si se selecciona un elemento de la tabla
        if (row != -1) {

            //Si la tabla clickeada no se encuentra seleccionada.
            if (!tablaExamenesSistemaIsSelected()) {
                //Checkea el elemento seleccionado.
                tablaExamenesSistema.setValueAt(true, row, 0);
            } else {
                int rowSelected = -1;
                //Recorre la tabla para identificar el numero de fila del elemento seleccionado.
                for (int i = 0; i < tablaExamenesSistema.getRowCount(); i++) {
                    if ((boolean) tablaExamenesSistema.getValueAt(i, 0)) {
                        rowSelected = i;
                        break;
                    }
                }

                //Al elemento encontrado lo deschequea 
                tablaExamenesSistema.setValueAt(false, rowSelected, 0);

                //Si el elemento encontrado es distinto al que se seleccionó por el evento.
                if (rowSelected != row) {
                    //Lo chequea.
                    tablaExamenesSistema.setValueAt(true, row, 0);
                }
            }

            comprobarRelacion();
        }
    }//GEN-LAST:event_tablaExamenesSistemaMouseClicked

    /**
     * Activa el botón para relacionar exámenes si ambas tablas
     * (tablaExamenesAnalizador y tablaExameneSistema) tienen algún elemento
     * seleccionado.
     */
    public void comprobarRelacion() {
        butRelacionar.setEnabled(tablaExamenesAnalizadorIsSelected() && tablaExamenesSistemaIsSelected());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butEliminarRelacion;
    private javax.swing.JButton butRelacionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaExamenesAnalizador;
    private javax.swing.JTable tablaExamenesRelacionados;
    private javax.swing.JTable tablaExamenesSistema;
    private javax.swing.JTextField txtAbreviaturaExamenSistema;
    private javax.swing.JTextField txtCodigoExamenAnalizador;
    private javax.swing.JTextField txtCodigoExamenSistema;
    private javax.swing.JTextField txtNombreExamenAnalizador;
    private javax.swing.JTextField txtNombreExamenSistema;
    // End of variables declaration//GEN-END:variables
}
