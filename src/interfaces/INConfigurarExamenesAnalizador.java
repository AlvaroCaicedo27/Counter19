package interfaces;

import entidades.ConsultasAnalizador;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que genera la vista de 'INConfigurarExamenesAnalizador'. Esta vista se
 * encarga de parametrizar toda la información referente a los exámenes que usa
 * el analizador de muestras.
 */
public class INConfigurarExamenesAnalizador extends javax.swing.JFrame {

    //Constructor
    public INConfigurarExamenesAnalizador() {
        initComponents();
        
        actualizarTablaExamenesAnalizador();
    }

    /**
     * Método que ajusta los bordes, y el color de fondo del InternalFrame para
     * hacerlo más agradable y adaptable al DesktopPane de GUIPrincipal.
     *
     * Incluye también el evento de ESC para cerrar rápido una ventana.
     */
   

    /**
     * Limpia los elemento de registro del panel de parámetros del exámen de
     * analizador para dejarlos por defecto.
     */
    public void limpiarParametros() {
        txtCodigo.setText("");
        txtCodigo.setBackground(Color.white);
        txtNombre.setText("");
        txtNombre.setBackground(Color.white);
    }

    /**
     * Elimina y carga la lista de exámenes del analizador dentro de la
     * tablaExamenesAnalizador que se encuentren registradas en la base de
     * datos.
     */
    public void actualizarTablaExamenesAnalizador() {
        DefaultTableModel modelo = (DefaultTableModel) tablaExamenesAnalizador.getModel();
        modelo.setRowCount(0);

        Object[][] exams = ConsultasAnalizador.darExamenesAnalizador();
        for (Object[] exam : exams) {
            if (exam[0] != null) {
                modelo.addRow(exam);
            }
        }
    }

    /**
     * Encapsula toda la información se encuentre registradas en los componentes
     * del panel de parámetros.
     *
     * @return Object[] con toda la información del exámen del analizador.
     */
    public String[] getData() {
        String codigoExamen = txtCodigo.getText().trim();
        String nombreExamen = txtNombre.getText().trim();

        String[] data = {codigoExamen, nombreExamen};
        return data;
    }

    /**
     * Llama todos los componentes del panelHerramientas para habilitarlos o
     * deshabilitarlos.
     *
     * @param pTurn Estado habilitado/deshabilitado para los componentes del
     * panel.
     */
    public void turnPanelHerramientas(boolean pTurn) {
        Component[] components = panelHerramientas.getComponents();
        for (Component component : components) {
            component.setEnabled(pTurn);
        }
    }

    /**
     * Método que consulta y carga en el panel de parámetro la información de un
     * exámen del analizador dado su consecutivo. Se llama cuando se requiere
     * actualizar la información del exámen.
     *
     * @param pConsecutivo Consecutivo del exámen del analizador a la cual se le
     * requiere cargar su información.
     */
    public void cargarDatos(String pConsecutivo) {
        Object[] data = ConsultasAnalizador.darExamenAnalizadorPorConsecutivo(pConsecutivo);
        txtCodigo.setText(data[0].toString());
        txtNombre.setText(data[1].toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelParametros = new javax.swing.JPanel();
        butAgregar = new javax.swing.JButton();
        butDeshacer = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExamenesAnalizador = new javax.swing.JTable();
        panelHerramientas = new javax.swing.JPanel();
        butEditar = new javax.swing.JButton();
        butEliminar = new javax.swing.JButton();

        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        panelParametros.setBackground(new java.awt.Color(255, 255, 255));
        panelParametros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        butAgregar.setBackground(new java.awt.Color(255, 255, 255));
        butAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_guardar_24.png"))); // NOI18N
        butAgregar.setToolTipText("Guardar Cambios Valor Referencia");
        butAgregar.setBorder(null);
        butAgregar.setPreferredSize(new java.awt.Dimension(40, 40));
        butAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarActionPerformed(evt);
            }
        });

        butDeshacer.setBackground(new java.awt.Color(255, 255, 255));
        butDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_deshacer_24.png"))); // NOI18N
        butDeshacer.setToolTipText("Deshacer Edición");
        butDeshacer.setBorder(null);
        butDeshacer.setPreferredSize(new java.awt.Dimension(40, 40));
        butDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDeshacerActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Agregar Exámen de Analizador");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel1.setText("Código");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel2.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelParametrosLayout = new javax.swing.GroupLayout(panelParametros);
        panelParametros.setLayout(panelParametrosLayout);
        panelParametrosLayout.setHorizontalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelParametrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(butDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(panelParametrosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        panelParametrosLayout.setVerticalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelParametrosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        butDeshacer.setVisible(false);

        tablaExamenesAnalizador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "consecutivo", "Código", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExamenesAnalizador.getTableHeader().setResizingAllowed(false);
        tablaExamenesAnalizador.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaExamenesAnalizador);
        if (tablaExamenesAnalizador.getColumnModel().getColumnCount() > 0) {
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setMinWidth(0);
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaExamenesAnalizador.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setMinWidth(80);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setPreferredWidth(80);
            tablaExamenesAnalizador.getColumnModel().getColumn(1).setMaxWidth(80);
        }

        panelHerramientas.setBackground(new java.awt.Color(244, 244, 244));
        panelHerramientas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        butEditar.setBackground(new java.awt.Color(235, 235, 235));
        butEditar.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        butEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_editar_24.png"))); // NOI18N
        butEditar.setToolTipText("Editar Exámen Analizador");
        butEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        butEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butEditar.setIconTextGap(1);
        butEditar.setPreferredSize(new java.awt.Dimension(48, 48));
        butEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEditarActionPerformed(evt);
            }
        });
        panelHerramientas.add(butEditar);

        butEliminar.setBackground(new java.awt.Color(235, 235, 235));
        butEliminar.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        butEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_papeleraLlena_24.png"))); // NOI18N
        butEliminar.setToolTipText("Eliminar Exámen de Analizador");
        butEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        butEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butEliminar.setIconTextGap(1);
        butEliminar.setPreferredSize(new java.awt.Dimension(48, 48));
        butEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarActionPerformed(evt);
            }
        });
        panelHerramientas.add(butEliminar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panelParametros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelParametros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void butAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarActionPerformed

        //Si todos los datos ingresados para la sección son correctos, continua.
        if (verificarInvariantes()) {

            //Si el botón 'Deshacer' no está visible, significa que quiere agregar.
            //Si no, es porque quiere actualizar.
            if (!butDeshacer.isVisible()) {
                if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea agregar esta referencia de exámen?", "Agregar Exámen de Analizador", JOptionPane.YES_NO_OPTION) == 0) {
                    ConsultasAnalizador.insertarExamenAnalizador(getData());
                    limpiarParametros();
                    actualizarTablaExamenesAnalizador();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea actualizar esta servicio?", "Actualizar Servicio", JOptionPane.YES_NO_OPTION) == 0) {
                    ConsultasAnalizador.actualizarExamenAnalizador(tablaExamenesAnalizador.getValueAt(tablaExamenesAnalizador.getSelectedRow(), 0).toString(), getData());
                    tablaExamenesAnalizador.setEnabled(true);
                    butDeshacer.setVisible(false);
                    limpiarParametros();
                    actualizarTablaExamenesAnalizador();
                }
            }
        }

    }//GEN-LAST:event_butAgregarActionPerformed

    private void butDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDeshacerActionPerformed

        limpiarParametros();
        tablaExamenesAnalizador.setEnabled(true);
        butDeshacer.setVisible(false);
        turnPanelHerramientas(true);
        tablaExamenesAnalizador.clearSelection();

    }//GEN-LAST:event_butDeshacerActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            butAgregarActionPerformed(null);
        }

    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
    }//GEN-LAST:event_txtNombreKeyTyped

    private void butEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEditarActionPerformed

        //Si se selecciona un elemento de la tabla
        if (tablaExamenesAnalizador.getSelectedRow() != -1) {
            cargarDatos(tablaExamenesAnalizador.getValueAt(tablaExamenesAnalizador.getSelectedRow(), 0).toString());
            butDeshacer.setVisible(true);
            tablaExamenesAnalizador.setEnabled(false);
            turnPanelHerramientas(true);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el elemento de la tabla que desea editar.");
        }

    }//GEN-LAST:event_butEditarActionPerformed

    private void butEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarActionPerformed

        int row = tablaExamenesAnalizador.getSelectedRow();
        //Si se selecciona un elemento de la tabla
        if (row != -1) {

            //Se confirma si el usuario desea eliminar el exámen del analizador.
            //En caso de que el exámen del analizador se encuentre relacionado con un exámen del sistema. La relación también se borrará.
            if (JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este exámen de analizador?\nNota: Si el examen de analizador cuenta con relación, está será eliminada.") == 0) {
                String consecutivo = tablaExamenesAnalizador.getValueAt(row, 0).toString();
                String codigoAnalizador = tablaExamenesAnalizador.getValueAt(row, 1).toString();

                ConsultasAnalizador.eliminarRelacionExamenes(codigoAnalizador);
                ConsultasAnalizador.eliminarExamenAnalizador(consecutivo);
                JOptionPane.showMessageDialog(null, "Exámen eliminado.");

                actualizarTablaExamenesAnalizador();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el elemento de la tabla que desea eliminar.");
        }

    }//GEN-LAST:event_butEliminarActionPerformed

    /**
     * Método encargado de validar la información del exámen de analizador que
     * se desea registrar.
     *
     * @return true si las entradas son correctas, false de lo contrario.
     */
    public boolean verificarInvariantes() {

        //Si no se ingresó un código para el exámen del analizador.
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el código del exámen de analizador que desea registrar.");
            txtCodigo.requestFocus(true);
            txtCodigo.setBackground(Color.pink);
            return false;
        } else {
            txtCodigo.setBackground(Color.white);
        }

        //Si ya existe en el sistema un exámen de analizador con el código ingresado.
        if (ConsultasAnalizador.existeExamenAnalizador(txtCodigo.getText().trim()) && !butDeshacer.isVisible()) {
            JOptionPane.showMessageDialog(null, "El código ingresado ya se encuentra registrado.");
            txtCodigo.requestFocus(true);
            txtCodigo.setBackground(Color.pink);
            return false;
        } else {
            txtCodigo.setBackground(Color.white);
        }

        //Si no se ingresó un nombre para el exámen del analizador que se desea registrar.
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre del exámen de analizador que desea registrar.");
            txtNombre.requestFocus(true);
            txtNombre.setBackground(Color.pink);
            return false;
        } else {
            txtNombre.setBackground(Color.white);
        }

        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAgregar;
    private javax.swing.JButton butDeshacer;
    private javax.swing.JButton butEditar;
    private javax.swing.JButton butEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel panelHerramientas;
    private javax.swing.JPanel panelParametros;
    private javax.swing.JTable tablaExamenesAnalizador;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
